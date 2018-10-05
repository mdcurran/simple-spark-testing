package com.mdcurran

import org.scalatest.FlatSpec

class SharedSparkSessionTests extends FlatSpec with SharedSparkSession {

  "The createSparkSession function" should "create an active SparkSession" in {
    assert(!spark.sparkContext.isStopped)
  }

}
