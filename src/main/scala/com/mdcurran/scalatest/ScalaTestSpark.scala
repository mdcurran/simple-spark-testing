package com.mdcurran.scalatest

import com.mdcurran.utils.CustomSparkSession
import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterAll, Suite}

trait ScalaTestSpark extends BeforeAndAfterAll { self: Suite =>

  var spark: SparkSession = _

  override def beforeAll(): Unit = spark = CustomSparkSession.createSparkSession

  override def afterAll(): Unit = spark.stop()

}
