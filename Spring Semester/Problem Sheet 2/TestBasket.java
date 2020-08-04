package ProblemSheet2;

/**
 * Stores an array of Item and ItemByWeight objects in a Basket object
 *
 * @author Zer Jun Eng Date: 17/02/17
 */
public class TestBasket {

    /**
     * The main method for the vending machine program
     *
     * @param args String[]	Command line arguments - not used
     */
    public static void main(String[] args) {
        // Initialise the array
        Item[] shopping = {
            new Item("Baked beans", 0.3),
            new Item("Potato chips", 1.1),
            new ItemByWeight("Bananas", 0.4, 0.7),
            new ItemByWeight("Onions", 0.3, 0.8)
        };

        Basket basket = new Basket(shopping);

        // Test by printing out
        for (int i = 0; i < shopping.length; i++) {
            System.out.println(shopping[i]);
        }

        System.out.println("Total price: " + basket.total());
    }

}
