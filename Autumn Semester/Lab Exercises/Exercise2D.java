import sheffield.*;
import javax.swing.JOptionPane;

public class Exercise2D {
  public static void main(String[] args) {
    String givenName = JOptionPane.showInputDialog("Enter your given name: ");
    String familyName = JOptionPane.showInputDialog("Enter your family name: ");

    System.out.println("Hi, " + givenName + " " + familyName + ".");
    JOptionPane.showMessageDialog(null, "Hi, " + givenName + " " + familyName + ".", "Hello!",
      JOptionPane.INFORMATION_MESSAGE);
  }
}