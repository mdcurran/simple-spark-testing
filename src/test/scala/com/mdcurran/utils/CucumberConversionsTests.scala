package com.mdcurran.utils

import com.mdcurran.scalatest.SharedSparkSession
import com.mdcurran.utils.CucumberConversions._
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.scalatest.FlatSpec

class CucumberConversionsTests extends FlatSpec with SharedSparkSession {

  "The deriveSchema function" should "return a Spark SQL StructType schema" in {
    val expected = StructType(List(
      StructField("col_a", DataTypes.StringType),
      StructField("col_b", DataTypes.IntegerType)
    ))
    val columns = List(("col_a", DataTypes.StringType), ("col_b", DataTypes.IntegerType))
    val output = deriveSchema(columns)
    assert(output == expected)
  }

}
