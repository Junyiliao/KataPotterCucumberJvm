package com.wubinben.kata.pottercucumberjvm;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 13-8-1
 * Time: 下午12:15
 * To change this template use File | Settings | File Templates.
 */
public class PotterBookClient {
    private static final Logger LOGGER = Logger.getLogger(PotterBookClient.class.getName());
    private ShoppingBasket shoppingBasket = new ShoppingBasket();
    private BookPriceCalculator bookPriceCalculator = new BookPriceCalculator();
    private DiscountStrategy discountStrategy = new DiscountStrategy();

    /**
     * Utility classes should not have a public or default constructor.
     */
    public PotterBookClient() {
        // To turn on logging, set level to be Level.INFO.
        LOGGER.setLevel(Level.OFF);
    }

    public void putIntoBasket(int numberOfBook, int seriesNumberOfBook) {
        if (seriesNumberOfBook <= 0 || seriesNumberOfBook > DiscountStrategy.MAX_SERIES_NUMBER ) {
            throw new IllegalStateException("series number of book should be between 1 and " + DiscountStrategy.MAX_SERIES_NUMBER);
        }
        shoppingBasket.sortBooksInBasket(numberOfBook, seriesNumberOfBook);
    }

    void calculatePrice() {
        bookPriceCalculator.calculatePrice(shoppingBasket, discountStrategy);
    }

    public int getCalculatedPrice() {
        return bookPriceCalculator.getCalculatedPrice();
    }

    public void initializeBasket() {
        this.shoppingBasket = new ShoppingBasket();
        shoppingBasket.initializeBasket();
    }
}
