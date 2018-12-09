package com.mdcurran.cucumber

import com.mdcurran.utils.CucumberConversions._
import com.mdcurran.utils.CustomSparkSession
import cucumber.api.DataTable
import cucumber.api.scala.{EN, ScalaDsl}
import org.apache.spark.sql.{DataFrame, SparkSession}

/**
  * Trait to provide a new SparkSession before each Cucumber scenario. Also implements
  * functionality to convert Cucumber/Gherkin DataTables to Spark DataFrames.
  */
trait CucumberSpark extends ScalaDsl with EN {

  var spark: SparkSession = _

  Before { scenario =>
    spark = CustomSparkSession.createSparkSession
  }

  After { scenario =>
    spark.stop()
  }

  def dataTableToDataFrame(data: DataTable): DataFrame = {
    val columns = extractColumns(data)
    val schema = deriveSchema(columns)
    val rows = extractRows(data, schema)
    spark.sqlContext.createDataFrame(spark.sparkContext.parallelize(rows), schema) // Borrow from scenario
  }

}
