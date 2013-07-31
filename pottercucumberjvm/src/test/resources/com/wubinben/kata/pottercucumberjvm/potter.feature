Feature: Kata Potter
  In order to get as big a discount as possible
  As a reader
  I want to calculate the lowest price of different series of Potter books in the basket.

  Scenario Outline: test basics
    Given I buy <number of 1st book> copies of 1st book
    And I buy <number of 2nd book>  copies of 2nd book
    And I buy <number of 3rd book> copies of 3rd book
    And I buy <number of 4th book> copies of 4th book
    And I buy <number of 5th book> copies of 5th book
    When I calculate the price
    Then I should get the lowest price <lowest price>

    Examples:
    | number of 1st book | number of 2nd book | number of 3rd book | number of 4th book | number of 5th book | lowest price |
    |         0          |           0        |           0        |         0         |          0         |       0      |
    |         1          |           0        |           0        |         0         |          0         |       8      |
    |         0          |           1        |           0        |         0         |          0         |       8      |
    |         0          |           0        |           1        |         0         |          0         |       8      |
    |         0          |           0        |           0        |         1         |          0         |       8      |
    |         0          |           0        |           0        |         0         |          1         |       8      |
    |         2          |           0        |           0        |         0         |          0         |       16     |
    |         0          |           3        |           0        |         0         |          0         |       24     |
