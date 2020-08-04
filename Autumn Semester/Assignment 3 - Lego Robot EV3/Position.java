/**
 * COM 1003 Assignment 3 A class for checking the current robot position
 *
 * @author Zer Jun Eng
 */

import ShefRobot.*;

public class Position {

    private static final int CORNER_TIME = 100;
    private static final int SEARCH_BACK_TIME = 500;
    private static final int SEARCH_FORWARD_TIME = 300;
    private static final int NUMBER_OF_SEARCH = 3;
    private static final int SEARCH_ANGLE = 35;
    private static boolean isBlack = false;
    private static boolean isColour = false;
    private static boolean isCorner = false;
    // Constants for searchDot method
    private static boolean firstSearch = true;

    /**
     * Check if the sensor detects black colour
     * @return boolean     True if it detects black colour
     * @param    myColor         Robot's colour sensor
     */
    public static boolean isBlack(ColorSensor myColor) {
        if (myColor.getColor() == ColorSensor.Color.BLACK) {
            isBlack = true;
        } else if (myColor.getColor() == ColorSensor.Color.WHITE) {
            isBlack = false;
        }

        return isBlack;
    }

    /**
     * Check if the sensor detects a colour except black and white
     * @return boolean    True if it detects a colour
     * @param    myRobot     Lego Robot Mindstorm EV3
     * @param    leftMotor    Robot's left motor
     * @param    rightMotor   Robot's right motor
     * @param    myColor         Robot's colour sensor
     */
    public static boolean isColour(Robot myRobot, Motor leftMotor, Motor rightMotor,
        ColorSensor myColor) {
        if (myColor.getColor() == ColorSensor.Color.RED
            || myColor.getColor() == ColorSensor.Color.GREEN ||
            myColor.getColor() == ColorSensor.Color.BLUE
            || myColor.getColor() == ColorSensor.Color.YELLOW) {
            // Perform an error check (black line as blue dot error)
            if (Movement.colourErrorCheck(myRobot, leftMotor, rightMotor, myColor)) {
                isColour = true;
            } else {
                isColour = false;
            }
        } else if (myColor.getColor() == ColorSensor.Color.BLACK
            || myColor.getColor() == ColorSensor.Color.WHITE) {
            isColour = false;
        }

        return isColour;
    }

    /**
     * Check if the robot has reached a corner
     * @return boolean     True if the current position is a corner
     * @param    myRobot     Lego Robot Mindstorm EV3
     * @param    leftMotor    Robot's left motor
     * @param    rightMotor   Robot's right motor
     * @param    myColor         Robot's colour sensor
     */
    public static boolean isCorner(Robot myRobot, Motor leftMotor, Motor rightMotor,
        ColorSensor myColor) {
        // While the robot detects a colour dot, move forward
        while (Position.isColour(myRobot, leftMotor, rightMotor, myColor)) {
            Movement.forward(leftMotor, rightMotor);
        }

        // Search for black line around circumference
        Movement.customForward(myRobot, leftMotor, rightMotor, CORNER_TIME);
        if (!Position.isBlack(myColor)) {
            Movement.searchBlack(leftMotor, rightMotor, myColor);
        }

        // A dot is not a corner if there is black line after it
        if (Position.isBlack(myColor)) {
            isCorner = false;
        } else {
            Movement.customBackward(myRobot, leftMotor, rightMotor, CORNER_TIME);
            Movement.backwardToDotCentre(leftMotor, rightMotor);
            isCorner = true;
        }

        return isCorner;
    }

    /**
     * Search for the first dot at the current robot position
     * @param    myRobot            Lego Robot Mindstorm EV3
     * @param    leftMotor            Robot's left motor
     * @param    rightMotor        Robot's right motor
     * @param    myColor                Robot's colour sensor
     * @param    colourDetected        The colour of second dot
     */
    public static void searchDot(Robot myRobot, Motor leftMotor, Motor rightMotor,
        ColorSensor myColor, Dot.Colour colourDetected) {
        if (firstSearch) {
            Movement.customBackward(myRobot, leftMotor, rightMotor, SEARCH_BACK_TIME);
        } else {
            Movement.customForward(myRobot, leftMotor, rightMotor, SEARCH_FORWARD_TIME);
        }

        // Search right first
        for (int i = 0; i < NUMBER_OF_SEARCH; i++) {
            if (Dot.colourCheck(myColor) != colourDetected) {
                Movement.customRotateRight(leftMotor, rightMotor, SEARCH_ANGLE);
            } else {
                break;
            }
        }

        // Return to original position (rotate left)
        if (Dot.colourCheck(myColor) != colourDetected) {
            Movement.customRotateLeft(leftMotor, rightMotor, SEARCH_ANGLE * NUMBER_OF_SEARCH);
        }

        // Then search left
        for (int i = 0; i < NUMBER_OF_SEARCH; i++) {
            if (Dot.colourCheck(myColor) != colourDetected) {
                Movement.customRotateLeft(leftMotor, rightMotor, SEARCH_ANGLE);
            } else {
                break;
            }
        }

        // Return to original position (rotate right)
        if (Dot.colourCheck(myColor) != colourDetected) {
            Movement.customRotateRight(leftMotor, rightMotor, SEARCH_ANGLE * NUMBER_OF_SEARCH);
        }
    }
}
