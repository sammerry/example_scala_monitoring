logBuffered := false
name := "ExampleServer"
parallelExecution in test := false

resolvers ++= Seq(
  s3resolver.value("S3 Releases resolver",
    s3("maven-bucket/maven/releases")) withMavenPatterns,
  s3resolver.value("S3 Releases resolver",
    s3("ivy-bucket/ivy/releases")) withIvyPatterns)

libraryDependencies ++= {
  val akkaV       = "2.5.6"
  val akkaHttpV   = "10.0.10"
  val jacksonV    = "2.9.2"

  Seq(
    "com.typesafe.akka" %% "akka-actor" % akkaV,
    "com.typesafe.akka" %% "akka-stream" % akkaV,
    "com.typesafe.akka" %% "akka-http" % akkaHttpV,

    "io.sentry"               % "sentry-logback" % "1.5.0",

    "io.dropwizard.metrics"   %  "metrics-jvm" % "3.2.2",
    "io.dropwizard.metrics"   %  "metrics-logback" % "3.2.2",
    "org.coursera"            %  "metrics-datadog" % "1.1.13",
    "nl.grons"                %% "metrics-scala" % "3.5.9_a2.4")
}