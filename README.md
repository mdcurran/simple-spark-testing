# simple-spark-testing

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/74599363b0734ad1843843273fad87f5)](https://app.codacy.com/app/mdcurran/simple-spark-testing?utm_source=github.com&utm_medium=referral&utm_content=mdcurran/simple-spark-testing&utm_campaign=Badge_Grade_Dashboard)
[![travis](https://travis-ci.org/mdcurran/simple-spark-testing.svg?branch=master)](https://travis-ci.org/mdcurran/simple-spark-testing)
[![codecov](https://codecov.io/gh/mdcurran/simple-spark-testing/branch/master/graph/badge.svg)](https://codecov.io/gh/mdcurran/simple-spark-testing)

## Overview

A small library designed to be a wrapper for unit and integration tests. Tailored for Spark 2.3.

Support is offered for both ScalaTest and Cucumber, and there is functionality to allow Cucumber DataTables to be mocked as Spark DataFrames.

Both test frameworks can be run simultaneously using the `sbt test` command from a project's root directory.

## Setup

The following should be added to your `build.sbt` file:

```scala
parallelExecution in Test := false
```

Additionally if Cucumber is used, then the following should also be added to `build.sbt` as Cucumber support is dependent on this framework (https://github.com/lewismj/cucumber):

```scala
val framework = new TestFramework("com.waioeka.sbt.runner.CucumberFramework")
testFrameworks += framework

testOptions in Test += Tests.Argument(framework,"--glue","")
testOptions in Test += Tests.Argument(framework,"--plugin","pretty")
testOptions in Test += Tests.Argument(framework,"--plugin","html:/tmp/html")
testOptions in Test += Tests.Argument(framework,"--plugin","json:/tmp/json")
```

## ScalaTest

Mix-in your test class with the `ScalaTestSpark` trait. This will create a new SparkSession for each test, meaning a global SparkSession isn't shared across the full suite.

```scala
class ExampleTests extends FlatSpec with ScalaTestSpark
```

`FlatSpec` can be replaced with any testing style (e.g. `FunSuite` or `FeatureSpech`), but there must be one present.

The `beforeAll()` and `afterAll()` methods can be overwritten if additional setup is needed, for example:

```scala
override def beforeAll(): Unit = {
    spark = CustomSparkSession.createSparkSession
    data = DataTable.create(java.util.Arrays.asList(
      java.util.Arrays.asList("col_a: String", "col_b: Int"),
      java.util.Arrays.asList("abc", 10))
    )
    super.beforeAll()
}
```

## Cucumber

Mix-in your steps file with the `CucumberSpark` trait. Like the ScalaTest trait, a new SparkSession is created for each Scenario.

```scala
class ExampleSteps extends CucumberSpark
```

A Cucumber DataTable can be converted to a Spark DataFrame in the `Given` step of a Scenario:

```scala
var dataFrame: DataFrame = _

Given("""^an example DataTable$""") { dataTable: DataTable =>
  dataFrame = dataTableToDataFrame(dataTable)
}
```

For additional steps, even if no arguments are captured from the `.feature` file, an empty parameters list - `() =>` - must be included:

```scala
When("""^Some transformation is applied$""") { () =>
  ...
}
```

## Contact

Max Curran: maxcurran96@gmail.com
