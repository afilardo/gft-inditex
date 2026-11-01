Feature: Tariff search API

  Scenario Outline: The API returns the applicable tariff
    When I request a tariff for date "<date>", product <productId>, and brand <brandId>
    Then response code is 200
    And the response body contains
      | productId | <productId>   |
      | brandId   | <brandId>     |
      | tariffId  | <tariffId>    |
      | startDate | <startDate>   |
      | endDate   | <endDate>     |
      | price     | <price>       |

    Examples:
      | date                | productId | brandId | tariffId | startDate           | endDate             | price     |
      | 2020-06-14T10:00:00 | 35455     | 1       | 1        | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 | 35.50 EUR |
      | 2020-06-14T16:00:00 | 35455     | 1       | 2        | 2020-06-14 15:00:00 | 2020-06-14 18:30:00 | 25.45 EUR |
      | 2020-06-14T21:00:00 | 35455     | 1       | 1        | 2020-06-14 00:00:00 | 2020-12-31 23:59:59 | 35.50 EUR |
      | 2020-06-15T10:00:00 | 35455     | 1       | 3        | 2020-06-15 00:00:00 | 2020-06-15 11:00:00 | 30.50 EUR |
      | 2020-06-16T21:00:00 | 35455     | 1       | 4        | 2020-06-15 16:00:00 | 2020-12-31 23:59:59 | 38.95 EUR |

  Scenario Outline: The API returns not found response
    When I request a tariff for date "<date>", product <productId>, and brand <brandId>
    Then response code is 404

    Examples:
      | date                | productId | brandId |
      | 2020-06-14T10:00:00 | 0         | 1       |