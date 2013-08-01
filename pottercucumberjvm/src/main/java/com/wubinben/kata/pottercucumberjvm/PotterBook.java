package com.wubinben.kata.pottercucumberjvm;

/**
 * Potter Book
 * User: Ben
 * Date: 13-7-31
 * Time: 下午6:45
 * To change this template use File | Settings | File Templates.
 */
public class PotterBook {
    /**
     * Utility classes should not have a public or default constructor.
     */
    private PotterBook() {
    }
    public static PotterBook newInstance() {
        return new PotterBook();
    }

}
