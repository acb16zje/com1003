package ProblemSheet2;

/**
 * Item.java
 *
 * Model of an item of shopping
 *
 * @author Zer Jun Eng Date: 17/02/17
 */

public class Item {

    // instance fields
    private double price;

    // Accessors methods
    private String name;

    /**
     * Constructor for Item
     *
     * @param n The name of the item
     * @param p The price of the item
     */
    public Item(String n, double p) {
        name = n;
        price = p;
    }

    // using ukp to denote pounds sterling as unicode pound does not display
    // properly in MS Command Window

    /**
     * The main method
     *
     * @param args String[]	Command line arguments - not used
     */
    public static void main(String[] args) {
        final String TESTNAME = "testObject";
        final double TESTPRICE = 10.0;
        Item testObject = new Item(TESTNAME, TESTPRICE);
        System.out.println("Name:");
        System.out.println("Actual field " + testObject.getName());
        System.out.println("Expected " + TESTNAME);
        System.out.println("Price:");
        System.out.println("Actual field " + testObject.getPrice());
        System.out.println("Expected " + TESTPRICE);
    }

    // equals method to be added here

    /**
     * Gets the name of the item
     *
     * @return The name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the price of the item
     *
     * @return The price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Converts the item into a string
     *
     * @return A string representation of the item
     */
    public String toString() {
        return (name + " = ukp" + price);
    }

    /**
     * Test if two objects are equal
     *
     * @param obj The object passed in as a parameter
     * @return The boolean value
     */
    public boolean equals(Object obj) {
        // check if identical objects
        if (this == obj) {
            return true;
        }

        // must be false if parameter is null
        if (obj == null) {
            return false;
        }

        // must be false if objects have different classes
        if (getClass() != obj.getClass()) {
            return false;
        }

        // now we can cast and do something specific for Item
        Item object = (Item) obj;

        return getName() == object.getName() && getPrice() == object.getPrice();
    }
}
