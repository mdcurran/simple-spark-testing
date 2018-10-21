package com.mdcurran.scalatest

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.functions.col
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, Row}
import org.scalatest.FlatSpec

class SharedSparkSessionTests extends FlatSpec with SharedSparkSession {
  import SharedSparkSessionTests._

  "The factorialSum function" should "return the sum of the factorial of an RDD of integers" in {
    val rdd = spark.sparkContext.parallelize(Seq(1, 2, 3, 4, 5))
    assert(factorialSum(rdd) == 153)
  }

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

object SharedSparkSessionTests {

  private def factorialSum(rdd: RDD[Int]): Int = {
    def factorial(n: Int): Int =
      if (n == 0) 1
      else n * factorial(n - 1)
    rdd.map(i => factorial(i)).reduce(_ + _)
  }

  private def countOfAge(df: DataFrame): DataFrame = df.groupBy("age").count()

}
