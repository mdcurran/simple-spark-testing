Feature: Cucumber for Spark testing

    Scenario: Convert a DataTable to a DataFrame

        Given a Cucumber DataTable
          | col_a: String | col_b: Int |
          | abc           | 10         |
        When the DataTable is converted to a Spark DataFrame
        Then the DataFrame has 2 columns
        And the DataFrame has 1 row
        And the DataFrame has the correct data
        And the DataFrame has the correct column names
        And the DataFrame columns have the expected data types