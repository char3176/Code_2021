package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.constants.IntakeConstants;

/**
 * <b> The Intake subsystem </b>
 * <p>
 * The mechanism that pulls power cells from the ground and deposits them into the Drum. Uses DoubleSolenoids to extend outward and
 * to retract into the robot.
 * @see subsystems.Drum
 */

public class Intake extends SubsystemBase {

  /** Creates a new Intake. */

  private static Intake instance = new Intake();
  private DoubleSolenoid leftPiston = new DoubleSolenoid(2, 5);
  private DoubleSolenoid rightPiston = new DoubleSolenoid(1, 7);
  private WPI_TalonSRX motor = new WPI_TalonSRX(IntakeConstants.MOTOR_CAN_ID);
  private boolean pistonCurrentSetting = false;
  private double intakeMotorSpeed = 0;

  /**
   * Initializes the Intake once upon code deploy
   */

  public Intake() {}

  /**
   * @return A single, universal Intake instance to be used anywhere else in the code
   */

  public static Intake getInstance() {
    return instance;
  }

  /**
   * Sets the speed of the intake wheels.
   * @param percent between -1 and 1
   */

  public void setPercentControl(double percent) {
    motor.set(ControlMode.PercentOutput, percent);
    intakeMotorSpeed = percent;
  }

  /**
   * Extends left and right DoubleSolenoids to lower the Intake to the ground
   */

  public void Extend() {
    pistonCurrentSetting = true;
    leftPiston.set(Value.kForward);
    rightPiston.set(Value.kForward);
  }

  /**
   * Retracts left and right DoubleSolenoids to bring Intake off the ground and back inside the bot.
   * It also sets the motor to 0 because the intake shouldn't run while retracted.
   */

  public void Retract() {
    pistonCurrentSetting = false;
    leftPiston.set(Value.kReverse);
    rightPiston.set(Value.kReverse);
    setPercentControl(0.0);
  }

  /**
   * @return the current Piston Setting (either Extended or Retracted) as a boolean
   */

  public boolean getPistonCurrentSetting() {
    return pistonCurrentSetting;
  }

  /**
   * @return the intake's motor speed
   */
  
  public double getIntakeMotorSpeed() {
    return intakeMotorSpeed;
  }
}