package com.mdcurran.utils

import com.mdcurran.scalatest.SharedSparkSession
import com.mdcurran.utils.CucumberConversions._
import cucumber.api.DataTable
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.scalatest.FlatSpec

class CucumberConversionsTests extends FlatSpec with SharedSparkSession {

  "The extractColumns function" should "return a List containing tuples (name, DataType)" in {
    val data = DataTable.create(java.util.Arrays.asList(
      java.util.Arrays.asList("col_a: String", "col_b: Int"),
      java.util.Arrays.asList("abc", 10))
    )
    val expected = List(("col_a", DataTypes.StringType), ("col_b", DataTypes.IntegerType))
    val output = extractColumns(data)
    assert(output == expected)
  }

  "The deriveSchema function" should "return a Spark SQL StructType schema" in {
    val columns = List(("col_a", DataTypes.StringType), ("col_b", DataTypes.IntegerType))
    val expected = StructType(List(
      StructField("col_a", DataTypes.StringType),
      StructField("col_b", DataTypes.IntegerType)
    ))
    val output = deriveSchema(columns)
    assert(output == expected)
  }

  "The extractRows function" should "return a List of Row type that can be used to construct a DataFrame" in {
    val data = DataTable.create(java.util.Arrays.asList(
      java.util.Arrays.asList("col_a: String", "col_b: Int"),
      java.util.Arrays.asList("abc", 10))
    )
    val schema = StructType(List(
      StructField("col_a", DataTypes.StringType),
      StructField("col_b", DataTypes.IntegerType)
    ))
    println(data)
    val expected = List(Row("abc", 10))
    val output = extractRows(data, schema)
    assert(output == expected)
  }

}
