logLevel := Level.Warn

addSbtPlugin("pl.project13.scala" % "sbt-jmh" % "0.2.27")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.6")

addSbtPlugin("ohnosequences" % "sbt-s3-resolver" % "0.17.0")

resolvers += Resolver.jcenterRepo