import sheffield.*;

public class Exercise5A {
  public static void main(String[] args) {
    EasyReader keyboard = new EasyReader();

    double[] randArray = new double[10];

    for (int i = 0; i < randArray.length; i++) {
      randArray[i] = Math.random();
      System.out.println(i + " : " + randArray[i]);
    }

    int userInput = keyboard.readInt("Enter an array index: ");
    System.out.println("Thee value at array index " + userInput + " is " + randArray[userInput]);
  }
}
