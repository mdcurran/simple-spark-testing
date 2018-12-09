package com.mdcurran.scalatest

import com.mdcurran.utils.CustomSparkSession
import org.apache.spark.sql.SparkSession
import org.scalatest.{BeforeAndAfterAll, Suite}

/**
 * Trait to provide a new SparkSession before each ScalaTest test.
 */
trait ScalaTestSpark extends BeforeAndAfterAll { self: Suite =>

  var spark: SparkSession = _

  override def beforeAll(): Unit = spark = CustomSparkSession.createSparkSession

  override def afterAll(): Unit = spark.stop()

}
