import sheffield.*;

public class Assignment2 {

    public static void main(String[] args) {
        //Constants
        final int DUCK_ROWS = 52;
        final int DUCK_COLS = 130;
        final int DUCK_SCALE = 2;
        final int POND_ROWS = 90;
        final int POND_COLS = 360;
        final int POND_SCALE = 3;

        //The reader
        EasyReader input = new EasyReader("duck.txt");

        //Read in the duck
        boolean[][] duck = new boolean[DUCK_ROWS][DUCK_COLS];
        for (int r = DUCK_ROWS - 1; r >= 0; r--) {
            for (int c = 0; c < DUCK_COLS; c++) {
                duck[r][c] = (input.readChar() == 'x');
            }
        }

        //Read in the pond
        Scene[][] pond = new Scene[POND_ROWS][POND_COLS];
        for (int r = POND_ROWS - 1; r >= 0; r--) {
            for (int c = 0; c < POND_COLS; c++) {
                switch (input.readChar()) {
                    case 's':
                        pond[r][c] = Scene.SKY;
                        break;
                    case 'w':
                        pond[r][c] = Scene.POND;
                        break;
                    case 'g':
                        pond[r][c] = Scene.GREEN;
                        break;
                    case 'b':
                        pond[r][c] = Scene.BROWN;
                        break;
                }
            }
        }

        //The display window
        EasyGraphics window = new EasyGraphics(POND_COLS * POND_SCALE,
            POND_ROWS * POND_SCALE);
        //The pond
        for (int r = 0; r < POND_ROWS; r++) {
            for (int c = 0; c < POND_COLS; c++) {
                switch (pond[r][c]) {
                    case SKY:
                        window.setColor(50, 150, 250);
                        break;
                    case POND:
                        window.setColor(0, 100, 100);
                        break;
                    case GREEN:
                        window.setColor(150, 255, 0);
                        break;
                    case BROWN:
                        window.setColor(100, 50, 0);
                        break;
                }
                window.fillRectangle(c * POND_SCALE, r * POND_SCALE,
                    POND_SCALE, POND_SCALE);
            }
        }

        //The big duck
        window.setColor(255, 255, 0); //yellow
        for (int r = DUCK_ROWS / 2; r < DUCK_ROWS; r++) {
            for (int c = 0; c < DUCK_COLS; c++) {
                if (duck[r][c]) {
                    window.fillRectangle(c * DUCK_SCALE, r * DUCK_SCALE,
                        DUCK_SCALE, DUCK_SCALE);
                }
            }
        }

        //The three little ducks
        final int GAP = DUCK_COLS;
        for (int i = 0; i < 3; i++) {
            for (int r = DUCK_ROWS / 2; r < DUCK_ROWS; r++) {
                for (int c = 0; c < DUCK_COLS; c++) {
                    if (duck[r][c]) {
                        window.plot(c + DUCK_COLS + (i + 1) * GAP, r + DUCK_ROWS / 2);
                    }
                }
            }
        }

    }

    ;

    enum Scene {SKY, POND, GREEN, BROWN}

}
