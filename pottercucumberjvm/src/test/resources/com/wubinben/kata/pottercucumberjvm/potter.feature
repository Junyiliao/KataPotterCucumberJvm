Feature: Kata Potter
  In order to get as big a discount as possible
  As a reader
  I want to calculate the lowest price of different series of Potter books in the basket.
  One copy of any of the five books costs 8 EUR. If, however, you buy two different books from
  the series, you get a 5% discount on those two books. If you buy 3 different books, you get a 10%
  discount. With 4 different books, you get a 20% discount. If you go the whole hog, and buy all 5,
  you get a huge 25% discount.
  The unit of price is cent.

  Scenario Outline: potter tests
    Given I buy <number of 1st book> copies of 1st book
    And I buy <number of 2nd book>  copies of 2nd book
    And I buy <number of 3rd book> copies of 3rd book
    And I buy <number of 4th book> copies of 4th book
    And I buy <number of 5th book> copies of 5th book
    When I calculate the price
    Then I should get the lowest price <lowest price>

    Examples: test basics
    | number of 1st book | number of 2nd book | number of 3rd book | number of 4th book | number of 5th book | lowest price |
    |         0          |           0        |           0        |         0         |          0         |       0      |
    |         1          |           0        |           0        |         0         |          0         |       800    |
    |         0          |           1        |           0        |         0         |          0         |       800    |
    |         0          |           0        |           1        |         0         |          0         |       800    |
    |         0          |           0        |           0        |         1         |          0         |       800    |
    |         0          |           0        |           0        |         0         |          1         |       800    |
    |         2          |           0        |           0        |         0         |          0         |       1600   |
    |         0          |           3        |           0        |         0         |          0         |       2400   |

  Examples: test simple discounts
    | number of 1st book | number of 2nd book | number of 3rd book | number of 4th book | number of 5th book | lowest price |
    |         1          |           1        |           0        |         0         |          0         |       1520   |
    |         1          |           0        |           1        |         0         |          1         |       2160   |
    |         1          |           1        |           1        |         0         |          1         |       2560   |
    |         1          |           1        |           1        |         1         |          1         |       3000   |
