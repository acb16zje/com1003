/**
 * COM 1003 Assignment 3 A class for the colour dots
 *
 * @author Zer Jun Eng
 */

import ShefRobot.*;

public class Dot {

    // One motor needs to turn 370 degrees for the robot to turn a 90 degrees
    private static final double ANGLE_MULTIPLER = 370 / 90;

    ;
    private static final int RIGHT_ANGLE = 370;
    private static final int ANGLE_ERROR = 88;
    // Variables used for colour counter
    private static int redCounter = 0;
    private static int greenCounter = 0;
    private static int blueCounter = 0;
    private static int yellowCounter = 0;
    private static boolean hasMetSameColour = false;
    // Instance variable
    private Colour colour;
    private int xCoordinate;
    private int yCoordinate;
    /**
     * Constructs a new dot from the parameters provided
     * @param c        Colour 		Its colour
     * @param xCoor    int 		Its x-coordinate
     * @param yCoor    int 		Its y-coordinate
     */
    public Dot(Colour c, int xCoor, int yCoor) {
        this.colour = c;
        this.xCoordinate = xCoor;
        this.yCoordinate = yCoor;
    }

    /**
     * Reset the robot coordinate to (0, 0)
     * @param     leftMotor    Robot's left motor
     * @param     rightMotor   Robot's right motor
     */
    public static void resetCoordinate(Motor leftMotor, Motor rightMotor) {
        leftMotor.resetTachoCount();
        rightMotor.resetTachoCount();
    }

    // Accessors

    /**
     * Get the distance it moved from the last coordinate
     * @return int         The current robot coordinate
     * @param    leftMotor    Robot's left motor
     * @param    rightMotor   Robot's right motor
     */
    public static int getCurrentCoordinate(Motor leftMotor, Motor rightMotor) {
        // Get the average tacho count of left and right motor
        int firstPos = leftMotor.getTachoCount();
        int secondPos = rightMotor.getTachoCount();
        int accuratePos = (firstPos + secondPos) / 2;

        return accuratePos;
    }

    /**
     * Calculate the distance betweeen two same colour dots
     * @return int         The distance between two dots
     * @param    firstDot     First same dot
     * @param    secondDot    Second same dot
     */
    public static int distanceBetweenTwoDots(Dot firstDot, Dot secondDot) {
        int x1 = firstDot.getXCoordinate();
        int x2 = secondDot.getXCoordinate();
        int y1 = firstDot.getYCoordinate();
        int y2 = secondDot.getYCoordinate();

        int distance = (int) Math.round(Math.hypot(x2 - x1, y2 - y1));

        return distance;
    }

    /**
     * Calculate the angle and rotate to the first dot direction
     * @param    leftMotor    Robot's left motor
     * @param    rightMotor   Robot's right motor
     * @param    firstDot     First same dot
     * @param    secondDot    Second same dot
     */
    public static void angleToRotate(Motor leftMotor, Motor rightMotor, Dot firstDot,
        Dot secondDot) {
        int x1 = firstDot.getXCoordinate();
        int x2 = secondDot.getXCoordinate();
        int y1 = firstDot.getYCoordinate();
        int y2 = secondDot.getYCoordinate();
        int actualAngle = 0;

        // Changes in X and Y
        int deltaX = x2 - x1;
        int deltaY = y2 - y1;

        // Second dot is on a same line with the first dot
        if (deltaX == 0 || deltaY == 0) {
            actualAngle = (int) Math.round(180 * ANGLE_MULTIPLER);
        }
        // Second dot is in north-east direction (First quadrant)
        else if (deltaX > 0 && deltaY > 0) {
            actualAngle = (int) (Math.toDegrees(Math.atan(deltaX / deltaY)) * ANGLE_MULTIPLER);
            if (Movement.getCurrentDirection() == Movement.Direction.SOUTH) {
                leftMotor.rotate(actualAngle);
                rightMotor.stop();
            } else if (Movement.getCurrentDirection() == Movement.Direction.EAST) {
                leftMotor.rotate(actualAngle + RIGHT_ANGLE + ANGLE_ERROR);
                rightMotor.stop();
            }
        }
        // Second dot is in north-west direction (Second quadrant)
        else if (deltaX < 0 && deltaY > 0) {
            actualAngle = (int) (Math.toDegrees(Math.atan(deltaY / -deltaX)) * ANGLE_MULTIPLER);
            if (Movement.getCurrentDirection() == Movement.Direction.NORTH) {
                leftMotor.rotate(actualAngle + RIGHT_ANGLE + ANGLE_ERROR);
                rightMotor.stop();
            } else if (Movement.getCurrentDirection() == Movement.Direction.EAST) {
                leftMotor.rotate(actualAngle);
                rightMotor.stop();
            }
        }
        // Second dot is in south-west direction (Third quadrant)
        else if (deltaX < 0 && deltaY < 0) {
            actualAngle = (int) (Math.toDegrees(Math.atan(deltaX / deltaY)) * ANGLE_MULTIPLER);
            if (Movement.getCurrentDirection() == Movement.Direction.NORTH) {
                leftMotor.rotate(actualAngle);
                rightMotor.stop();
            } else if (Movement.getCurrentDirection() == Movement.Direction.WEST) {
                leftMotor.rotate(actualAngle + RIGHT_ANGLE + ANGLE_ERROR);
                rightMotor.stop();
            }
        }
        // Second dot is in south-east direction (Fourth quadrant)
        else if (deltaX > 0 && deltaY < 0) {
            actualAngle = (int) (Math.toDegrees(Math.atan(-deltaY / deltaX)) * ANGLE_MULTIPLER);
            if (Movement.getCurrentDirection() == Movement.Direction.SOUTH) {
                leftMotor.rotate(actualAngle + RIGHT_ANGLE + ANGLE_ERROR);
                rightMotor.stop();
            } else if (Movement.getCurrentDirection() == Movement.Direction.WEST) {
                leftMotor.rotate(actualAngle);
                rightMotor.stop();
            }
        }
    }

    /**
     * Count the number of same colour that is detected(red, green, blue, yellow)
     * @param    myColor        Robot's colour sensor
     * @return Colour        The dot's colour
     */
    public static Colour whichColour(ColorSensor myColor) {
        switch (myColor.getColor()) {
            case RED:
                redCounter++;
                return Colour.RED;
            case GREEN:
                greenCounter++;
                return Colour.GREEN;
            case BLUE:
                blueCounter++;
                return Colour.BLUE;
            case YELLOW:
                yellowCounter++;
                return Colour.YELLOW;
            default:
                return Colour.NONE;
        }
    }

    /**
     * Colour checking method for colourErrorCheck(red, green, blue, yellow)
     * @param    myColor        Robot's colour sensor
     * @return Colour        The dot's colour
     */
    public static Colour colourCheck(ColorSensor myColor) {
        switch (myColor.getColor()) {
            case RED:
                return Dot.Colour.RED;
            case GREEN:
                return Dot.Colour.GREEN;
            case BLUE:
                return Dot.Colour.BLUE;
            case YELLOW:
                return Dot.Colour.YELLOW;
            default:
                return Dot.Colour.NONE;
        }
    }

    /**
     * Check if the robot has met the same colour twice
     * @return boolean    True if the robot has met the same colour twice
     */
    public static boolean hasMetSameColour() {
        if (redCounter == 2 || greenCounter == 2 || blueCounter == 2 || yellowCounter == 2) {
            hasMetSameColour = true;
        } else {
            hasMetSameColour = false;
        }

        return hasMetSameColour;
    }

    /**
     * Finds the colour of the dot
     * @return Colour        Its colour
     */
    public Colour getColour() {
        return this.colour;
    }

    /**
     * Finds the x-coordinate of the Dot
     * @return int        Its x-coordinate
     */
    public int getXCoordinate() {
        return this.xCoordinate;
    }

    /**
     * Finds the y-coordinate of the Dot
     * @return int        Its y-coordinate
     */
    public int getYCoordinate() {
        return this.yCoordinate;
    }

    public enum Colour {RED, GREEN, BLUE, YELLOW, NONE}
}
