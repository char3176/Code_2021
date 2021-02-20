package frc.robot;

public final class Constants {

    /* Controller */

    public static final int xbControllerPort = 2;

    /* Flywheel */

    public static final double Flywheel_kP = 0.14;
    public static final double Flywheel_kI = 0.0;
    public static final double Flywheel_kD = 1.5;
    public static final double Flywheel_kF = 0.0;
    public static final int Flywheel_kIzone = 0;
    public static final double Flywheel_kPeakOutput = 1.0;
    public static final int Flywheel_kTimeoutMs = 30;
    public static final int Flywheel_kPIDLoopIdx = 0;

    /* Angled Shooter */

	public static final int angledShooterCANID = 44;
	public static final int angledShooterSlotIdx = 0;
	public static final int angledShooterPIDLoopIdx = 0;
	public static final int angledShooterTimeoutMs = 30;
	public static boolean angledShooterSensorPhase = true;
    public static boolean angledShooterMotorInvert = false;
    
	public static final double angledShooter_kP = 0.31;
	public static final double angledShooter_kI = 0.00075;
	public static final double angledShooter_kD = 1.12;
	public static final double angledShooter_kF = 0.0;
	public static final int angledShooter_iZone = 0;
	public static final double angledShooter_PeakOutput = 1.0;

	public static final double k5Degrees = 570;
	public static final double kMaxDegrees = 3410;
	public static final double kSecondMax = 2840;
}