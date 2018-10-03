package com.mdcurran.simple.spark.testing

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SQLContext
import org.scalatest.{BeforeAndAfterAll, Suite}

trait SharedSQLContext extends BeforeAndAfterAll { self: Suite =>

  var sqlContext: SQLContext = _

  override def beforeAll(): Unit = sqlContext = createSQLContext

  override def afterAll(): Unit = sqlContext.sparkContext.stop()

  def createSQLContext: SQLContext = {
    val conf = new SparkConf()
      .setMaster("local")
      .setAppName(this.getClass.getSimpleName)

    val sparkContext = new SparkContext(conf)
    new SQLContext(sparkContext)
  }

}
