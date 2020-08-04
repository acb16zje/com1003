import sheffield.*;

public class Exercise2C {
  public static void main(String[] args) {
    EasyReader fileInput = new EasyReader("data.txt");
    double numberOfPounds1 = fileInput.readDouble();
    double numberOfPounds2 = fileInput.readDouble();

    final double POUND_TO_EURO = 1.13;
    double numberOfEuros1 = numberOfPounds1 * POUND_TO_EURO;
    double numberOfEuros2 = numberOfPounds2 * POUND_TO_EURO;
    double totalEuros = numberOfEuros1 + numberOfEuros2;

    EasyWriter fileOutput = new EasyWriter("call it out.txt");
    fileOutput.println(numberOfEuros1, 2, 0);
    fileOutput.println(numberOfEuros2, 2, 0);
    fileOutput.println(totalEuros, 2, 0);
  }
}