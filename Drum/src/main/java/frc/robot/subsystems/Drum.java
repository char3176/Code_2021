// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import frc.robot.Constants;

public class Drum extends SubsystemBase {
  /** Creates a new Drum. */

  private CANSparkMax drumMotor = new CANSparkMax(Constants.drumMotorCANID, MotorType.kBrushless);
  private CANPIDController drumPIDController;
  private CANEncoder drumEncoder;
  private SlewRateLimiter rateLimiter;
  private static Drum instance = new Drum();
  private double percentOutputSet = 0.0;
  public boolean drumPctOutputMode = false;

  public Drum() {

    // Set defaults and get all Motor components and set type
    // Set PID constants

    drumMotor.restoreFactoryDefaults();
    drumPIDController = drumMotor.getPIDController();
    drumEncoder = drumMotor.getEncoder();

    drumPIDController.setReference(0.0, ControlType.kVelocity);

    rateLimiter = new SlewRateLimiter(Constants.drumKRampRate, 0);

    drumPIDController.setP(Constants.drumKP);
    drumPIDController.setI(Constants.drumKI);
    drumPIDController.setD(Constants.drumKD);
    drumPIDController.setFF(Constants.drumKF);
    drumPIDController.setIZone(Constants.drumKIZone);
    drumPIDController.setOutputRange(Constants.drumKMinOutput, Constants.drumKMaxOutput);

  }

  public void highSpin() {
    drumPIDController.setReference(rateLimiter.calculate(4000), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("High run");
  }

  public void mediumSpin() {
    drumPIDController.setReference(rateLimiter.calculate(2000), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("Medium run");
  }

  public void lowSpin() {
    drumPIDController.setReference(rateLimiter.calculate(500), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("Low run");
  }

  public void easeStop() {
    drumPIDController.setReference(rateLimiter.calculate(0), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("Ease stop run");
  }

  public void instantStop() {
    // drumPIDController.setReference(0, ControlType.kVoltage);
    // System.out.println(drumEncoder.getVelocity());

    // attempt to make the motor slowly accelerate when started up after using the
    // instant stop button
    if (Math.abs(drumEncoder.getVelocity()) <= 10) {
      drumPIDController.setReference(rateLimiter.calculate(0), ControlType.kVelocity);
    } else {
      drumPIDController.setReference(0, ControlType.kVelocity);
    }
    System.out.println("Instant stop run");
  }

  // used with the default command to cut power to the drum upon being enabled
  public void drumPowerOff() {
    drumMotor.set(0.0);
    drumEncoder.getVelocity();
    System.out.println("Power off run");
  }

  public void shakeDrumOld(boolean direction) {
    if (direction) {
      drumPIDController.setReference(500, ControlType.kVelocity);
    } else {
      drumPIDController.setReference(-500, ControlType.kVelocity);
    }
  }

  public void shakeDrum() throws InterruptedException {
    for (int e = 1; e <= 5; e++) {
      drumMotor.set(0.05);
      Thread.sleep(500);
      drumMotor.set(-0.05);
      Thread.sleep(500);
    }
  }


  // Percent Output control, runs every loop to print the stuff
  public void percentOutputIncrement() {
    //drumMotor.set(percentOutputSet);
    drumEncoder.getVelocity();
    System.out.println("Percent output run at " + percentOutputSet);
  }

  // called once when the percent is triggered to be increased or decreased
  public void changePercentSet(boolean upDown) {
    if ((upDown == true) && (percentOutputSet < 1.0)) {
      percentOutputSet += 0.05;
    } else if ((upDown == false) && (percentOutputSet > -1.0)) {
      percentOutputSet -= 0.05;
    }
  }

  public void resetPercentSet() {
    percentOutputSet = 0.0;
  }

  public static Drum getInstance() {
    return instance;
  }

  // Periodic NOT USED for now
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
