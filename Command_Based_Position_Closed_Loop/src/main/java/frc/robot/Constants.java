/**
 * Simple class containing constants used throughout project
 */
package frc.robot;

public class Constants {

	public static final int angledShooterCANID = 44;

	/**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */
	public static final int angledShooterSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */
	public static final int angledShooterPIDLoopIdx = 0;

	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */
	public static final int angledShooterTimeoutMs = 30;
	
	/* Choose so that Talon does not report sensor out of phase */
	public static boolean angledShooterSensorPhase = true;

	/**
	 * Choose based on what direction you want to be positive,
	 * this does not affect motor invert. 
	 */
	public static boolean angledShooterMotorInvert = false;

	/**
	 * Gains used in Positon Closed Loop, to be adjusted accordingly
     * Gains(kp, ki, kd, kf, izone, peak output);
     *///

	public static final double angledShooter_kP = 0.31; //1 As a test if needed
	public static final double angledShooter_kI = 0.00075;
	public static final double angledShooter_kD = 1.12;
	public static final double angledShooter_kF = 0.0;
	public static final int angledShooter_iZone = 0;
	public static final double angledShooter_PeakOutput = 1.0;

 	// public static final Gains kGains = new Gains(0.31, 0.00075, 1.12, 0.0, 0, 1.0);
	//public static final Gains kGains = new Gains(1, 0.00075, 1.12, 0.0, 0, 1.0);

	public static final double kGearRatio = 4/1;
	public static final double k5Degrees = 570*kGearRatio;
	public static final double kMaxDegrees = 3413*kGearRatio;
	public static final double kSecondMax = 2830*kGearRatio  ;
}
