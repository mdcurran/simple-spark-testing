package com.mdcurran.utils

import org.apache.spark.sql.SparkSession

/**
  * Basic SparkSession used in tests. Users have the ability to define a custom
  * SparkSession if required.
  */
object CustomSparkSession {

  def createSparkSession: SparkSession = {
    SparkSession
      .builder()
      .appName(this.getClass.getSimpleName)
      .master("local")
      .getOrCreate()
  }

}
