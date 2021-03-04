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

public class BallTransfer extends SubsystemBase {
  /** Creates a new Intake. */
  private static BallTransfer instance = new BallTransfer();
  private DoubleSolenoid transferPiston = new DoubleSolenoid(2, 5);
  private WPI_TalonSRX transferMotor = new WPI_TalonSRX(33);

  public BallTransfer() {
    transferPiston.set(DoubleSolenoid.Value.kOff);
  }

  public static BallTransfer getInstance() {
    return instance;
  }

  public void setPercentControl(double percent) {
    transferMotor.set(ControlMode.PercentOutput, percent);
  }

  public void Extend() {
    transferPiston.set(Value.kForward);
  }

  public void Retract() {
    transferPiston.set(Value.kReverse);
  }
}
