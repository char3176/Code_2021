package frc.robot.util;

import java.util.Scanner;
import frc.robot.constants.VisionConstants;

public class VisionCalc {
    private static Scanner input = new Scanner(System.in);
    private static double distance; // m
    private static double[] possInitialVelocity = {7089, 6734, 6380, 6025, 5671, 5317, 4962, 4608, 4253, 3899, 3545};
    private static double initialAngle; // radians
    private static double actualInitialVelocity;
    private static final double gravity = -9.81;
    private static final double deltaY = (8.1875 * VisionConstants.FEET2METER) - (1.833333 * VisionConstants.FEET2METER);

    public static void execute(){
        System.out.println("Distance Along Ground From Target (ft): ");
        distance = Integer.parseInt(input.nextLine()) * VisionConstants.FEET2METER;

        calcInitialAngleAndVelocity(0);

        System.out.println("Initial Angle (deg): " + ((initialAngle != -999) ? (initialAngle / VisionConstants.DEG2RAD) : "undefined"));
        System.out.println("Initial Velocity (rpm): " + ((actualInitialVelocity != -999) ? actualInitialVelocity : "undefined"));
    }

    private static void calcInitialAngleAndVelocity(int speedIdx){
        double speed = possInitialVelocity[speedIdx] * VisionConstants.RPM2MPS;
        initialAngle = Math.atan((-distance + Math.sqrt(Math.pow(distance, 2) - 4 * ((gravity * distance) / (2 * Math.pow(speed, 2))) * (((gravity * distance) / (2 * Math.pow(speed, 2))) - deltaY))) / ((gravity * distance) / Math.pow(speed, 2)));
        
        if(!(initialAngle >= 0 && initialAngle <= (29.2 * VisionConstants.DEG2RAD))){
            if(speedIdx >= possInitialVelocity.length - 1){
                initialAngle = -999;
                actualInitialVelocity = -999;
            } else{
                calcInitialAngleAndVelocity(speedIdx++);
            }
        } else{
            actualInitialVelocity = possInitialVelocity[speedIdx];
        }
    }
}
