package com.mdcurran

import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterAll, Suite}

trait SharedSparkSession extends BeforeAndAfterAll { self: Suite =>

  var spark: SparkSession = _

  override def beforeAll(): Unit = spark = createSparkSession

  override def afterAll(): Unit = spark.stop()

  def createSparkSession: SparkSession = {
    SparkSession
      .builder()
      .appName(this.getClass.getSimpleName)
      .master("local")
      .getOrCreate()
  }

}
