package ProblemSheet1;

/**
 * A class for the products of the vending machine Created by Zer Jun Eng on 08/02/17.
 */
public class ProductLine {

    private static final int NULL = -1;
    private static final int SPACING = 7;
    private static int longestNameLength = 0;
    // Instance variable
    private String name;
    private int price;
    private int amount;

    // Constructors

    /**
     * Constructor which initializes everything to values provided as parameter
     *
     * @param n The name of the product
     * @param p The price of the product
     * @param a The amount remaining of the product
     */
    public ProductLine(String n, int p, int a) {
        this.name = n;
        this.price = p;
        this.amount = a;
        setLongestNameLength(n.length());
    }

    // Methods

    /**
     * Get the longest name length of a product
     *
     * @return The longest name length
     */
    private int getLongestNameLength() {
        return longestNameLength;
    }

    /**
     * Set the longest name length to the one provided as a parameter
     *
     * @param newNameLength The name of the product
     */
    private void setLongestNameLength(int newNameLength) {
        if (newNameLength > longestNameLength) {
            longestNameLength = newNameLength;
        }
    }

    /**
     * Finds the name of the product
     *
     * @return The name of the product
     */
    public String getName() {
        return this.name;
    }

    /**
     * Finds the price of the product
     *
     * @return The price of the product
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Finds the amount remaining
     *
     * @return The amount remaining
     */
    public int getAmount() {
        return this.amount;
    }

    /**
     * Sell the product, decrease its amount by 1
     */
    public void sell() {
        this.amount -= 1;
    }

    /**
     * Refill the amount of the product
     *
     * @param a The amount to refill
     */
    public void refill(int a) {
        this.amount = a;
    }

    /**
     * Converts the product into a string
     *
     * @return A string representation of the product
     */
    public String toString() {
        String space = " ";
        String result = this.name;
        String priceTag = "Price : " + getPrice() + " pence,";

        if (price != NULL && amount != NULL) {
            result += ",";
            for (int i = 0; i < getLongestNameLength() - this.name.length(); i++) {
                result += space;
            }

            // Check if a product is sold out
            if (getAmount() == 0) {
                for (int i = 0; i < priceTag.length() + SPACING; i++) {
                    result += space;
                }
                result += "SOLD OUT";
            } else {
                result += priceTag;

                for (int i = 0; i < SPACING; i++) {
                    result += space;
                }

                result += getAmount() + " remaining";
            }
        }

        return result;
    }

}
