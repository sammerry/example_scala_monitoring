package com.loggingtest

import akka.actor.ActorSystem
import akka.stream.{ActorMaterializer, ThrottleMode}
import akka.stream.scaladsl.Source
import com.loggingtest.util.Instrumented
import com.loggingtest.util.{Config => MyConfig}
import com.typesafe.config.Config

import scala.concurrent.Future
import scala.concurrent.duration._


object Server extends App with Instrumented {
  implicit val system = ActorSystem("testActorSystem")
  implicit val executor = system.dispatchers.lookup("test.customDispatcher")
  implicit val materializer = ActorMaterializer()

  val config: Config = MyConfig.config

  val RATE: Int = config.getInt("test.rate")
  val DURATION: FiniteDuration = config.getDuration("test.duration").toMillis millis
  val MAX_BURST: Int = config.getInt("test.burst")

  private val countStage = metrics.meter("fancyStage")
  private val timeFutureStage = metrics.timer("fancyTimer")
  private val finalCounter = metrics.counter("fancyCounter")

  def multiplyByTwo(x: Int): Int = {
    x*2
  }

  Source(1 to 10000)
    .throttle(RATE, DURATION, MAX_BURST, ThrottleMode.Shaping)

    // time a future that multiplies by 2
    .mapAsyncUnordered(8) { x =>
      timeFutureStage.time( Future( multiplyByTwo(x) ))}

    // count number of times we hit this stage
    .map { countNumber =>
      countStage.mark()
      countNumber

    // add 10 to all numbers returned from the future above
    }.runForeach { x =>
      finalCounter += 10
      println(x)
    }

}
