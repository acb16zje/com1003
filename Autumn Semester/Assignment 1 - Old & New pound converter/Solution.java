import sheffield.*;

public class Assignment1 {

    public static void main(String[] args) {
        final double SHILLINGS_TO_POUNDS = 20;
        final double OLD_PENCE_TO_POUNDS = 240;
        final double NEW_PENCE_TO_SHILLINGS = SHILLINGS_TO_POUNDS / 100;
        final double NEW_PENCE_TO_OLD = OLD_PENCE_TO_POUNDS / 100;
        final double POUNDS_TO_NEW_PENCE = 100;

        //Read in the values in pounds shillings and pence
        EasyReader keyboard = new EasyReader();
        int pounds = keyboard.readInt("How many pounds? ");
        int shillings = keyboard.readInt("How many shillings? ");
        int pence = keyboard.readInt("How many pence? ");

        //Output the value in decimal currency
        EasyWriter screen = new EasyWriter();
        screen.print("That is ");
        screen.print(pounds +
            shillings / SHILLINGS_TO_POUNDS +
            pence / OLD_PENCE_TO_POUNDS, 2);
        screen.println(" in decimal currency");

        //Open the file money.txt
        EasyReader money = new EasyReader("money.txt");

        //Read in the first line
        String line1 = money.readString();
        //Extract the decimal amount
        double decimal = Double.valueOf(
            line1.substring(
                "The price in new money is".length()));

        //Split it into pounds shillings and pence
        pounds = (int) decimal;
        shillings = (int) ((decimal - pounds) * POUNDS_TO_NEW_PENCE *
            NEW_PENCE_TO_SHILLINGS);
        pence = (int) ((decimal - pounds - shillings / SHILLINGS_TO_POUNDS) *
            POUNDS_TO_NEW_PENCE * NEW_PENCE_TO_OLD);

        //Output the answer
        screen.print(decimal, 2);
        screen.print(" in old money is L");
        screen.print(pounds);
        screen.print(".");
        screen.print(shillings);
        screen.print("s.");
        screen.print(pence);
        screen.println("d");

        //Output it again with headings
        screen.println("   Old    L  s  d");
        screen.print(decimal, 2, 6);
        screen.print(pounds, 5);
        screen.print(shillings, 3);
        screen.println(pence, 3);

        //Read in the final line
        decimal = money.readDouble();

        //Split it into pounds shillings and pence
        pounds = (int) decimal;
        shillings = (int) ((decimal - pounds) * POUNDS_TO_NEW_PENCE *
            NEW_PENCE_TO_SHILLINGS);
        pence = (int) ((decimal - pounds - shillings / SHILLINGS_TO_POUNDS)
            * POUNDS_TO_NEW_PENCE * NEW_PENCE_TO_OLD);

        //Output the answer
        screen.print(decimal, 2, 6);
        screen.print(pounds, 5);
        screen.print(shillings, 3);
        screen.println(pence, 3);

    }
}
