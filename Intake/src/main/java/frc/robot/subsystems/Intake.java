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
import frc.robot.Constants;

public class Intake extends SubsystemBase {
  /** Creates a new Intake. */
  private static Intake instance = new Intake();
  private DoubleSolenoid leftPiston = new DoubleSolenoid(2, 5);
  private DoubleSolenoid rightPiston = new DoubleSolenoid(3, 4);
  private WPI_TalonSRX motor = new WPI_TalonSRX(Constants.MOTOR_CAN_ID);

  public Intake() {
    leftPiston.set(DoubleSolenoid.Value.kOff);
    rightPiston.set(DoubleSolenoid.Value.kOff);
  }

  public static Intake getInstance() {
    return instance;
  }

  public void setPercentControl(double percent) {
    motor.set(ControlMode.PercentOutput, percent);
  }

  public void Extend() {
    leftPiston.set(Value.kForward);
    rightPiston.set(Value.kForward);
  }

  public void Retract() {
    leftPiston.set(Value.kReverse);
    rightPiston.set(Value.kReverse);
  }
}
