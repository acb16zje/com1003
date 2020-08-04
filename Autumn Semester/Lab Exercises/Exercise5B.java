import sheffield.*;

public class Exercise5B {
  public static void main(String[] args) {
    EasyReader keyboard = new EasyReader();

    int[] array = new int[10];
    int average = 0;

    for (int i = 0; i < array.length; i++) {
      array[i] = keyboard.readInt("Enter number " + i + ": ");
      average += array[i];
    }

    System.out.println("The average value is " + (average / 10.0));
  }
}
