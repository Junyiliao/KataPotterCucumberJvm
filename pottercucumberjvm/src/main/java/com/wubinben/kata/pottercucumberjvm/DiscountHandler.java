package com.wubinben.kata.pottercucumberjvm;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 13-8-2
 * Time: 上午12:56
 * To change this template use File | Settings | File Templates.
 */
public abstract class DiscountHandler {
    private DiscountHandler successor;

    public DiscountHandler getSuccessor() {
        return successor;
    }

    public void setSuccessor(DiscountHandler successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(ShoppingBasket shoppingBasket, DiscountStrategy discountStrategy, BookPriceCalculator bookPriceCalculator);
}
