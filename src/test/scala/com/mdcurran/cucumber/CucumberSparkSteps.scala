package com.mdcurran.cucumber

import com.waioeka.sbt.runner.CucumberSpec
import cucumber.api.DataTable
import org.apache.spark.sql.DataFrame
import org.scalatest.Matchers

class CucumberTestSuite extends CucumberSpec

class CucumberSparkSteps extends CucumberSpark with Matchers {

  var dataTable: DataTable = _
  var dataFrame: DataFrame = _

  Given("""^a Cucumber DataTable$""") { data: DataTable => dataTable = data }

  When("""^the DataTable is converted to a Spark DataFrame$""") { () =>
    dataFrame = dataTableToDataFrame(dataTable)
  }

  Then("""^the DataFrame has (\d+) columns$""") { numColumns: Int =>
    dataFrame.columns.length should be (numColumns)
  }

  Then("""^the DataFrame has (\d+) row$""") { numRows: Int =>
    dataFrame.count should be (numRows)
  }

  Then("""^the DataFrame has the correct data$""") { () =>
    val firstRow = dataFrame.first().mkString(",")
    assert(firstRow == "abc,10")
  }

  Then("""^the DataFrame has the correct column names$""") { () =>
    val expectedColumns = Array("col_a", "col_b")
    dataFrame.columns should be (expectedColumns)
  }

  Then("""^the DataFrame columns have the expected data types$""") { () =>
    val expectedTypes = Array(("col_a", "StringType"), ("col_b", "IntegerType"))
    dataFrame.dtypes should be (expectedTypes)
  }

}
