import sheffield.*;

public class Exercise2F {
  public static void main(String[] args) {
    EasyReader keyboard = new EasyReader();

    String input = keyboard.readString("Please type a sentence: ");
    char oldChar  = input.charAt(3);

    System.out.println(input.replace(oldChar, '*'));
  }
}