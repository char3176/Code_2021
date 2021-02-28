package frc.robot.constants;

public class DrumConstants {
    public static final int drumMotorCANID = 6;

    public static final double drumKP = 0.00013;
    public static final double drumKI = 0.0000008;
    public static final double drumKD = 0.003;
    public static final double drumKF = 0.000097;
    public static final double drumKIZone = 0; 
    public static final double drumKRampRate = 2500;    

    public static final double drumKMaxOutput = 1.0;
    public static final double drumKMinOutput = -1.0;

    // RPM for Drum buttons
    public static final int drumLow = 1500;
    public static final int drumMedium = 3500;
    public static final int drumHigh = 6000;
    public static final int drumExtreme = 8500;   
}