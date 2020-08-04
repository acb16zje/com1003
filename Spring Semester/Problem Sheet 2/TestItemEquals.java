package ProblemSheet2;

/**
 * A class to test for equals method
 *
 * @author Zer Jun Eng Date: 17/02/17
 */

public class TestItemEquals {

    /**
     * The main method for the vending machine program
     *
     * @param args String[]	Command line arguments - not used
     */
    public static void main(String[] args) {
        Item banana = new Item("Bananas", 0.8);
        Item galaApple = new Item("Gala Apples", 1.5);
        Item braeburnApple = new Item("Braeburn Apples", 1.5);
        Item anotherBraeburnApple = new Item("Braeburn Apples", 1.5);

        ItemByWeight onion = new ItemByWeight("Onions", 0.16, 1.3);
        ItemByWeight cabbage = new ItemByWeight("Cabbage", 0.79, 1);
        ItemByWeight redOnion = new ItemByWeight("Onions", 0.90, 1);

        // Test Item with Item
        System.out.println("Test Item with Item");
        System.out.println(banana.equals(galaApple));
        System.out.println(galaApple.equals(braeburnApple));
        System.out.println(braeburnApple.equals(anotherBraeburnApple));

        // Test Item with ItemByWeight
        System.out.println("\nTest Item with ItemByWeight");
        System.out.println(braeburnApple.equals(onion));

        // Test ItemByWeight with ItemByWeight
        System.out.println("\nTest ItemByWeight with ItemByWeight");
        System.out.println(onion.equals(cabbage));
        System.out.println(onion.equals(redOnion));
        System.out.println(onion.equals(onion));
    }
}
