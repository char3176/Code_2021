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

    public static final double kP = 4e-3; // Default 6e-5
    public static final double kI = 0.2; // Default 0
    public static final double kD = 1.5; // Default 0
    public static final double kF = 0.0015; // Default 0.000015
    public static final int kIzone = 0; // Default 0
    public static final double kMaxOutput = 0.25; // Default 1.0
    public static final double kMinOutput = -0.25; // Default -1.0
    // public static final int kMaxRPM = 5700; // Default 5700
    
    // public static final int kTimeoutMs = 30; // Default 30 (I think)
    // public static final int kPIDLoopIdx = 0; // Default 0

    public static final int SparkMaxCAN = 6;

}