package ProblemSheet2;

/**
 * ItemByWeight.java
 *
 * Subclass of Item, where price is specified by unit weight
 *
 * @author Zer Jun Eng Date: 17/02/17
 */

public class ItemByWeight extends Item {

    // static constants
    private static final double ROUNDING = 1000.0;
    // instance field
    private double weight;

    /**
     * Constructor for ItemByWeight
     *
     * @param n The name of the item
     * @param p The price of the item
     * @param w The weight of the item
     */
    public ItemByWeight(String n, double p, double w) {
        super(n, p);
        weight = w;
    }

    /**
     * Gets the price of the item * weight
     *
     * @return The final price * weight
     */
    public double getPrice() {
        return Math.round(weight * super.getPrice() * ROUNDING) / ROUNDING;
    }

    // An equals method that use the Item superclass equals method

    /**
     * Converts the item into a string
     *
     * @return A string representation of the item
     */
    public String toString() {
        return (getName() + " (" + weight + "kg @ "
            + super.getPrice() + "ukp/kg) = ukp"
            + getPrice());
    }

    /**
     * Test if two objects are equal
     *
     * @param otherObject The object passed in as a parameter
     * @return The boolean value
     */
    public boolean equals(Object otherObject) {
        if (super.equals(otherObject)) {
            return true;
        }

        ItemByWeight object = (ItemByWeight) otherObject;
        return getName() == object.getName() && getPrice() == object.getPrice();
    }
}
