package com.mdcurran

import com.mdcurran.scalatest.SharedSparkSession
import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.functions.col
import org.scalatest.FlatSpec

class DataFrameTests extends FlatSpec with SharedSparkSession {
  import DataFrameTests._

  "The countOfAge function" should "return a DataFrame counting the number of records per age" in {
    val data = Seq(
      Row("John", 21),
      Row("Jane", 18),
      Row("Jim", 21),
      Row("Jack", 19),
      Row("Jonah", 19),
      Row("Janet", 21))
    val schema = Seq(
      StructField("name", StringType),
      StructField("age", IntegerType))
    val df = spark.createDataFrame(spark.sparkContext.parallelize(data), StructType(schema))
    val result = countOfAge(df)
    assert(result.filter(col("age") === 21).select("count").first().mkString(",") === "3")
  }

  "The countOfAge function" should "return a row for each unique age" in {
    val data = Seq(
      Row("John", 21),
      Row("Jane", 18),
      Row("Jim", 21),
      Row("Jack", 19),
      Row("Jonah", 19),
      Row("Janet", 21))
    val schema = Seq(
      StructField("name", StringType),
      StructField("age", IntegerType))
    val df = spark.createDataFrame(spark.sparkContext.parallelize(data), StructType(schema))
    val result = countOfAge(df)
    assert(result.count === 3)
  }

}

object DataFrameTests {

  private def countOfAge(df: DataFrame): DataFrame = df.groupBy("age").count()

}
