package frc.robot.constants;

public class DrumConstants {
    public static final int drumMotorCANID = 50;

    /* Drum PIDF Constants for SparkMAX Neo 550 */

    public static final double drumKP = 0.00013;
    public static final double drumKI = 0.0000008;
    public static final double drumKD = 0.003;
    public static final double drumKF = 0.000097;
    public static final double drumKIZone = 0; 
    public static final double drumKRampRate = 2500;    

    public static final double drumKMaxOutput = 1.0;
    public static final double drumKMinOutput = -1.0;

    /* RPM Array */

    public static final int[] drumSpeeds = {0, 2500, 4500, 7000, 8500};
    public static final double drumShakePct = 0.35;
    public static final double drumMilli = 1000000;
    public static final double drumSec = 1000000000;
}