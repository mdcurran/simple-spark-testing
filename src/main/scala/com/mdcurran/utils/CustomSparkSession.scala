package com.mdcurran.utils

import org.apache.spark.sql.SparkSession

object CustomSparkSession {

  def createSparkSession: SparkSession = {
    SparkSession
      .builder()
      .appName(this.getClass.getSimpleName)
      .master("local")
      .getOrCreate()
  }

}
