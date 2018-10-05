organization := "com.mdcurran.simple.spark.testing"

name := "simple-spark-testing-library"

version := "0.0.1"

scalaVersion := "2.11.12"

parallelExecution in Test := false

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % "2.3.2" % "provided",
  "org.apache.spark" %% "spark-sql" % "2.3.2" % "provided",
  "org.scalactic" %% "scalactic" % "3.0.5",
  "org.scalatest" %% "scalatest" % "3.0.5"
)