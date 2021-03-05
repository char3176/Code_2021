package frc.robot;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionClient{
    public NetworkTableInstance tableInstance;
    public NetworkTable limelightTable;
    public static NetworkTableEntry tv;
    public static NetworkTableEntry ty;
    public static NetworkTableEntry tshort;
    public NetworkTableEntry tlong;
    public NetworkTableEntry thor;
    public static NetworkTableEntry tvert;
    public static NetworkTableEntry tcornx;
    private static NetworkTableEntry tl;

    private double deltaXCam;
    private double tcornx0;
    private double tcornx1;
    private double radius;

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

    public VisionClient() {
        tableInstance = NetworkTableInstance.getDefault();
        limelightTable = tableInstance.getTable("limelight");
        tv = limelightTable.getEntry("tv");
        ty = limelightTable.getEntry("ty");
        tshort = limelightTable.getEntry("tshort");
        tlong = limelightTable.getEntry("tlong");
        thor = limelightTable.getEntry("thor");
        tvert = limelightTable.getEntry("tvert");
        tcornx = limelightTable.getEntry("tcornxy");
        tl = limelightTable.getEntry("tl");
        
        limelightTable.getEntry("pipeline").setNumber(1);
    }

    /*public void time(){
        double startTime = Timer.getFPGATimestamp();
        SmartDashboard.putNumber("startTime", startTime);

        try{
            tcornx0 = tcornx.getDoubleArray(new double[1])[0];
            tcornx1 = tcornx.getDoubleArray(new double[2])[1];
        } catch(Exception e){
            tcornx0 = 10;
            tcornx1 = 20;
        }

        deltaXCam = tcornx1 - tcornx0;

        radius = Constants.VISION_CONSTANT * (Constants.DELTA_X_REAL / deltaXCam);
        deltaX = radius * Math.cos(ty.getDouble(0) * Constants.DEG2RAD);
        deltaY = radius * Math.sin(ty.getDouble(0) * Constants.DEG2RAD);

        int speedIdx = 0;
        initialTheta = Math.atan((-deltaX + Math.sqrt(Math.pow(deltaX, 2) - 4 * ((gravity * deltaX) / (2 * Math.pow(initialVelocity[speedIdx], 2))) * (((gravity * deltaX) / (2 * Math.pow(initialVelocity[speedIdx], 2))) - deltaY))) / ((gravity * deltaX) / Math.pow(initialVelocity[speedIdx], 2)));
        
        SmartDashboard.putNumber("intialTheta", initialTheta);

        SmartDashboard.putNumber("tl", tl.getDouble(0));
        SmartDashboard.putNumber("Difference", ((Timer.getFPGATimestamp() - startTime) * 1000) + tl.getDouble(0) + 11);
    }*/

    public void test() {
        double startTime = Timer.getFPGATimestamp();

        SmartDashboard.putBoolean("Has Targets", (tv.getDouble(2) == 1) ? true : false);
        SmartDashboard.putNumber("tshort", tshort.getDouble(0));
        SmartDashboard.putNumber("tvert", tvert.getDouble(0));

        SmartDashboard.putNumber("Length", tcornx.getDoubleArray(new double[1]).length);

        try{
            tcornx0 = tcornx.getDoubleArray(new double[1])[0];
            tcornx1 = tcornx.getDoubleArray(new double[2])[1];
        } catch(Exception e){
            tcornx0 = 10;
            tcornx1 = 20;
        }

        deltaXCam = tcornx1 - tcornx0;

        SmartDashboard.putNumber("DeltaXCam", deltaXCam);

        radius = Constants.VISION_CONSTANT * (Constants.DELTA_X_REAL / deltaXCam);
        deltaX = radius * Math.cos(ty.getDouble(0) * Constants.DEG2RAD);
        deltaY = radius * Math.sin(ty.getDouble(0) * Constants.DEG2RAD);
        
        double k = (Constants.DELTA_X_REAL * deltaX) / (deltaXCam * Math.cos(ty.getDouble(0) * Constants.DEG2RAD));

        System.out.println(ty.getDouble(0));

        double testRadius = k * (Constants.DELTA_X_REAL / deltaXCam);

        SmartDashboard.putNumber("Radius", radius);
        SmartDashboard.putNumber("deltaX", deltaX);
        SmartDashboard.putNumber("deltaY", deltaY);

        SmartDashboard.putNumber("Test Radius", testRadius);

        SmartDashboard.putNumber("k", k);

    
        //double[] resultArray = findInitialValues(0);

        SmartDashboard.putNumber("Latency (ms)", ((Timer.getFPGATimestamp() - startTime) * 1000) + tl.getDouble(0) + 11);
    }

    public double[] findInitialValues(int speedIdx){
        initialTheta = Math.atan((-deltaX + Math.sqrt(Math.pow(deltaX, 2) - 4 * ((gravity * deltaX) / (2 * Math.pow(initialVelocity[speedIdx], 2))) * (((gravity * deltaX) / (2 * Math.pow(initialVelocity[speedIdx], 2))) - deltaY))) / ((gravity * deltaX) / Math.pow(initialVelocity[speedIdx], 2)));

        SmartDashboard.putNumber("initialTheta", initialTheta);

        xVelocity = Math.cos(initialTheta);
        initialYVelocity = Math.sin(initialTheta);

        time = ((-initialYVelocity - Math.sqrt(Math.pow(initialYVelocity, 2) - 4 * (.5 * gravity) * (-1 * deltaY))) / (gravity));

        finalYVelocity = initialYVelocity + (gravity * time);

        finalTheta = Math.atan(finalYVelocity / xVelocity) + Math.PI;

        if(finalTheta >= (11 * Math.PI)/12 && finalTheta <= (13 * Math.PI)/12){
            double[] arrayToSend = {initialVelocity[speedIdx], initialTheta};
            return arrayToSend;
        } else{
            if(speedIdx + 1 < initialVelocity.length){
                return findInitialValues(speedIdx + 1);
            } else{
                return null;
            }
        }
    }

    enum VisionState{
        OFF,
        TRACKING,
        LOCKED
    }
}
