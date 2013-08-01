package com.wubinben.kata.pottercucumberjvm;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


import static org.junit.Assert.assertEquals;

/**
 * Potter Step Definitions
 * User: Ben
 * Date: 13-7-31
 * Time: 下午5:45
 * To change this template use File | Settings | File Templates.
 */
public class PotterStepdefs {

    @Given("^I clear my shopping basket$")
    public void I_clear_my_shopping_basket() throws Throwable {
        PotterBookClient.initializeBasket();
    }

    @Given("^I buy (\\d+) copies of (\\d+)(?:st|nd|rd|th) book$")
    public void I_buy_copies_of_book(int numberOfBook, int seriesNumberOfBook) throws Throwable {
        PotterBookClient.putIntoBasket(numberOfBook, seriesNumberOfBook);
    }
    @When("^I calculate the price$")
    public void I_calculate_the_price() throws Throwable {
        PotterBookClient.calculatePrice();
    }

    @Then("^I should get the lowest price (\\d+)$")
    public void I_should_get_the_lowest_price(int expectedPrice) throws Throwable {
        assertEquals("failure - not the same with expected price", expectedPrice, PotterBookClient.getCalculatedPrice());
    }

}
