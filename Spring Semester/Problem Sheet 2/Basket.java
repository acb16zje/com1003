package ProblemSheet2;

/**
 * Basket.java
 *
 * Model of a shopping basket containing items of shopping
 *
 * @author Zer Jun Eng Date: 17/02/17
 */

public class Basket {

    private static final double ROUNDING = 1000.0;
    // instance fields
    private Item[] items;

    /**
     * Stores the item array into a basket
     *
     * @param it The items array
     */
    public Basket(Item[] it) {
        items = it;
    }

    /**
     * Calculate the total price of all items
     *
     * @return The total price of all items
     */
    public double total() {
        double tot = 0.0;
        for (int i = 0; i < items.length; i++) {
            tot += items[i].getPrice();
        }
        return Math.round(tot * ROUNDING) / ROUNDING;
    }
}
