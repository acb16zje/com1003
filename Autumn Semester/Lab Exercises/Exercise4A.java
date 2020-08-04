/* A simple Java program
* Written by: Eng Zer Jun
* First written: 18/10/2016
* Last updated: 18/10/2016
*/
import sheffield.*;

public class Exercise4A {

  enum Suit { H, C, D, S };

  public static void main(String[] args) {
    // read user input
    EasyReader keyboard = new EasyReader();

    Suit cardSuit = Suit.valueOf(
      keyboard.readString("What is the initial letter of the suit? ").toUpperCase()
    );

    //switch case
    switch (cardSuit) {
      case H: case C:
        System.out.println("The suit is red.");
        break;

      case D: case S:
        System.out.println("The suit is black.");
        break;

      default:
        System.out.println("Invalid suit.");
        break;
    }
  }
}