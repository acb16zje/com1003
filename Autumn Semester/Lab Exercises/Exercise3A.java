import sheffield.*;

public class Exercise3A {
  public static void main(String[] args) {
    EasyReader keyboard = new EasyReader();

    int age = keyboard.readInt("What is your age: ");

    if (age < 18) {
      System.out.println("You cannot buy alcohol.");
    } else if (age >= 18 && age < 25) {
      System.out.println("You can buy alcohol but you need to proof your age.");
    } else {
      System.out.println("You can buy alcohol and don't need to proof your age.");
    }
  }
}