// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Drum extends SubsystemBase {
  CANSparkMax drumSpark = new CANSparkMax(0, MotorType.kBrushless);
  public static Drum instance = new Drum();

  /** Creates a new ExampleSubsystem. */
  public Drum() {
  }

  public void Off() {
  drumSpark.set(0);
  }

  public void Slow() {
    drumSpark.set(0.3);
  }

  public void Fast() {
    drumSpark.set(0.6);
  }

  public static Drum getInstance() {
    return instance;
  }
}
