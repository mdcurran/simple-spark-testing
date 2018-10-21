organization := "com.mdcurran"

name := "simple-spark-testing"

version := "0.0.1"

scalaVersion := "2.11.12"

parallelExecution in Test := false

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.2" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.3.2" % "provided",
  "io.cucumber" % "cucumber-core" % "2.0.1",
  "io.cucumber" %% "cucumber-scala" % "2.0.1",
  "com.waioeka.sbt" %% "cucumber-runner" % "0.1.3",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5"
)

// Cucumber settings
val framework = new TestFramework("com.waioeka.sbt.runner.CucumberFramework")
testFrameworks += framework

testOptions in Test += Tests.Argument(framework,"--glue","")
testOptions in Test += Tests.Argument(framework,"--plugin","pretty")
testOptions in Test += Tests.Argument(framework,"--plugin","html:/tmp/html")
testOptions in Test += Tests.Argument(framework,"--plugin","json:/tmp/json")

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
