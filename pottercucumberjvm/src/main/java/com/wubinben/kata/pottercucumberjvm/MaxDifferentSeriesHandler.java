package com.wubinben.kata.pottercucumberjvm;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 13-8-2
 * Time: 上午12:58
 * To change this template use File | Settings | File Templates.
 */
public class MaxDifferentSeriesHandler extends DiscountHandler {
    public static MaxDifferentSeriesHandler newInstance() {
        return new MaxDifferentSeriesHandler();
    }

    @Override
    public void handleRequest(ShoppingBasket shoppingBasket, DiscountStrategy discountStrategy, BookPriceCalculator bookPriceCalculator) {
        while (shoppingBasket.areThereAnyBooksLeft()) {
            bookPriceCalculator.fillSeriesBoxAndCalculatePrice(shoppingBasket);
        }
        getSuccessor().handleRequest(shoppingBasket, discountStrategy, bookPriceCalculator);
    }
}
