package com.wubinben.kata.pottercucumberjvm;

import java.util.ArrayDeque;
import java.util.Arrays;
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
    public static final int MAX_SERIES_NUMBER = 5;
    public static final int CHEAPER_BY_FIVE_THREE_PATTERN = 40;
    public static final int FIVE_THREE_PATTERN_FIVE = 5;
    public static final int FIVE_THREE_PATTERN_THREE = 3;
    public static final int MAX_NUMBER_OF_COPIES_FOR_EACH_SERIES = 10;
    public static final int PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT = 800;
    public static final double DISCOUNT_FOR_TWO_SERIES = 0.95;
    public static final double DISCOUNT_FOR_THREE_SERIES = 0.9;
    public static final double DISCOUNT_FOR_FOUR_SERIES = 0.8;
    public static final double DISCOUNT_FOR_FIVE_SERIES = 0.75;
    private static ArrayDeque[] basket = {new ArrayDeque(), new ArrayDeque(), new ArrayDeque(), new ArrayDeque(), new ArrayDeque()};
    private static int calculatedPrice = 0;

    /**
     * Utility classes should not have a public or default constructor.
     */
    private PotterBookClient () {}

    public static int getCalculatedPrice() {
        return calculatedPrice;
    }

    public static void setCalculatedPrice(int calculatedPrice) {
        PotterBookClient.calculatedPrice = calculatedPrice;
    }

    public static void initializeBasket() {
        // To turn on logging, set level to be Level.INFO.
        LOGGER.setLevel(Level.OFF);
        for(ArrayDeque series : basket) {
            series.clear();
        }
    }

    public static void putIntoBasket(int numberOfBook, int seriesNumberOfBook) {
        if (seriesNumberOfBook <= 0 || seriesNumberOfBook > MAX_SERIES_NUMBER ) {
            throw new IllegalStateException("series number of book should be between 1 and " + MAX_SERIES_NUMBER);
        }
        for (int i = 0; i < numberOfBook; i++) {
            basket[seriesNumberOfBook - 1].push(PotterBook.newInstance());
        }
    }

    static void calculatePrice() {
        int[] seriesBox = {0, 0, 0, 0, 0};
        setCalculatedPrice(0);
        if (hasPatternFiveThree()) {
            setCalculatedPrice(getCalculatedPrice() - CHEAPER_BY_FIVE_THREE_PATTERN);
        }
        while (areThereAnyBooksLeft()) {
            fillSeriesBoxAndCalculatePrice(seriesBox);
        }
    }
    private static boolean hasPatternFiveThree() {
        int[][] basketTwoDArray = convertBasketToTwoDArray();
        int[] differentSeriesCount = countDifferentSeries(basketTwoDArray);
        for (int i = 0; i < differentSeriesCount.length; i++) {
            if (differentSeriesCount[i] == FIVE_THREE_PATTERN_FIVE && differentSeriesCount[i+1] == FIVE_THREE_PATTERN_THREE) {
                return true;
            }
        }
        return false;
    }

    private static int[] countDifferentSeries(int[][] basketTwoDArray) {
        int[] differentSeriesCount = new int[MAX_NUMBER_OF_COPIES_FOR_EACH_SERIES];
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

    private static int[][] convertBasketToTwoDArray() {
        int[][] twoDArray = new int[MAX_SERIES_NUMBER][MAX_NUMBER_OF_COPIES_FOR_EACH_SERIES];
        for (int i = 0; i < basket.length; i++) {
            for (int j = 0; j < basket[i].size(); j++) {
                if (i >= MAX_SERIES_NUMBER && j >= MAX_NUMBER_OF_COPIES_FOR_EACH_SERIES) {
                    throw new IllegalStateException("the 2-d array is only 5x10.");
                }
                twoDArray[i][j] = 1;
            }
        }
        printBasketTwoDArray(twoDArray);
        return twoDArray;
    }

    private static void clearSeriesBox(int[] seriesBox) {
        for (int i = 0; i < seriesBox.length; i++) {
            seriesBox[i] = 0;
        }
        printSeriesBox(seriesBox);
    }

    private static void fillSeriesBoxAndCalculatePrice(int[] seriesBox) {
        clearSeriesBox(seriesBox);
        for (int i = 0; i < basket.length; i++) {
            if (!basket[i].isEmpty()) {
                basket[i].pop();
                seriesBox[i] = 1;
            }
        }
        calculatePrice(seriesBox);
    }

    private static void calculatePrice(int[] seriesBox) {
        int bookCount = 0;
        printBasket();
        for (int number : seriesBox) {
            bookCount += number;
        }
        switch (bookCount) {
            case 1:
                calculatedPrice += PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT;
                break;
            case 2:
                calculatedPrice += PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT * 2 * DISCOUNT_FOR_TWO_SERIES;
                break;
            case 3:
                calculatedPrice += PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT * 3 * DISCOUNT_FOR_THREE_SERIES;
                break;
            case 4:
                calculatedPrice += PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT * 4 * DISCOUNT_FOR_FOUR_SERIES;
                break;
            case 5:
                calculatedPrice += PRICE_FOR_EACH_BOOK_WITHOUT_DISCOUNT * 5 * DISCOUNT_FOR_FIVE_SERIES;
                break;
            default:
                throw new IllegalStateException("the number of series should be within " + MAX_SERIES_NUMBER);
        }
    }

    private static boolean areThereAnyBooksLeft() {
        for(ArrayDeque series : basket) {
            if (!series.isEmpty()) {
                return true;
            }
        }
        return false;
    }

    private static void printBasketTwoDArray(int[][] basketTwoDArray) {
        StringBuilder output = new StringBuilder("");
        output.append("**basketTwoDArray: \n[\n");
        for (int[] row : basketTwoDArray) {
            output.append("[");
            for (int cell : row) {
                output.append(cell + ", ");
            }
            output.append("]");
        }
        output.append("]");
        LOGGER.info(output.toString());
    }

    private static void printSeriesBox(int[] seriesBox) {
        LOGGER.info("--seriesBox: " + Arrays.toString(seriesBox));
    }

    private static void printBasket() {
        StringBuilder output = new StringBuilder("==basket: [");
        for (int i = 0; i < basket.length; i++) {
            output.append(basket[i].size() + ", ");
        }
        output.append("]");
        LOGGER.info(output.toString());
    }

}
