/**
 * COM1003 Assignment 2 Zer Jun Eng (acb16zje) Created on 09-Nov-2016.
 */

import sheffield.*;

public class Assignment2 {

    public static void main(String[] args) {
        // create a graphics windows of size (360, 90) * 3
        final int MAX_WIDTH = 1080;
        final int MAX_HEIGHT = 270;
        EasyGraphics draw = new EasyGraphics(MAX_WIDTH, MAX_HEIGHT);

        // create an array of 52 rows and 130 columns for a simple duck picture, then reads the file input
        final int DUCK_ROW = 52;
        final int DUCK_COLUMN = 130;
        char[][] duckPicture = new char[DUCK_ROW][DUCK_COLUMN];
        EasyReader fileInput = new EasyReader("duck.txt");

        // for the duck picture, store 130 character per row into the array, total 6760 characters
        for (int y = 0; y < duckPicture.length; y++) {
            for (int x = 0; x < duckPicture[0].length; x++) {
                duckPicture[y][x] = fileInput.readChar();
            }
        }

        // create an array of 90 rows and 360 columns for a picture of a pond
        final int POND_ROW = 90;
        final int POND_COLUMN = 360;
        char[][] pondPicture = new char[POND_ROW][POND_COLUMN];

        // for the pond pictures, store 360 characters per row into the array, total 32400 characters
        for (int row = 0; row < pondPicture.length; row++) {
            for (int column = 0; column < pondPicture[0].length; column++) {
                pondPicture[row][column] = fileInput.readChar();
            }
        }

        final int TRIPLE = 3; // constant for triple the size of the pond picture
        final int POND_TOP_Y = pondPicture.length
            - 1; // the starting y-coordinate for the top line of the pond picture

        // display the pond first so it won't cover the ducks
        /* the top line of the picture is the first row of the array,
        * the y-coordinate to fill the top line is (pondPicture.length - 1) as the coordinate start from 0,
        * so (POND_TOP_Y - i) will descend a row when all the columns in the row are filled */
        for (int i = 0; i < pondPicture.length; i++) {
            for (int j = 0; j < pondPicture[0].length; j++) {
                switch (pondPicture[i][j]) {
                    case 's':
                        draw.setColor(50, 150, 250); // blue colour
                        draw.fillRectangle(j * TRIPLE, (POND_TOP_Y - i) * TRIPLE, TRIPLE, TRIPLE);
                        break;

                    case 'w':
                        draw.setColor(1, 100, 100); // dark blue colour
                        draw.fillRectangle(j * TRIPLE, (POND_TOP_Y - i) * TRIPLE, TRIPLE, TRIPLE);
                        break;

                    case 'g':
                        draw.setColor(144, 252, 12); // green colour
                        draw.fillRectangle(j * TRIPLE, (POND_TOP_Y - i) * TRIPLE, TRIPLE, TRIPLE);
                        break;

                    case 'b':
                        draw.setColor(97, 50, 4); // brown colour
                        draw.fillRectangle(j * TRIPLE, (POND_TOP_Y - i) * TRIPLE, TRIPLE, TRIPLE);
                        break;
                }
            }
        }

        final int DOUBLE = 2; // double the size of the duck picture
        final int DUCK_TOP_Y = duckPicture.length
            - 1; // the starting y-coordinate for the top line of the duck picture
        final int BIG_WIDTH = 260; // width of big duck picture is 260 pixels
        final int SMALL_WIDTH = 130; // width of small duck picture is 130 pixels
        final int VERTICAL_ALIGN = 26; // to vertically align, smaller duck must add (104-52)/2, which is 26

        // display four ducks without background
        // duckPicture.length / 2 will only display top half of the duck
        for (int a = 0; a < duckPicture.length / 2; a++) {
            for (int b = 0; b < duckPicture[0].length; b++) {
                if (duckPicture[a][b] == 'x') {
                    draw.setColor(255, 255, 0); // yellow colour
                    draw.fillRectangle(b * DOUBLE, (DUCK_TOP_Y - a) * DOUBLE, DOUBLE,
                        DOUBLE); // larger duck

                    // display 3 small ducks behind the larger duck
                    /* the first duck need to be displayed after the larger duck, so its x-coordinate must add the width of the larger duck,
                    * the x-coordinate of the next smaller duck need to add the width of the previous smaller duck and the larger duck,
                    * to vertically align the smaller ducks, their y-coordinate must add half the height difference between the larger duck*/
                    for (int numOfSmallDuck = 0; numOfSmallDuck < 3; numOfSmallDuck++) {
                        draw.fillRectangle(b + (BIG_WIDTH + SMALL_WIDTH * numOfSmallDuck),
                            (DUCK_TOP_Y - a) + VERTICAL_ALIGN, 1, 1);
                    }
                }
            }
        }
    }
}