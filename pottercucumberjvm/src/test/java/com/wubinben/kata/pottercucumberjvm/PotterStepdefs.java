package com.wubinben.kata.pottercucumberjvm;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.ArrayDeque;
import java.util.Arrays;

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
        initializeBasket();
        putIntoBasket(numberOfBook, seriesNumberOfBook);
    }

    private void initializeBasket() {
        for(ArrayDeque series : basket) {
            series.clear();
        }
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
        int[] seriesBox = {0, 0, 0, 0, 0};
        this.calculatedPrice = 0;
        if (hasPatternFiveThree()) {
            this.calculatedPrice -= 40;
        }
        while (areThereAnyBooksLeft()) {
            fillSeriesBoxAndCalculatePrice(seriesBox);
        }
    }

    private boolean hasPatternFiveThree() {
        int[][] basketTwoDArray = convertBasketToTwoDArray();
        printBasketTwoDArray(basketTwoDArray);
        int[] differentSeriesCount = countDifferentSeries(basketTwoDArray);
        for (int i = 0; i < differentSeriesCount.length; i++) {
            if (differentSeriesCount[i] == 5) {
                if (differentSeriesCount[i+1] == 3) {
                    return true;
                }
            }
        }
        return false;
    }

    private void printBasketTwoDArray(int[][] basketTwoDArray) {
        System.out.print("basketTwoDArray: \n[\n");
        for (int[] row : basketTwoDArray) {
            System.out.print("[");
            for (int cell : row) {
                System.out.print(cell + ", ");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    private int[] countDifferentSeries(int[][] basketTwoDArray) {
        int[] differentSeriesCount = new int[10];
        int count = 0;
        for (int i = 0; i < differentSeriesCount.length; i++) {
            for (int j = 0; j < basketTwoDArray.length; j++) {
                count += basketTwoDArray[j][i];
            }
            differentSeriesCount[i] = count;
            count = 0;
        }
        return differentSeriesCount;
    }

    private int[][] convertBasketToTwoDArray() {
        int[][] twoDArray = new int[5][10];
        for (int i = 0; i < this.basket.length; i++) {
            for (int j = 0; j < this.basket[i].size(); j++) {
                if (i > 4 && j > 9) {
                    throw new IllegalStateException("the 2-d array is only 5x10.");
                }
                twoDArray[i][j] = 1;
            }
        }
        return twoDArray;
    }

    private void clearSeriesBox(int[] seriesBox) {
        for (int i = 0; i < seriesBox.length; i++) {
            seriesBox[i] = 0;
        }
    }

    private void fillSeriesBoxAndCalculatePrice(int[] seriesBox) {
        clearSeriesBox(seriesBox);
        for (int i = 0; i < basket.length; i++) {
            if (!basket[i].isEmpty()) {
                basket[i].pop();
                seriesBox[i] = 1;
            }
        }
        calculatePrice(seriesBox);
    }

    private void printBasket() {
        for (int i = 0; i < basket.length; i++) {
            System.out.println("==index: " + i + "; size of ArrayDeque:" + basket[i].size());
        }
    }

    private void calculatePrice(int[] seriesBox) {
        int bookCount = 0;
        for (int number : seriesBox) {
            bookCount += number;
        }
        switch (bookCount) {
            case 1:
                this.calculatedPrice += 800;
                break;
            case 2:
                this.calculatedPrice += 800 * 2 * 0.95;
                break;
            case 3:
                this.calculatedPrice += 800 * 3 * 0.9;
                break;
            case 4:
                this.calculatedPrice += 800 * 4 * 0.8;
                break;
            case 5:
                this.calculatedPrice += 800 * 5 * 0.75;
                break;
        }
    }

    private void printSeriesBox(int[] seriesBox) {
        System.out.println("---seriesBox: " + Arrays.toString(seriesBox));
    }

    private boolean areThereAnyBooksLeft() {
        for(ArrayDeque series : basket) {
            if (!series.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @Then("^I should get the lowest price (\\d+)$")
    public void I_should_get_the_lowest_price(int expectedPrice) throws Throwable {
        assertEquals("failure - not the same with expected price", expectedPrice, this.calculatedPrice);
    }

}
