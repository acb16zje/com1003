/**
 * COM 1003 Assignment 3
 *
 * @author Zer Jun Eng
 */

import ShefRobot.*;

public class Assignment3 {

    // static constants, variables
    private static final int SPEED = 100;
    private static final int DOTS_NUMBER = 16;
    private static final int SAME_DOTS = 2;
    private static int index = 0;
    private static int dotsCounter = 0;
    private static int arraySearchCount = 0;
    private static boolean firstDotOnGrid = false;

    /**
     * The main method
     * @param args the standard command line string array
     */
    public static void main(String[] args) {
        // Robot setting
        Robot myRobot = new Robot();
        Motor leftMotor = myRobot.getLargeMotor(Motor.Port.A);
        Motor rightMotor = myRobot.getLargeMotor(Motor.Port.D);
        ColorSensor myColor = myRobot.getColorSensor(Sensor.Port.S1);
        Speaker speaker = myRobot.getSpeaker();

        // Speed setting
        leftMotor.setSpeed(SPEED);
        rightMotor.setSpeed(SPEED);

        // Colour sensor setting
        myColor.setMode(ColorSensor.Mode.COLOR);
        myColor.setFloodlightState(ColorSensor.FloodlightState.WHITE);

        // Create Dots array
        Dot[] dotsArray = new Dot[DOTS_NUMBER];

        //* Main program body *//
        // Go straight until the color sensor detects the black line and turn left
        while (!Position.isBlack(myColor)) {
            Movement.forward(leftMotor, rightMotor);
        }

        Movement.stop(leftMotor, rightMotor);
        Movement.turnLeft(leftMotor, rightMotor);

        // Check the colour sensor is on the black line after rotating
        while (!Position.isBlack(myColor)) {
            Movement.searchBlack(leftMotor, rightMotor, myColor);
        }

        //* The algorithm *//
        // While the robot has not met the same colour dot twice yet,
        // it will move from one dot to the next and going around the edge.
        // Every coordinate of a dot is recorded when the robot passes through them,
        // and the shortest distance of the two dots is calculated using Pythagorean theorem,
        // the angle that the robot needs to turn is calculated using Trigonometric function,
        // and finally the robot will turn and move towards the first dot.
        while (!Dot.hasMetSameColour()) {
            // Move from one dot to the next by following the black line
            while (Position.isBlack(myColor) && !Position
                .isColour(myRobot, leftMotor, rightMotor, myColor)) {
                Movement.forward(leftMotor, rightMotor);
            }

            // Search for the black line again if the sensor fails to detect it
            while (!Position.isBlack(myColor) && !Position
                .isColour(myRobot, leftMotor, rightMotor, myColor)) {
                Movement.searchBlack(leftMotor, rightMotor, myColor);
            }

            // If a colour dot is detected
            if (Position.isColour(myRobot, leftMotor, rightMotor, myColor)) {
                // Move to the centre of the dot to record coordinate later
                Movement.forwardToDotCentre(leftMotor, rightMotor);
                Dot.Colour currentColour = Dot.whichColour(myColor);

                // Check if it is the first dot the robot meets
                if (!firstDotOnGrid) {
                    // Set the first dot to be Origin(0, 0)
                    firstDotOnGrid = true;
                    Dot.resetCoordinate(leftMotor, rightMotor);
                    dotsArray[dotsCounter] = new Dot(currentColour, 0, 0);
                } else {
                    int prevX = dotsArray[dotsCounter - 1].getXCoordinate();
                    int prevY = dotsArray[dotsCounter - 1].getYCoordinate();
                    int currX = 0;
                    int currY = 0;

                    if (Movement.getCurrentDirection() == Movement.Direction.NORTH) {
                        // X-coordinate will be the same as the previous dot
                        currY = prevY + Dot.getCurrentCoordinate(leftMotor, rightMotor);
                        dotsArray[dotsCounter] = new Dot(currentColour, prevX, currY);
                    } else if (Movement.getCurrentDirection() == Movement.Direction.EAST) {
                        // Y-coordinate will be the same as the previous dot
                        currX = prevX + Dot.getCurrentCoordinate(leftMotor, rightMotor);
                        dotsArray[dotsCounter] = new Dot(currentColour, currX, prevY);
                    } else if (Movement.getCurrentDirection() == Movement.Direction.SOUTH) {
                        // X-coordinate will be the same as the previous dot
                        currY = prevY - Dot.getCurrentCoordinate(leftMotor, rightMotor);
                        dotsArray[dotsCounter] = new Dot(currentColour, prevX, currY);
                    } else if (Movement.getCurrentDirection() == Movement.Direction.WEST) {
                        // Y-coordinate will be the same as the previous dot
                        currX = prevX - Dot.getCurrentCoordinate(leftMotor, rightMotor);
                        dotsArray[dotsCounter] = new Dot(currentColour, currX, prevY);
                    }
                }

                // Reset coordinate and increase the dots count after detecting a colour
                Dot.resetCoordinate(leftMotor, rightMotor);
                dotsCounter++;

                // Don't check for corners in the second same colour dot
                if (!Dot.hasMetSameColour()) {
                    // Check if the current dot is a corner
                    if (Position.isCorner(myRobot, leftMotor, rightMotor, myColor)) {
                        // Turn right and go forward until it reaches the black line
                        Movement.turnRight(leftMotor, rightMotor);

                        // Reset the bonus tacho count in checking
                        Dot.resetCoordinate(leftMotor, rightMotor);
                        while (Position.isColour(myRobot, leftMotor, rightMotor, myColor)) {
                            Movement.forward(leftMotor, rightMotor);
                        }

                        Movement.searchBlack(leftMotor, rightMotor, myColor);
                    }
                } else {
                    break;
                }
            }
        }
        // Sings when second same colour dot has been detected
        Movement.sing(myRobot, speaker);

        // Going back to the first dot
        // Get the index of the dots with same colour, save as parameter
        Dot.Colour colourDetected = Dot.colourCheck(myColor);
        int[] dotIndex = new int[SAME_DOTS];

        for (int i = 0; i < dotsArray.length - 1; i++) {
            if (arraySearchCount == 2) {
                break;
            } else if (colourDetected == dotsArray[i].getColour()) {
                dotIndex[index] = i;
                index++;
                arraySearchCount++;
            }
        }

        // Calculate the distance
        int distance = Dot.distanceBetweenTwoDots(dotsArray[dotIndex[0]], dotsArray[dotIndex[1]]);

        // Rotate to the first dot
        Dot.angleToRotate(leftMotor, rightMotor, dotsArray[dotIndex[0]], dotsArray[dotIndex[1]]);

        // Move towards first dot
        Dot.resetCoordinate(leftMotor, rightMotor);
        leftMotor.rotateTo(distance, true);
        rightMotor.rotateTo(distance);

        // Finishing dance
        if (Dot.colourCheck(myColor) == colourDetected) {
            Movement.dance(leftMotor, rightMotor);
        } else {
            while (Dot.colourCheck(myColor) != colourDetected) {
                Position.searchDot(myRobot, leftMotor, rightMotor, myColor, colourDetected);
            }
            Movement.dance(leftMotor, rightMotor);
        }

        // Ending
        myRobot.close();
    }
}
