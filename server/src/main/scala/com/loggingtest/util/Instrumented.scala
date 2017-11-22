package com.loggingtest.util

import java.util.concurrent.TimeUnit

import com.codahale.metrics.{JmxReporter, MetricRegistry, SharedMetricRegistries}
import nl.grons.metrics.scala.InstrumentedBuilder
import org.coursera.metrics.datadog.transport.UdpTransport
import org.coursera.metrics.datadog.DatadogReporter

trait Instrumented extends InstrumentedBuilder {
  val metricRegistry: MetricRegistry = SharedMetricRegistries.getOrCreate("default")

  private val transport = new UdpTransport.Builder().build()
  new DatadogReporter.Builder(metricRegistry)
    .convertDurationsTo(TimeUnit.MILLISECONDS)
    .withTransport(transport)
    .build()

  JmxReporter.forRegistry(metricRegistry)
    .convertDurationsTo(TimeUnit.MILLISECONDS).build()
}
