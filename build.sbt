organization := "com.mdcurran"

name := "simple-spark-testing"

version := "0.0.1"

scalaVersion := "2.11.12"

parallelExecution in Test := false

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.2" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.3.2" % "provided",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5"
)

// Publish settings
scmInfo := Some(
  ScmInfo(
    url("https://github.com/mdcurran/simple-spark-testing"),
    "scm:git@github.com:mdcurran/simple-spark-testing.git"
  )
)

developers := List(
  Developer(
    id = "maxcurran96",
    name = "Max Curran",
    email = "maxcurran96@gmail.com",
    url = url("https://github.com/mdcurran")
  )
)

description := "A small library to facilitate an easy way to test Spark applications whilst adhering to best practices."
licenses := List("Apache 2" -> new URL("http://www.apache.org/licenses/LICENSE-2.0.txt"))
homepage := Some(url("https://github.com/mdcurran/simple-spark-testing"))

pomIncludeRepository := { _ => false }
publishTo := {
  val nexus = "https://oss.sonatype.org/"
  if (isSnapshot.value) Some("snapshots" at nexus + "content/repositories/snapshots")
  else Some("releases" at nexus + "service/local/staging/deploy/maven2")
}
publishMavenStyle := true