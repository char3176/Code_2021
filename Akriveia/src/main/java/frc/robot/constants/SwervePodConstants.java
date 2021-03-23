package frc.robot.constants;

public final class SwervePodConstants {

    // private static final double WHEEL_DIAMETER = DrivetrainConstants.WHEEL_DIAMETER;  // in inches
    // private static final double SPIN_GEAR_RATIO = 70.0 / 1.0; // Is the Versa gearbox btwn motor & encoder
    // private static final double DRIVE_GEAR_RATIO = (54.0 / 14.0) * (48.0 / 30.0);  // 216/35?

    /**
	 * Which PID slot to pull gains from. Starting 2018, you can choose from
	 * 0,1,2 or 3. Only the first two (0,1) are visible in web-based
	 * configuration.
	 */

	public static final int kSlotIdx = 0;

	/**
	 * Talon SRX/ Victor SPX will supported multiple (cascaded) PID loops. For
	 * now we just want the primary one.
	 */

	public static final int kPIDLoopIdx = 0;

	/**
	 * Set to zero to skip waiting for confirmation, set to nonzero to wait and
	 * report to DS if action fails.
	 */

	public static final int kTimeoutMs = 0;
	
    /* Choose so that Talon does not report sensor out of phase */
    
	public static boolean kSensorPhase = false;

	/**
	 * Choose based on what direction you want to be positive,
	 * this does not affect motor invert. 
	 */

    public static boolean kMotorInverted = false;

    public static final double SPIN_ENCODER_UNITS_PER_REVOLUTION = 4096;
    public static final int TALON_SPIN_PID_SLOT_ID = 0; 
    public static final int TALON_SPIN_PID_LOOP_ID = 0; 
    public static final int TALON_SPIN_PID_TIMEOUT_MS = 0;  

    public static final double DRIVE_ENCODER_UNITS_PER_REVOLUTION = 2048;
    public static final int TALON_DRIVE_PID_SLOT_ID = kSlotIdx; 
    public static final int TALON_DRIVE_PID_LOOP_ID = kPIDLoopIdx; 
    public static final int TALON_DRIVE_PID_TIMEOUT_MS = kTimeoutMs;  
    

    public static final double[][] DRIVE_PID_2019 = {
        /* kP */     {0.15, 0.15, 0.15, 0.15},
        /* kI */    {0.0, 0.0, 0.0, 0.0},
        /* kD */   {0.0, 0.0, 0.0, 0.0},
        /* kF */    {0.0, 0.0, 0.0, 0.0},    // Feed forward gain constant
        /* I-Zne */ {0.0, 0.0, 0.0, 0.0}     // The range of error for kI to take affect (like a reverse deadband)
    };
    
    public static final double[][] DRIVE_PID_2021 = {
        /* kP */     {0.15, 0.15, 0.15, 0.15},
        /* kI */    {0.0, 0.0, 0.0, 0.0},
        /* kD */   {0.0, 0.0, 0.0, 0.0},
        /* kF */    {0.0, 0.0, 0.0, 0.0},    // Feed forward gain constant
        /* I-Zne */ {0.0, 0.0, 0.0, 0.0}     // The range of error for kI to take affect (like a reverse deadband)
    };

    public static double DRIVE_PID[][] = (MasterConstants.is2021Bot) ? DRIVE_PID_2021 : DRIVE_PID_2019;

    public static final double[][] SPIN_PID_2019 = {
                    {1.0, 1.0, 1.0, 1.0},
        /* kI */    {0.0, 0.0, 0.0, 0.0},
                    {20.0, 20.0, 20.0, 20.0},
    /* kF */    {0.0, 0.0, 0.0, 0.0}    // Feed forward gain constant
    };

    public static final double[][] SPIN_PID_2021 = {
                    {2.0, 2.0, 2.0, 2.0},
        /* kI */    {0.0, 0.0, 0.0, 0.0},
                    {50.0, 50.0, 50.0, 50.0},
    /* kF */    {0.0, 0.0, 0.0, 0.0}    // Feed forward gain constant
    };

    public static double SPIN_PID[][] = (MasterConstants.is2021Bot) ? SPIN_PID_2021 : SPIN_PID_2019;

    public static final int[] SPIN_OFFSET_2019 = {-7492, -6034, -6649, -8475}; // 2019 Bot
    public static final int[] SPIN_OFFSET_2021 = {453, 307, 187183, -1191}; // 2021 Bot
    public static final int[] SPIN_OFFSET = (MasterConstants.is2021Bot) ? SPIN_OFFSET_2021 : SPIN_OFFSET_2019;

    public static final double DRIVE_SPEED_MAX_EMPIRICAL_FEET_PER_SECOND = 13.79;

    public static final double METERS_TO_FEET_CONSTANT = 3.28084;
    public static final double FEET_TO_METERS_CONSTANT = .3048;
}