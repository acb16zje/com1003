import sheffield.*;

public class Exercise3C {
  public static void main(String[] args) {
    EasyReader keyboard = new EasyReader();

    int temperature = keyboard.readInt("Temperature in degrees: ");

    if (temperature >= 101 && temperature <= 120) {
      System.out.println("Dripping");
    } else if (temperature >= 91 && temperature <= 100) {
      System.out.println("Sweating");
    } else if (temperature >= 81 && temperature <= 90) {
      System.out.println("Perspiring");
    } else if (temperature >= 71 && temperature <= 80) {
      System.out.println("Glowing");
    } else if (temperature >= 40 && temperature <= 70) {
      System.out.println("Gleaming");
    } else {
      System.out.println("Out of range");
    }
  }
}