package com.mdcurran.simple.spark.testing

import org.scalatest.FlatSpec

class SharedSparkContextTests extends FlatSpec with SharedSparkContext {

  "The createSparkContext function" should "create an active SparkContext" in {
    assert(!sparkContext.isStopped)
  }

  "The createSparkContext function" should "have an appName of the local class" in {
    assert(sparkContext.appName == "SharedSparkContextTests")
  }

  "The createSparkContext function" should "create a local instance of a SparkContext" in {
    assert(sparkContext.isLocal)
  }

  "The createSparkContext function" should "have the master set to local" in {
    assert(sparkContext.master == "local")
  }

}
