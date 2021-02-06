// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int XBoxControllerPort = 2;

    public static final int drumMotorCANID = 6;

    // Drum PIDF constants
    public static final double drumKP = 0.000121;
    public static final double drumKI = 0.0000024;
    public static final double drumKD = 0.00272;
    public static final double drumKF = 0.000169;
    public static final double drumKIZone = 48;

    // Output limits, ramp rate used with SlewRateLimiter
    public static final double drumKMaxOutput = 1.0;
    public static final double drumKMinOutput = -1.0;
    public static final double drumKRampRate = 4500;

}
