/**
 * COM 1003 Assignment 3 A class for robot's movement
 *
 * @author Zer Jun Eng
 */

import ShefRobot.*;

public class Movement {

    // Constants for turning, and moving in a colour dot
    private static final int TURN_ANGLE = 370; // 90 degrees

    ;
    private static final int FORWARD_CENTER = 155;
    private static final int BACKWARD_CENTER = 230;
    // Variables for searchBlack method
    private static final int NUMBER_OF_SEARCH = 5;
    private static final int SEARCH_ANGLE = 20;
    // Variables for colour error checking
    private static final int ERROR_TIME = 200;
    // Constants for singing method
    private static final int TONE_DURATION = 250;
    private static final int REPEAT = 2;
    private static final int LOW_TONE = 800;
    private static final int MEDIUM_TONE = 1000;
    private static final int HIGH_TONE = 1200;
    // Constatnts for dancing method
    private static final int SPIN_ANGLE = 1480;
    private static boolean turnedLeft = false;
    // Set the initial starting direction as North
    private static Direction currentDirection = Direction.NORTH;

    /**
     * Stop moving
     * @param     leftMotor    Robot's left motor
     * @param     rightMotor   Robot's right motor
     */
    public static void stop(Motor leftMotor, Motor rightMotor) {
        leftMotor.stop();
        rightMotor.stop();
    }

    /**
     * Move forward by rotating the motor for 1 complete revolution
     * @param     leftMotor    Robot's left motor
     * @param     rightMotor   Robot's right motor
     */
    public static void forward(Motor leftMotor, Motor rightMotor) {
        leftMotor.forward();
        rightMotor.forward();
    }

    /**
     * Move forward for a specific time
     * @param    myRobot     Lego Robot Mindstorm EV3
     * @param    leftMotor    Robot's left motor
     * @param    rightMotor   Robot's right motor
     * @param    time         Move for a specific time
     */
    public static void customForward(Robot myRobot, Motor leftMotor, Motor rightMotor, int time) {
        Movement.forward(leftMotor, rightMotor);
        myRobot.sleep(time);
        Movement.stop(leftMotor, rightMotor);
    }

    /**
     * Move backward by rotating the motor for 1 complete revolution
     * @param     leftMotor    Robot's left motor
     * @param     rightMotor   Robot's right motor
     */
    public static void backward(Motor leftMotor, Motor rightMotor) {
        leftMotor.backward();
        rightMotor.backward();
    }

    /**
     * Move backward for a specific time
     * @param    myRobot     Lego Robot Mindstorm EV3
     * @param    leftMotor    Robot's left motor
     * @param    rightMotor   Robot's right motor
     * @param    time         Move for a specific time
     */
    public static void customBackward(Robot myRobot, Motor leftMotor, Motor rightMotor, int time) {
        Movement.backward(leftMotor, rightMotor);
        myRobot.sleep(time);
        Movement.stop(leftMotor, rightMotor);
    }

    /**
     * Do a left rotation from the angle provided as parameter
     * @param    leftMotor    Robot's left motor
     * @param    rightMotor   Robot's right motor
     * @param    angle         Angle to rotate
     */
    public static void customRotateLeft(Motor leftMotor, Motor rightMotor, int angle) {
        leftMotor.rotate(-angle, true);
        rightMotor.rotate(angle);
    }

    /**
     * Do a right rotation from the angle provided as parameter
     * @param    leftMotor    Robot's left motor
     * @param    rightMotor   Robot's right motor
     * @param    angle         Angle to rotate
     */
    public static void customRotateRight(Motor leftMotor, Motor rightMotor, int angle) {
        leftMotor.rotate(angle, true);
        rightMotor.rotate(-angle);
    }

    /**
     * Turn left 90 degrees
     * @param     leftMotor    Robot's left motor
     * @param     rightMotor   Robot's right motor
     */
    public static void turnLeft(Motor leftMotor, Motor rightMotor) {
        leftMotor.stop();
        rightMotor.rotate(TURN_ANGLE);

        // Changes the direction when it turns left
        switch (currentDirection) {
            case NORTH:
                currentDirection = Direction.WEST;
                break;
            case EAST:
                currentDirection = Direction.NORTH;
                break;
            case SOUTH:
                currentDirection = Direction.EAST;
                break;
            case WEST:
                currentDirection = Direction.SOUTH;
                break;
        }
    }

    /**
     * Turn right 90 degrees
     * @param     leftMotor    Robot's left motor
     * @param     rightMotor   Robot's right motor
     */
    public static void turnRight(Motor leftMotor, Motor rightMotor) {
        rightMotor.stop();
        leftMotor.rotate(TURN_ANGLE);

        // Changes the direction when it turns right
        switch (currentDirection) {
            case NORTH:
                currentDirection = Direction.EAST;
                break;
            case EAST:
                currentDirection = Direction.SOUTH;
                break;
            case SOUTH:
                currentDirection = Direction.WEST;
                break;
            case WEST:
                currentDirection = Direction.NORTH;
                break;
        }
    }

    /**
     * Get the current direction of the robot
     * @return Direction     The current robot direciton
     */
    public static Direction getCurrentDirection() {
        return currentDirection;
    }

    /**
     * Search for the black line when it fails to detect it
     * @param    leftMotor    Robot's left motor
     * @param    rightMotor   Robot's right motor
     * @param    myColor         Robot's colour sensor
     */
    public static void searchBlack(Motor leftMotor, Motor rightMotor, ColorSensor myColor) {
        if (turnedLeft) {
            // Search right first
            for (int i = 0; i < NUMBER_OF_SEARCH; i++) {
                if (!Position.isBlack(myColor)) {
                    Movement.customRotateRight(leftMotor, rightMotor, SEARCH_ANGLE);
                } else {
                    turnedLeft = false;
                    break;
                }
            }

            // Return to original position (rotate left)
            if (!Position.isBlack(myColor)) {
                Movement.customRotateLeft(leftMotor, rightMotor, SEARCH_ANGLE * NUMBER_OF_SEARCH);
            }

            // Then search left
            for (int i = 0; i < NUMBER_OF_SEARCH; i++) {
                if (!Position.isBlack(myColor)) {
                    Movement.customRotateLeft(leftMotor, rightMotor, SEARCH_ANGLE);
                } else {
                    turnedLeft = true;
                    break;
                }
            }

            // Return to original position (rotate right)
            if (!Position.isBlack(myColor)) {
                Movement.customRotateRight(leftMotor, rightMotor, SEARCH_ANGLE * NUMBER_OF_SEARCH);
            }
        } else {
            // Search left first
            for (int i = 0; i < NUMBER_OF_SEARCH; i++) {
                if (!Position.isBlack(myColor)) {
                    Movement.customRotateLeft(leftMotor, rightMotor, SEARCH_ANGLE);
                } else {
                    turnedLeft = true;
                    break;
                }
            }

            // Return to original position (rotate right)
            if (!Position.isBlack(myColor)) {
                Movement.customRotateRight(leftMotor, rightMotor, SEARCH_ANGLE * NUMBER_OF_SEARCH);
            }

            // Then search right
            for (int i = 0; i < NUMBER_OF_SEARCH; i++) {
                if (!Position.isBlack(myColor)) {
                    Movement.customRotateRight(leftMotor, rightMotor, SEARCH_ANGLE);
                } else {
                    turnedLeft = false;
                    break;
                }
            }

            // Return to original position (rotate left)
            if (!Position.isBlack(myColor)) {
                Movement.customRotateLeft(leftMotor, rightMotor, SEARCH_ANGLE * NUMBER_OF_SEARCH);
            }
        }
    }

    /**
     * Perform error check for colour
     * @return boolean       True if the colour sensor detects colour
     * @param     myRobot       Lego Robot Mindstorm EV3
     * @param     leftMotor     Robot's left motor
     * @param     rightMotor    Robot's right motor
     * @param     myColor       Robot's colour sensor
     */
    public static boolean colourErrorCheck(Robot myRobot, Motor leftMotor, Motor rightMotor,
        ColorSensor myColor) {
        Dot.Colour tempColour = Dot.colourCheck(myColor);

        Movement.forward(leftMotor, rightMotor);
        myRobot.sleep(ERROR_TIME);
        Movement.stop(leftMotor, rightMotor);

        if (Dot.colourCheck(myColor) == tempColour) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Move forward to the dot's centre
     * @param     leftMotor    Robot's left motor
     * @param     rightMotor   Robot's right motor
     */
    public static void forwardToDotCentre(Motor leftMotor, Motor rightMotor) {
        leftMotor.rotate(FORWARD_CENTER, true);
        rightMotor.rotate(FORWARD_CENTER);
    }

    /**
     * Move backward to the dot's centre
     * @param     leftMotor    Robot's left motor
     * @param     rightMotor   Robot's right motor
     */
    public static void backwardToDotCentre(Motor leftMotor, Motor rightMotor) {
        leftMotor.rotate(-BACKWARD_CENTER, true);
        rightMotor.rotate(-BACKWARD_CENTER);
    }

    /**
     * Sing JingleBell when the robot encounters the second same colour
     * @param     myRobot     Lego Robot Mindstorm EV3
     * @param     speaker     Robot's speaker
     */
    public static void sing(Robot myRobot, Speaker speaker) {
        for (int i = 0; i < REPEAT; i++) {
            speaker.playTone(LOW_TONE, TONE_DURATION);
            speaker.playTone(MEDIUM_TONE, TONE_DURATION);
            speaker.playTone(HIGH_TONE, TONE_DURATION);

            speaker.playTone(HIGH_TONE, TONE_DURATION);
            speaker.playTone(MEDIUM_TONE, TONE_DURATION);
            speaker.playTone(LOW_TONE, TONE_DURATION);
        }
    }

    /**
     * Ending dance (Spinning)
     * @param     leftMotor    Robot's left motor
     * @param     rightMotor   Robot's right motor
     */
    public static void dance(Motor leftMotor, Motor rightMotor) {
        leftMotor.rotate(SPIN_ANGLE, true);
        rightMotor.rotate(-SPIN_ANGLE);
    }

    // Virtual Direction system
    public enum Direction {
        NORTH, EAST, SOUTH, WEST
    }
}
