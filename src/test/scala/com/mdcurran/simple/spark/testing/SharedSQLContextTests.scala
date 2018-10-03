package com.mdcurran.simple.spark.testing

import org.scalatest.FlatSpec

class SharedSQLContextTests extends FlatSpec with SharedSQLContext {

  "The createSQLContext function" should "create an active SQLContext" in {
    assert(!sqlContext.sparkContext.isStopped)
  }

}
