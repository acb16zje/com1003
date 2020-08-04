import sheffield.*;

public class Exercise3B {
  public static void main(String[] args) {
    EasyReader keyboard = new EasyReader();

    char note = keyboard.readChar("Note Name: ");

    switch (note) {
      case 'C':
      case 'c':
        System.out.println("Doh");
        break;
      case 'D':
      case 'd':
        System.out.println("Ray");
        break;
      case 'E':
      case 'e':
        System.out.println("Me");
        break;
      case 'F':
      case 'f':
        System.out.println("Fah");
        break;
      case 'G':
      case 'g':
        System.out.println("Soh");
        break;
      case 'A':
      case 'a':
        System.out.println("La");
        break;
      case 'B':
      case 'b':
        System.out.println("Ti");
        break;
      default:
        System.out.println("Not a valid note.");
    }
  }
}