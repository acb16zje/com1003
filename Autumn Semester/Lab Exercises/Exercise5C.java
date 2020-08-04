import sheffield.*;

public class Exercise5C {
  public static void main(String[] args) {
    EasyReader keyboard = new EasyReader();

    int[][] array = new int[2][5];

    for (int i = 0; i < array.length; i++) {
      for (int j = 0; j < array[i].length; j++) {
        array[i][j] = keyboard.readInt("Enter array value " + j + " of list " + (i + 1) + " : ");
      }

      System.out.println();
    }

    int sameCount = 0;

    for (int i = 0; i < array[0].length; i++) {
      for (int j = 0; i < array[0].length; j++) {
        if (array[0][i] == array[1][j]) {
          sameCount++;
          break;
        }
      }
    }

    if (sameCount == 5) {
      System.out.println("The two lists have the same contents");
    } else {
      System.out.println("The two lists do not have the same contents");
    }
  }
}
