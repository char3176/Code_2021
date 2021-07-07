package frc.robot.constants;

import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.trajectory.constraint.SwerveDriveKinematicsConstraint;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.constants.MasterConstants;

public class DrivetrainConstants {
    // IDs for Drivetrain motors and solenoids

    //CAN IDs
    public static final int DRIVE_ONE_CID = 1;
    public static final int DRIVE_TWO_CID = 2;
    public static final int DRIVE_THREE_CID = 3;
    public static final int DRIVE_FOUR_CID = 4;

    //CAN IDs
    public static final int STEER_ONE_CID = 11;
    public static final int STEER_TWO_CID = 22;
    public static final int STEER_THREE_CID = 33;
    public static final int STEER_FOUR_CID = 44;

    // Drivetrain dimensions for kinematics and odometry
    public static final double LENGTH_IN_INCHES_2019 = 30.5; // Inches
    public static final double LENGTH_IN_METERS_2021 = 0.581; // measured in cm as 58.1cm
    public static final double LENGTH_IN_INCHES_2021 = Units.metersToInches(LENGTH_IN_METERS_2021); //58.1 * (1.0/2.54); // 22.87 inches but measured in cm as 58.1cm

    public static final double WIDTH_IN_INCHES_2019 = 29.5; // Inches
    public static final double WIDTH_IN_METERS_2021 = 0.640; // measured in cm as 64.0cm
    public static final double WIDTH_IN_INCHES_2021 = Units.metersToInches(WIDTH_IN_METERS_2021); //64.0 * (1.0/2.54); // 25.197 inches but measured in cm as 64.0cm

    public static double LENGTH = (MasterConstants.is2021Bot) ?  LENGTH_IN_INCHES_2021 : LENGTH_IN_INCHES_2019;
    public static double WIDTH = (MasterConstants.is2021Bot) ?  WIDTH_IN_INCHES_2021 : WIDTH_IN_INCHES_2019;
    public static double DRIVE_ENCODER_UNITS_PER_REVOLUTION;

    public static final double WHEEL_DIAMETER_INCHES = 3.25; // Inches
    public static final double WHEEL_DIAMETER_METERS = Units.inchesToMeters(WHEEL_DIAMETER_INCHES); // Inches
    public static final double WHEEL_DIAMETER_FEET = WHEEL_DIAMETER_INCHES / 12.0 ; // Inches
    public static final double WHEEL_DIAMETER = WHEEL_DIAMETER_INCHES;

    public static final double MAX_WHEEL_SPEED_INCHES_PER_SECOND = 165.48; // inches/s
    public static final double MAX_WHEEL_SPEED_METERS_PER_SECOND = Units.inchesToMeters(MAX_WHEEL_SPEED_INCHES_PER_SECOND); 
    public static final double MAX_WHEEL_SPEED_FEET_PER_SECOND = MAX_WHEEL_SPEED_INCHES_PER_SECOND / 12.0 ; // 13.5 ft/s
    public static final double MAX_WHEEL_SPEED = MAX_WHEEL_SPEED_INCHES_PER_SECOND;
    
    public static final double MAX_ACCEL_INCHES_PER_SECOND = 60.0; // inches/s*s
    public static final double MAX_ACCEL_METERS_PER_SECOND = Units.inchesToMeters(MAX_ACCEL_INCHES_PER_SECOND); // meters/(s*s)
    public static final double MAX_ACCEL_FEET_PER_SECOND = MAX_ACCEL_INCHES_PER_SECOND / 12.0; // Unknown units - likely ft/(s*s)
    public static final double MAX_ACCEL = MAX_ACCEL_INCHES_PER_SECOND;

    public static final double MAX_ROT_SPEED_RADIANS_PER_SECOND = 5.0; // rad/s
    public static final double MAX_ROT_SPEED = 5.0; // rad/s
    
    public static final double MAX_ROT_ACCELERATION_RADIANS_PER_SECOND_SQUARED = 1; // rad/s*s

    public static final double NON_TURBO_PERCENT_OUT_CAP = /*0.5*/1.0; //TODO: CHANGE BACK and fix for AUTON

    //public static final double LENGTH_CENTER_TO_CENTER = 23.5;
    //public static final double WIDTH_CENTER_TO_CENTER = 23.5;
    public static final SwerveDriveKinematics DRIVE_KINEMATICS = new SwerveDriveKinematics(
        new Translation2d(-Units.inchesToMeters(LENGTH) / 2, Units.inchesToMeters(WIDTH) / 2),  //FR where +x=forward and +y=port
        new Translation2d(Units.inchesToMeters(LENGTH) / 2, Units.inchesToMeters(WIDTH) / 2),   //FL where +x=forward and +y=port
        new Translation2d(-Units.inchesToMeters(LENGTH) / 2, -Units.inchesToMeters(WIDTH) / 2), //BL where +x=forward and +y=port
        new Translation2d(Units.inchesToMeters(LENGTH) / 2, -Units.inchesToMeters(WIDTH) / 2)   //BR where +x=forward and +y=port
    );

    /* NOTE: Related to above decomposition of pod locations where
     *               O (Chassis center) = 0,0,
     *              FR (front right pod) = -x +y, 
     *              FL (front left pod) = +x, +y,
     *              BL (back left pod) = -x, -y, 
     *              BR (back right pod) = +x -y
     *      and +x is considered the forward direction (fore) and +y is 
     *      considered the direction to port on the chassis
     *      IT MUST BE NOTED that with current orientation of NavX-MXP on the 2021 bot
     *      the raw navx gyro/angle values from NavX-MXP give +y = fore and +x = starboard.
     *      THEREFORE, all raw gyro angles retrieved from the NavX-MXP must be 
     *      rotated by 90-degrees such that +x = fore and +y = port.
     * 
     *          Quick primer on directions:
     *                  
     *                                  Forward facing side = "fore" 
     *                              FL-------FR
     *                               |       |
     *           This side = "port"  |   O   |  This side = "starboard" 
     *                               |       |
     *                              BL-------BR
     *                                  Backward direction side = "aft"
     *
     */ 

     // Below line contains offset needed to rotate raw navx angle output such that +x=fore and +y=port
    public static final double GYRO_COORDSYS_ROTATIONAL_OFFSET_2019 = 0;/*Units.radiansToDegrees(Math.PI / 2); */  // Currently equivalent to +90 Degrees 
    public static final double GYRO_COORDSYS_ROTATIONAL_OFFSET_2021 =90; //-90;/*Units.radiansToDegrees(Math.PI / 2); */  // Currently equivalent to +90 Degrees 
    public static final double GYRO_COORDSYS_ROTATIONAL_OFFSET = (MasterConstants.is2021Bot) ? GYRO_COORDSYS_ROTATIONAL_OFFSET_2021 : GYRO_COORDSYS_ROTATIONAL_OFFSET_2019;

    public static final double[] AUTON_THETA_CONTROLLER_PIDF = { 3.0 /*kP*/, 0.0 /*kI*/, 0.0 /*kD*/, 0.0 /*kF*/};

    public static final TrapezoidProfile.Constraints THETA_CONTROLLER_CONSTRAINTS =
        new TrapezoidProfile.Constraints(
            //MAX_ROT_SPEED_RADIANS_PER_SECOND, MAX_ROT_ACCELERATION_RADIANS_PER_SECOND_SQUARED);
            2*Math.PI, 2*Math.PI
        );

    public static final double P_X_Controller = 1;
    public static final double P_Y_Controller = 1;
    public static final double P_Theta_Controller = 1;
    
    //public static final double DEGREES_PER_SECOND_TO_METERS_PER_SECOND_OF_WHEEL = (3.25*Math.PI)/360;
    public static final double DEGREES_PER_SECOND_TO_METERS_PER_SECOND_OF_WHEEL = (WHEEL_DIAMETER_METERS * Math.PI)/360;

    
}
