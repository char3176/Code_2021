// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.constants;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class DrumConstants {

    public static final int drumMotorCANID = 6;

    // Drum PIDF Constants for SparkMAX Neo 550
    public static final double drumKP = 0.00013;
    public static final double drumKI = 0.0000008;
    public static final double drumKD = 0.003;
    public static final double drumKF = 0.000097;
    public static final double drumKIZone = 0; // does this really need to be messed with?
    public static final double drumKRampRate = 4000;    

    public static final double drumKMaxOutput = 1.0;
    public static final double drumKMinOutput = -1.0;

    // RPM for Drum buttons
    // public static final int drumLow = 2500;
    // public static final int drumMedium = 4500;
    // public static final int drumHigh = 7000;
    // public static final int drumExtreme = 8500;
    public static final int[] drumSpeeds = {0, 2500, 4500, 7000, 8500};
}
