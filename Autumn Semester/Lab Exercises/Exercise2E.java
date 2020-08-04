import sheffield.*;
import javax.swing.JOptionPane;

public class Exercise2E {
  public static void main(String[] args) {
    String fullTeleNumber = JOptionPane.showInputDialog("Enter your telephone number: (e.g. (0114) 239 3124)");

    System.out.println("The area code is: " + fullTeleNumber.substring(1, fullTeleNumber.indexOf(")")));
    System.out.println("The telephone number is: " + fullTeleNumber.substring(fullTeleNumber.indexOf(" "), fullTeleNumber.length()).trim());
  }
}