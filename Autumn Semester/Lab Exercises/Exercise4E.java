import sheffield.*;

public class Exercise4E {
  public static void main(String[] args) {
    EasyReader fileInput = new EasyReader("ex4Edata.txt");
    EasyWriter fileOutput = new EasyWriter("cipher.txt");

    EasyReader keyboard = new EasyReader();
    int shift = keyboard.readInt("Please enter a shift: ");
    shift = Math.floorMod(shift, 26);

    char myChar;

    while (!fileInput.eof()) {
      myChar = fileInput.readChar();

      if (Character.isLetter(myChar)) {
        fileOutput.print((char) ((int) myChar + shift));
      } else {
        fileOutput.print(myChar);
      }
    }
  }
}
