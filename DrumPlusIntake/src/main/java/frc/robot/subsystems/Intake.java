// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import javax.swing.text.Position;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.constants.IntakeConstants;

/**
 * <b> The Intake subsystem </b>
 * <p>
 * The mechanism that pulls power cells from the ground and deposits them into the Drum. Uses DoubleSolenoids to extend outward and
 * to retract into the robot.
 * @see subsystems.Drum
 * @author Caleb Walters
 */
public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private static Intake instance = new Intake();
  private DoubleSolenoid leftPiston = new DoubleSolenoid(2, 5);
  private DoubleSolenoid rightPiston = new DoubleSolenoid(3, 4);
  private WPI_TalonSRX motor = new WPI_TalonSRX(IntakeConstants.INTAKE_MOTOR_CAN_ID);
  private boolean pistonCurrentSetting = false;
  private double intakeMotorSpeed = 0; 

  /**
   * Initializes the Intake once upon code deploy. <b>Does not run at each enable! </b>
   * @author Caleb Walters
   */
  public Intake() {
    leftPiston.set(DoubleSolenoid.Value.kOff);
    rightPiston.set(DoubleSolenoid.Value.kOff);
  }

  /**
   * @return A single, universal Intake instance to be used anywhere else in the code
   */
  public static Intake getInstance() {
    return instance;
  }

  /**
   * Sets the speed of the intake wheels.
   * @param percent between -1 and 1
   * @author Caleb Walters
   */
  public void setPercentControl(double percent) {
    motor.set(ControlMode.PercentOutput, percent);
    intakeMotorSpeed = percent;
  }

  /**
   * Extends left and right DoubleSolenoids to lower the Intake to the ground.
   * @author Caleb Walters
   */
  public void Extend() {
    pistonCurrentSetting = true;
    leftPiston.set(Value.kForward);
    rightPiston.set(Value.kForward);
  }

  /**
   * Retracts left and right DoubleSolenoids to bring Intake off the ground and back inside the bot.
   * @author Caleb Walters
   */
  public void Retract() {
    pistonCurrentSetting = false;
    leftPiston.set(Value.kReverse);
    rightPiston.set(Value.kReverse);
    // don't run the motors while inside the bot
    setPercentControl(0.0);
  }

  public boolean getPistonCurrentSetting() {
    return pistonCurrentSetting;
  }

  public double getIntakeMotorSpeed() {
    return intakeMotorSpeed;
  }
}
