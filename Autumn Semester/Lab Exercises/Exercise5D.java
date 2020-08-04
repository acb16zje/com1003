import sheffield.*;

public class Exercise5D {
  public static void main(String[] args) {
    EasyReader fileInput = new EasyReader("ex5Ddata.txt");

    String[] array = new String[236];

    while(!fileInput.eof()) {
      array = fileInput.readString().split(" "));
    }
  }
}
