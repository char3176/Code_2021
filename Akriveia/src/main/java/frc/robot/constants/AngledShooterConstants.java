package frc.robot.constants;

public class AngledShooterConstants {
    public static final int angledShooterCANID = 60;
	public static final int angledShooterSlotIdx = 0;
    public static final int angledShooterPIDLoopIdx = 0;
    public static final int angledShooterTimeoutMs = 30;
    public static boolean angledShooterSensorPhase = true;
    public static boolean angledShooterMotorInvert = false;
    
    public static final double[] pid = {/*P*/.31,/*I*/0.002,/*D*/2.12,/*F*/0.35};

    public static final double GEAR_RATIO = 4/1;
	
	public static final double MAX_DEGREES_IN_TIC_UNITS = 341.3 * GEAR_RATIO;
	public static final double TICS_EQUAL_TO_5DEGREES = (MAX_DEGREES_IN_TIC_UNITS / 6 );

    public static final double MIN_TICS = -3822;
    public static final double MAX_TICS = -2461 ;
    public static final double RANGE = (MAX_TICS-MIN_TICS);

    public static final double[] pos = {MIN_TICS, MIN_TICS + (RANGE*.25), MIN_TICS+ (RANGE*.5), MIN_TICS + (RANGE*.75), MAX_TICS};
}