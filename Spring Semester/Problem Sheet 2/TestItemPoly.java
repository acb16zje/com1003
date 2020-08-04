package ProblemSheet2;

/**
 * A class for testing polymorphism
 *
 * @author Zer Jun Eng Date: 17/02/17
 */

public class TestItemPoly {

    /**
     * The main method for the vending machine program
     *
     * @param args String[]	Command line arguments - not used
     */
    public static void main(String[] args) {
        Item[] polyTest = {
            new Item("Bananas", 0.8),
            new Item("Gala Apples", 1.5),
            new Item("Braeburn Apples", 1.5),

            new ItemByWeight("Onions", 0.16, 1.3),
            new ItemByWeight("Cabbage", 0.79, 1),
            new ItemByWeight("Red Onions", 0.90, 1)
        };

        // Test by displaying the properties of the object
        for (int i = 0; i < polyTest.length; i++) {
            System.out.println(polyTest[i]);
        }
    }
}
