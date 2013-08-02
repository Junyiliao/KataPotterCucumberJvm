package com.wubinben.kata.pottercucumberjvm;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 13-8-1
 * Time: 下午7:29
 * To change this template use File | Settings | File Templates.
 */
public class BookPriceCalculator {
    private int calculatedPrice = 0;

    private static final Logger LOGGER = Logger.getLogger(BookPriceCalculator.class.getName());

    public BookPriceCalculator() {
        // To turn on logging, set level to be Level.INFO.
        LOGGER.setLevel(Level.OFF);
    }


    void calculatePriceForEachSeriesBox(int[] seriesBox) {
        int bookCount = 0;
        for (int number : seriesBox) {
            bookCount += number;
        }
        switch (bookCount) {
            case 1:
                calculatedPrice += DiscountStrategy.PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT;
                break;
            case 2:
                calculatedPrice += DiscountStrategy.PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT * 2 * DiscountStrategy.DISCOUNT_FOR_TWO_SERIES;
                break;
            case 3:
                calculatedPrice += DiscountStrategy.PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT * 3 * DiscountStrategy.DISCOUNT_FOR_THREE_SERIES;
                break;
            case 4:
                calculatedPrice += DiscountStrategy.PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT * 4 * DiscountStrategy.DISCOUNT_FOR_FOUR_SERIES;
                break;
            case 5:
                calculatedPrice += DiscountStrategy.PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT * 5 * DiscountStrategy.DISCOUNT_FOR_FIVE_SERIES;
                break;
            default:
                throw new IllegalStateException("the number of series should be within " + DiscountStrategy.MAX_SERIES_NUMBER);
        }
    }

    void calculatePrice(ShoppingBasket shoppingBasket, DiscountStrategy discountStrategy) {
        DiscountHandler fiveThreePatternHandler = FiveThreePatternHandler.newInstance();
        DiscountHandler maxDifferentSeriesHandler = MaxDifferentSeriesHandler.newInstance();
        DiscountHandler nullHandler = NullHandler.newInstance();

        fiveThreePatternHandler.setSuccessor(maxDifferentSeriesHandler);
        maxDifferentSeriesHandler.setSuccessor(nullHandler);

        fiveThreePatternHandler.handleRequest(shoppingBasket, discountStrategy, this);

    }

    public int getCalculatedPrice() {
        return calculatedPrice;
    }

    public void setCalculatedPrice(int calculatedPrice) {
        this.calculatedPrice = calculatedPrice;
    }



    public void fillSeriesBoxAndCalculatePrice(ShoppingBasket shoppingBasket) {
        calculatePriceForEachSeriesBox(shoppingBasket.generateSeriesBox());
    }

}
