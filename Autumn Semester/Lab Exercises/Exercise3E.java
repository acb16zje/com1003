import sheffield.*;

public class Exercise3e {
	public static void main(String[] args){

		// reads the input from user
		EasyReader keyboard = new EasyReader();
		boolean sunny = keyboard.readBoolean("Is it sunny today? : ");

		// switch case
		if (sunny) {
			int numberOfMonth = keyboard.readInt("What number the month is? : ");

			if (numberOfMonth >= 5 && numberOfMonth <= 9)
			  System.out.println("Remember to use sunscreen.");
			else {
				System.out.println("You don't need sunscreen unless you burn very easily.");
      }
		} else {
			System.out.println("Take an umbrella, it's not sunny.");
    }
	}
}