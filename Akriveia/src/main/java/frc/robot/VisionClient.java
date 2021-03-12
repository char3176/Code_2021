package frc.robot;

import java.util.Arrays;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.constants.VisionConstants;

/**
 * The VisionClient class is used as a proxy between direct Limelight values and the rest of the Java code.
 * The class also calculates some specific values when used in the context of the 2020/2021 season, such as:
 * -distance from target
 * -horizontal distance from target
 * -vertical distance from target
 * -initial velocity of a ball in order to shoot it into the target
 * -initial angle of the ball in order to shoot it into the target
 */
public class VisionClient{
    // variables to access values given from the Limelight interface
    public NetworkTableInstance tableInstance;
    public NetworkTable limelightTable;
    public static NetworkTableEntry tv;
    public static NetworkTableEntry ty;
    public static NetworkTableEntry tshort;
    public NetworkTableEntry tlong;
    public NetworkTableEntry thor;
    public static NetworkTableEntry tvert;
    public static NetworkTableEntry tcornxy;
    private static NetworkTableEntry tl;

    // separation of tcornxy into two different arrays
    private double[] tcornx = new double[4];
    private double[] tcorny = new double[4];

    private double deltaXCam;
    //private double tcornx0;
    //private double tcornx1;
    private double radius;

    // initializing variables for kinematic calculations
    private final double gravity = -9.81; // m/s^2
    private double deltaX; // m
    private double deltaY; // m
    private double[] initialVelocity = {4.0, 3.0, 2.0}; // m/s
    private double initialTheta; // radians
    private double finalTheta; // radians
    private double xVelocity; // m/s
    private double initialYVelocity; // m/s
    private double finalYVelocity; // m/s
    private double time; // seconds

    /**
     * Creates the default references for VisionClient, specifically for Limelight values
     */
    public VisionClient() {
        tableInstance = NetworkTableInstance.getDefault();
        limelightTable = tableInstance.getTable("limelight");
        tv = limelightTable.getEntry("tv");
        ty = limelightTable.getEntry("ty");
        tshort = limelightTable.getEntry("tshort");
        tlong = limelightTable.getEntry("tlong");
        thor = limelightTable.getEntry("thor");
        tvert = limelightTable.getEntry("tvert");
        tcornxy = limelightTable.getEntry("tcornxy");
        tl = limelightTable.getEntry("tl");
        
        limelightTable.getEntry("pipeline").setNumber(1);
    }

    /**
     * All calculations for everything are done in this method.
     * It is essentially the main method in this class.
     * It calculates the following values:
     * -distance of camera from target
     * -horizontal distance of camera from target
     * -vertical distance of camera from target
     * -initial angle of ball to make it into target
     * -initial velocity of ball to make it into target
     */
    public void test() {
        // used to calculate latency
        double startTime = Timer.getFPGATimestamp();

        SmartDashboard.putBoolean("Has Targets", (tv.getDouble(2) == 1) ? true : false);
        SmartDashboard.putNumber("tshort", tshort.getDouble(0));
        SmartDashboard.putNumber("tvert", tvert.getDouble(0));

        SmartDashboard.putNumber("Length", tcornxy.getDoubleArray(new double[1]).length);

        if(tcornxy.getDoubleArray(new double[1]).length != 8){
            return;
        }

        // separate the larger tcornxy arrays into the easier to understand x and y arrays
        int j = 0;
        for(int i = 0; i < 8; i++){
            if(i % 2 == 0){
                tcornx[j] = tcornxy.getDoubleArray(new double[1])[i];
            } else{
                tcorny[j] = tcornxy.getDoubleArray(new double[1])[i];
                j++;
            }
        }

        // calculate the distance between the furthest two points as the camera sees it
        deltaXCam = calculateDeltaX(tcornx);

        SmartDashboard.putNumber("DeltaXCam", deltaXCam);

        // calculate the various kinds of distances from the camera
        radius = VisionConstants.VISION_CONSTANT / deltaXCam;
        deltaX = radius * Math.cos(ty.getDouble(0) * VisionConstants.DEG2RAD);
        deltaY = radius * Math.sin(ty.getDouble(0) * VisionConstants.DEG2RAD);

        SmartDashboard.putNumber("Radius", radius);
        SmartDashboard.putNumber("deltaX", deltaX);
        SmartDashboard.putNumber("deltaY", deltaY);
        
        // get the initial velocity and angle of ball
        double[] resultArray = findInitialValues(0);

        SmartDashboard.putNumber("Latency (ms)", ((Timer.getFPGATimestamp() - startTime) * 1000) + tl.getDouble(0) + 11);
    }

    /**
     * Calculates the difference in the two points furthest away from each other
     * 
     * @param array array to find the range of
     * @return double that represents the range of the array
     */
    public double calculateDeltaX(double[] array){
        Arrays.sort(array);
        return array[array.length - 1] - array[0];
    }

    /**
     * Calculates the initial angle and velocity of the ball using kinematic equations
     * 
     * @param speedIdx The index in the possible speed array (above) for the method to start at. It is reccommended to default to 0.
     * @return A double array where the first value is the velocity, and the second value is the angle (in degrees).
     */
    public double[] findInitialValues(int speedIdx){
        // ths single line that calculates the initial angle
        initialTheta = Math.atan((-deltaX + Math.sqrt(Math.pow(deltaX, 2) - 4 * ((gravity * deltaX) / (2 * Math.pow(initialVelocity[speedIdx], 2))) * (((gravity * deltaX) / (2 * Math.pow(initialVelocity[speedIdx], 2))) - deltaY))) / ((gravity * deltaX) / Math.pow(initialVelocity[speedIdx], 2)));

        SmartDashboard.putNumber("initialTheta", initialTheta);

        // this section calculates the angle that the ball would approach the target to see if it would actually go in
        xVelocity = Math.cos(initialTheta);
        initialYVelocity = Math.sin(initialTheta);

        time = ((-initialYVelocity - Math.sqrt(Math.pow(initialYVelocity, 2) - 4 * (.5 * gravity) * (-1 * deltaY))) / (gravity));

        finalYVelocity = initialYVelocity + (gravity * time);

        finalTheta = Math.atan(finalYVelocity / xVelocity) + Math.PI;

        // figures out if the solution is valid by checking if it would actually go into the target
        if(finalTheta >= (11 * Math.PI)/12 && finalTheta <= (13 * Math.PI)/12){
            double[] arrayToSend = {initialVelocity[speedIdx], initialTheta / VisionConstants.DEG2RAD};
            return arrayToSend;
        } else{
            if(speedIdx + 1 < initialVelocity.length){
                return findInitialValues(speedIdx + 1);
            } else{
                return null;
            }
        }
    }
}