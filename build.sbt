

lazy val commonSettings = Seq(
  organization := "com.loggingtest",
  version := "0.0.1",
  scalaVersion := "2.12.4",
  publishMavenStyle := false,
  resolvers ++= Seq[Resolver](
    Resolver.mavenLocal,
    Resolver.sonatypeRepo("public"),
    Resolver.sonatypeRepo("releases"),
    Resolver.sonatypeRepo("snapshots"),
    Resolver.bintrayRepo("cakesolutions", "maven"),
    Resolver.bintrayRepo("beyondthelines", "maven"),
    Resolver.typesafeRepo("releases")))

lazy val server = (project in file("server"))
  .settings(commonSettings: _*)

lazy val client = (project in file("client"))
  .settings(commonSettings: _*)

lazy val benchServer = (project in file("bench-server"))
  .dependsOn(server % "compile;compile->test")
  .settings(commonSettings: _*)

lazy val benchClient = (project in file("bench-client"))
  .dependsOn(client % "compile;compile->test")
  .settings(commonSettings: _*)