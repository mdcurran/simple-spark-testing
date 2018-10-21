package com.mdcurran.utils

import cucumber.api.DataTable
import org.apache.spark.sql.{DataFrame, Row}
import org.apache.spark.sql.types.{DataType, StructField, StructType}

object CucumberConversions {

  def dataTableToDataFrame(data: DataTable): DataFrame = ???

  def extractColumns(data: DataTable): List[(String, DataType)] = ???

  def deriveSchema(columns: List[(String, DataType)]): StructType = {
    StructType(columns.map {
      case (name, dataType) => StructField(name, dataType)
    })
  }

  def extractRows(data: DataTable, schema: StructType): List[Row] = ???

}
