package com.wubinben.kata.pottercucumberjvm;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 13-8-2
 * Time: 上午12:59
 * To change this template use File | Settings | File Templates.
 */
public class NullHandler extends DiscountHandler {
    public static NullHandler newInstance() {
        return new NullHandler();
    }

    @Override
    public void handleRequest(ShoppingBasket shoppingBasket, DiscountStrategy discountStrategy, BookPriceCalculator bookPriceCalculator) {
        throw new IllegalStateException("cannot invoke handle request method in NullHandler.");
    }
}
