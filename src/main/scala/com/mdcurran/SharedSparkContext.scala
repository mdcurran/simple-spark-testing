package com.mdcurran

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.{BeforeAndAfterAll, Suite}

trait SharedSparkContext extends BeforeAndAfterAll { self: Suite =>

  var sparkContext: SparkContext = _

  override def beforeAll(): Unit = sparkContext = createSparkContext

  override def afterAll(): Unit = sparkContext.stop()

  def createSparkContext: SparkContext = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName(this.getClass.getSimpleName)

    new SparkContext(conf)
  }

}
