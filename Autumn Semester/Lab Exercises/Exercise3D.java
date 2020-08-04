import sheffield.*;
import javax.swing.JOptionPane;

public class Exercise3D {
  public static void main(String[] args) {
    // reads the user input from GUI
		String code = JOptionPane.showInputDialog("Enter a letter code of an amino acid: ");
		char codeSingle = code.charAt(0);

		// switch case
		switch (codeSingle) {
			case 'a': case 'A':
				System.out.println("The amino acid is ALANINE.");
				System.out.println("The acidity or basicity is NEUTRAL.");
				break;
			case 'e': case 'E':
				System.out.println("The amino acid is GLUTAMIC ACID.");
				System.out.println("The acidity or basicity is ACIDIC.");
				break;
			case 'g': case 'G':
				System.out.println("The amino acid is GLYCINE.");
				System.out.println("The acidity or basicity is NEUTRAL.");
				break;
			case 'h': case 'H':
				System.out.println("The amino acid is HISTIDINE.");
				System.out.println("The acidity or basicity is BASIC.");
				break;
			case 'k': case 'K':
				System.out.println("The amino acid is LYSINE.");
				System.out.println("The acidity or basicity is BASIC.");
				break;
			case 'f': case 'F':
				System.out.println("The amino acid is PHENYLAlANINE.");
				System.out.println("The acidity or basicity is NEUTRAL.");
				break;
			default:
				System.out.println("The amino acid is UNKNOWN.");
				System.out.println("The acidity or basicity is UNKNOWN.");
		}
  }
}