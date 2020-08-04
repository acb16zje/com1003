/* A simple Java program
* Written by: Eng Zer Jun
* First written: 18/10/2016
* Last updated: 18/10/2016
*/
import sheffield.*;

public class Exercise4B {
  public static void main(String[] args) {
    // ask the user for an odd number input
    EasyReader keyboard = new EasyReader();
    int oddNumber = keyboard.readInt("Please enter an odd number: ");

    String asterisks = "*";
    String space = " ";

    // do the loop and print a * triangle
    if (oddNumber % 2 == 0) {
      System.out.println("It is not an odd number.");
    } else {
      for (int i = 0; i < oddNumber; i++) {
        for (int k = 0; k < oddNumber - i; k++) {
          System.out.print(space);
        }

        for (int j = 0; j <= i * 2; j++) {
          System.out.print(asterisks);
        }

        System.out.println();
      }
    }
  }
}