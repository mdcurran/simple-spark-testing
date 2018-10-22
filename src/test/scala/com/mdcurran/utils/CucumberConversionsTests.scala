package com.mdcurran.utils

import com.mdcurran.scalatest.SharedSparkSession
import com.mdcurran.utils.CucumberConversions._
import cucumber.api.DataTable
import org.apache.spark.sql.{Row, SparkSession}
import org.apache.spark.sql.types.{DataTypes, StructField, StructType}
import org.scalatest.FlatSpec

class CucumberConversionsTests extends FlatSpec with SharedSparkSession {

  var data: DataTable = _

  // Custom beforeAll() to include the mock DataTable
  override def beforeAll(): Unit = {
    spark = CustomSparkSession.createSparkSession
    data = DataTable.create(java.util.Arrays.asList(
      java.util.Arrays.asList("col_a: String", "col_b: Int"),
      java.util.Arrays.asList("abc", 10))
    )
  }

  "The extractColumns function" should "return a List containing tuples (name, DataType)" in {
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
    val schema = StructType(List(
      StructField("col_a", DataTypes.StringType),
      StructField("col_b", DataTypes.IntegerType)
    ))
    val expected = List(Row("abc", 10))
    val output = extractRows(data, schema)
    assert(output == expected)
  }

  "The dataTableToDataFrame function" should "produce a DataFrame with 2 columns" in {
    assert(dataTableToDataFrame(data).columns.length == 2)
  }

  it should "produce a DataFrame with 1 row" in {
    assert(dataTableToDataFrame(data).count == 1)
  }

  it should "have the correct data" in {
    assert(dataTableToDataFrame(data).first().mkString(",") == "abc,10")
  }

  it should "have the correct column names" in {
    assert(dataTableToDataFrame(data).columns sameElements Array("col_a", "col_b"))
  }

  it should "have the correct column data types" in {
    assert(dataTableToDataFrame(data).dtypes sameElements
      Array(("col_a", "StringType"), ("col_b", "IntegerType")))
  }

}
