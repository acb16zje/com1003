import sheffield.*;

public class Exercise4D {
  public static void main(String[] args) {
    EasyReader keyboard = new EasyReader();
    int userInput = keyboard.readInt("Enter the value of n: ");

    if (userInput < 0) {
      System.out.println("It must be a positive integer.");
    } else {
      while(userInput != 1) {
        if (userInput % 2 == 0) {
          userInput /= 2;
        } else {
          userInput = userInput * 3 + 1;
        }

        System.out.print(userInput + " ");
      }
    }
  }
}