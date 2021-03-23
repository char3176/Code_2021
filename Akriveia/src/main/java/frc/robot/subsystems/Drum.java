package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import frc.robot.constants.DrumConstants;
import frc.robot.PowerManagement;

/**
 * <b> The Drum subsystem </b>
 * <p>
 * This mechanism holds up to 5 power cells, after the Intake and before the ball transfer / Flywheel. It spins at variable
 * speeds to unload power cells, and can shake to dislodge power cells stuck on top of one another inside the Drum.
 */

public class Drum extends SubsystemBase {

  /** Creates a new Drum. */

  private CANSparkMax drumMotor = new CANSparkMax(DrumConstants.drumMotorCANID, MotorType.kBrushless);
  private CANPIDController drumPIDController;
  private CANEncoder drumEncoder;
  private SlewRateLimiter rateLimiter;
  private static Drum instance = new Drum();
  public boolean drumPctOutputMode = false;
  private boolean isRateLimitOff = true;
  private int lastSetting = 0;
  private int direction = 1;
  private double shakeStartTime = -1;
  private double shakeIterations = 0;

  /**
   * Initializes the Drum subsystem once at code deploy.
   * <p>
   * Sets the Drum's PIDF constants, located in the Constants class, and creates rateLimiter and encoder objects.
   */

  public Drum() {
    drumMotor.restoreFactoryDefaults();
    drumPIDController = drumMotor.getPIDController();
    drumEncoder = drumMotor.getEncoder();

    drumPIDController.setReference(0.0, ControlType.kVelocity);

    rateLimiter = new SlewRateLimiter(DrumConstants.drumKRampRate, 0);
    
    /* Set PID constants */

    drumPIDController.setP(DrumConstants.drumKP);
    drumPIDController.setI(DrumConstants.drumKI);
    drumPIDController.setD(DrumConstants.drumKD);
    drumPIDController.setFF(DrumConstants.drumKF);
    drumPIDController.setIZone(DrumConstants.drumKIZone);
    drumPIDController.setOutputRange(DrumConstants.drumKMinOutput, DrumConstants.drumKMaxOutput);    
  }

  /**
   * Called one during each run of a nonzero spin speed method. This is to turn the rateLimiter back on at the Drum motor's current
   * velocity if it was turned off during the drumPowerOff and shakeDrum methods. It prevents sudden, violent jumps in speed.
   * @see Drum.drumPowerOff
   * @see Drum.shakeDrum
   */

  public void reengageRampLimit() {
    if (isRateLimitOff) {
      rateLimiter.reset(drumEncoder.getVelocity());
      isRateLimitOff = false;
    }
  }

  /**
   * <b> Spin </b>
   * <p>
   * Sets the Drum motor to whatever the index level is in the drumSpeeds array.
   * @see commands.teleop.DrumVelocitySlow
   * @see commands.teleop.DrumVelocitySpeed
   */

  public void setSpeed(int level) {
    reengageRampLimit();
    // if(level == 0) {isRateLimitOff = true;}
    lastSetting = level;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumSpeeds[level]), ControlType.kVelocity);
  }

  /**
   * @param pct The percentage to set the Drum to
   */
  public void simpleSet(double pct) {
    drumMotor.set(pct);
  }

  /**
   * <b> Shaking the Drum: </b>
   * <p>
   * Shakes the Drum rapidly, 5 times in each direction plus one extra time in the positive direction to slow it down initially.
   * Utilizes System.nanoTime to create a delay in between each change in direction.
   * <p>
   * The returned boolean tells the AgitateDrum command whether or not to keep running. Avoids using a loop as <b> loops
   * confuse the CommandScheduler</b>.
   * @see commands.AgitateDrum
   * @return Boolean -- whether or not the Drum has changed direction the specified number of times
   */

  public boolean shakeDrum() {
    isRateLimitOff = true;
    if (shakeIterations < 10) {
      if (shakeStartTime == -1) {
        drumMotor.set(DrumConstants.drumShakePct * direction);
        shakeStartTime = System.nanoTime() / DrumConstants.drumMilli;
        direction *= -1;
      }
      if ((System.nanoTime() / DrumConstants.drumMilli) - shakeStartTime >= 150) {
        drumMotor.set(DrumConstants.drumShakePct * direction);
        direction *= -1;
        shakeIterations += 1;
        shakeStartTime = System.nanoTime() / DrumConstants.drumMilli;
      }
    } else {
      shakeIterations = 0;
      shakeStartTime = -1;
      direction = 1;
      return true;
    }
    return false;
  }

  /**
   * <b> Checks the time </b>
   * <p>
   * If: spins at the slowest speed in reverse
   * <p>
   * If not: sets the motor to its last setting
   */

  public void ShortSpinInReverse() {
    if (System.nanoTime() / DrumConstants.drumSec >= 2) {
      drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumSpeeds[1] * -1), ControlType.kVelocity);
    }
    setSpeed(lastSetting);
  }

  public boolean PreShootSpinAgitate() {
    isRateLimitOff = true;
    if (shakeIterations < 5) {
      if (shakeStartTime == -1) {
        drumMotor.set(DrumConstants.drumShakePct * direction);
        shakeStartTime = DrumConstants.drumMilli;
        direction *= -1;
      }
      if ((System.nanoTime() / DrumConstants.drumMilli) - shakeStartTime >= 150) {
        drumMotor.set(DrumConstants.drumShakePct * direction);
        direction *= -1;
        shakeIterations += 1;
        shakeStartTime = System.nanoTime() / DrumConstants.drumMilli;
      }
    } else {
      shakeIterations = 0;
      shakeStartTime = -1;
      direction = 1;
      return true;
    }
    return false;
  }

  /**
   * Gets the Voltage of the Drum and if it is past a certain voltage it stops the intake
   * <p><b> DOESN'T WORK </b>
   */

  public void CheckForVoltageDrop() {
    double volt = PowerManagement.getDrumCurrent();
    if (volt >= 0) {
      drumMotor.set(0);
    }
  }

  /**
   * Sets the motor to -0.3 percent which is slow counter clockwise
   */

  public void CounterClockwise() {
    drumMotor.set(-0.3);
  }

  /**
   * @param PCT the percentage to make negative and go counter clockwise
   */

  public void CounterClockwise(double PCT) {
    drumMotor.set(-PCT);
  }

  /**
   * @return the last speed of the Drum
   */

  public int getLastSetting() {
    return lastSetting;
  }

  /**
   * <b> Returns Drum Instance </b>
   * <p>
   * Returns the instance created above for Drum.
   * @return instance
   */

  public static Drum getInstance() {
    return instance;
  }

  @Override
  public void periodic() {}
}