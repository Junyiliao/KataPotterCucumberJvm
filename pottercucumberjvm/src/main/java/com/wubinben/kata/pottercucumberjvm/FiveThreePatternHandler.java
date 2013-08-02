package com.wubinben.kata.pottercucumberjvm;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: Ben
 * Date: 13-8-2
 * Time: 上午12:57
 * To change this template use File | Settings | File Templates.
 */
public class FiveThreePatternHandler extends DiscountHandler {
    private static final Logger LOGGER = Logger.getLogger(FiveThreePatternHandler.class.getName());
    public FiveThreePatternHandler() {
        // To turn on logging, set level to be Level.INFO.
        LOGGER.setLevel(Level.OFF);
    }
    public static FiveThreePatternHandler newInstance() {
        return new FiveThreePatternHandler();
    }

    @Override
    public void handleRequest(ShoppingBasket shoppingBasket, DiscountStrategy discountStrategy, BookPriceCalculator bookPriceCalculator) {
        if (discountStrategy.hasFiveThreePattern(shoppingBasket)) {
            bookPriceCalculator.setCalculatedPrice(bookPriceCalculator.getCalculatedPrice() - DiscountStrategy.CHEAPER_BY_FIVE_THREE_PATTERN);
            LOGGER.info(">>has pattern five three. calculated price: " + bookPriceCalculator.getCalculatedPrice());
        }
        getSuccessor().handleRequest(shoppingBasket, discountStrategy, bookPriceCalculator);
    }
}
