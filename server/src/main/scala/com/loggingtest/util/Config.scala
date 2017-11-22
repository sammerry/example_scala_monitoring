package com.loggingtest.util

import com.typesafe.config.ConfigFactory
import com.typesafe.config.{Config => TSConfig}

object Config {
  val config: TSConfig = ConfigFactory.load().resolve()
}
