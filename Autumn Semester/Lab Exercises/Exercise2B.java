import sheffield.*;

public class Exercise2B {
  public static void main(String[] args) {
    EasyReader keyboard = new EasyReader();
    System.out.println("Enter a distance in miles and yards");
    int numberOfMiles = keyboard.readInt("How many miles? : ");
    int numberOFYards = keyboard.readInt("How many yards? : ");


    final double MILE_TO_YARD = 1760;
    final double MILE_TO_KM = 1.60934;
    double mileInKM = (numberOfMiles + (numberOFYards / MILE_TO_YARD)) * MILE_TO_KM ;

    EasyWriter screen = new EasyWriter();
    System.out.print(numberOfMiles + " miles and " + numberOFYards + " yards is equivalent to ");
    screen.print(mileInKM, 2, 0);
    System.out.println("km");
  }
}