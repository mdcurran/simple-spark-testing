package com.mdcurran.simple.spark.testing

import org.apache.spark.rdd.RDD
import org.scalatest.FlatSpec

class RDDTests extends FlatSpec with SharedSparkContext {
  import RDDTests._

  "The factorialSum function" should "return the sum of the factorial of an RDD of integers" in {
    val rdd = sparkContext parallelize Seq(1, 2, 3, 4, 5)
    assert(factorialSum(rdd) == 153)
  }

}

object RDDTests {

  private def factorialSum(rdd: RDD[Int]): Int = {
    def factorial(n: Int): Int =
      if (n == 0) 1
      else n * factorial(n - 1)
    rdd.map(i => factorial(i)).reduce(_ + _)
  }

}
