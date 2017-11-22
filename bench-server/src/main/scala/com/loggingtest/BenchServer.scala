package com.loggingtest

import java.util.concurrent.TimeUnit
import org.openjdk.jmh.annotations._

import org.openjdk.jmh.annotations.{Benchmark, BenchmarkMode, OutputTimeUnit}

@OutputTimeUnit(TimeUnit.MILLISECONDS)
@BenchmarkMode(Array(Mode.Throughput))
class BenchServer {

  @Benchmark
  def benchMultiplication(): Unit = {
    Server.multiplyByTwo(1000)
  }
}