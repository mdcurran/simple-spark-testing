package com.mdcurran.simple.spark.testing

import org.apache.spark.{SparkConf, SparkContext}
import org.scalatest.Suite

trait SharedSparkContext { self: Suite =>

  var sparkContext: SparkContext = _

  def createSparkContext: SparkContext = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName(this.getClass.getSimpleName)

    new SparkContext(conf)
  }

}
