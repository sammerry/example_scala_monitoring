package com.loggingtest.util

import java.util.concurrent.TimeUnit

import com.codahale.metrics.{JmxReporter, MetricRegistry, SharedMetricRegistries}
import nl.grons.metrics.scala.InstrumentedBuilder
import org.coursera.metrics.datadog.DatadogReporter

trait Instrumented extends InstrumentedBuilder {
  val metricRegistry: MetricRegistry = SharedMetricRegistries.getOrCreate("default")

  //DatadogReporter.forRegistry(metricRegistry)
  //  .convertDurationsTo(TimeUnit.MILLISECONDS).build()

  JmxReporter.forRegistry(metricRegistry)
    .convertDurationsTo(TimeUnit.MILLISECONDS).build()
}
