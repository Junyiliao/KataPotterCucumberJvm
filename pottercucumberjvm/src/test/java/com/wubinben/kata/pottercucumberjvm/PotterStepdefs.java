package com.wubinben.kata.pottercucumberjvm;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayDeque;

import static org.junit.Assert.assertEquals;

/**
 * Potter Step Definitions
 * User: Ben
 * Date: 13-7-31
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */
public class PotterStepdefs {
    private ArrayDeque[] basket = {new ArrayDeque(), new ArrayDeque(), new ArrayDeque(), new ArrayDeque(), new ArrayDeque()};

    private int calculatedPrice = 0;

    @Given("^I buy (\\d+) copies of (\\d+)st book$")
    public void I_buy_copies_of_st_book(int numberOfBook, int seriesNumberOfBook) throws Throwable {
        putIntoBasket(numberOfBook, seriesNumberOfBook);
    }

    @Given("^I buy (\\d+)  copies of (\\d+)nd book$")
    public void I_buy_copies_of_nd_book(int numberOfBook, int seriesNumberOfBook) throws Throwable {
        putIntoBasket(numberOfBook, seriesNumberOfBook);
    }

    @Given("^I buy (\\d+) copies of (\\d+)rd book$")
    public void I_buy_copies_of_rd_book(int numberOfBook, int seriesNumberOfBook) throws Throwable {
        putIntoBasket(numberOfBook, seriesNumberOfBook);
    }

    @Given("^I buy (\\d+) copies of (\\d+)th book$")
    public void I_buy_copies_of_th_book(int numberOfBook, int seriesNumberOfBook) throws Throwable {
        putIntoBasket(numberOfBook, seriesNumberOfBook);
    }

    private void putIntoBasket(int numberOfBook, int seriesNumberOfBook) {
        if (seriesNumberOfBook <=0 || seriesNumberOfBook >=6) {
            throw new IllegalStateException("series number of book should be between 1 and 5.");
        }
        for (int i = 0; i < numberOfBook; i++) {
            basket[seriesNumberOfBook - 1].push(PotterBook.newInstance());
        }
    }

    @When("^I calculate the price$")
    public void I_calculate_the_price() throws Throwable {
        for (ArrayDeque series : this.basket) {
            while (series != null && !series.isEmpty()) {
                this.calculatedPrice += 8;
                series.pop();
            }
        }
    }

    @Then("^I should get the lowest price (\\d+)$")
    public void I_should_get_the_lowest_price(int expectedPrice) throws Throwable {
        assertEquals("failure - not the same with expected price", expectedPrice, this.calculatedPrice);
    }

}
