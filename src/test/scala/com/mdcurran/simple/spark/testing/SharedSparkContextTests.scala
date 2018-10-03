package com.mdcurran.simple.spark.testing

import org.scalatest.FlatSpec

class SharedSparkContextTests extends FlatSpec with SharedSparkContext {

  "The createSparkContext function" should "create an active SparkContext" in {
    val sparkContext = createSparkContext
    assert(!sparkContext.isStopped)
  }

}
