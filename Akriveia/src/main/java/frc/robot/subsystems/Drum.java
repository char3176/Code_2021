
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
import frc.robot.constants.DrumConstants;

public class Drum extends SubsystemBase {
  /** Creates a new Drum. */

  private CANSparkMax drumMotor = new CANSparkMax(DrumConstants.drumMotorCANID, MotorType.kBrushless);
  private CANPIDController drumPIDController;
  private CANEncoder drumEncoder;
  private SlewRateLimiter rateLimiter;
  private static Drum instance = new Drum();
  public boolean drumPctOutputMode = false;
  private boolean isRateLimitOff = false;
  private int lastSetting;
  private int direction = 1;
  private double shakeStartTime = -1;
  private double shakeIterations = 0;

  private double percentOutputSet = 0.0;     // for unused percent output control

  public Drum() {
    drumMotor.restoreFactoryDefaults();
    drumPIDController = drumMotor.getPIDController();
    drumEncoder = drumMotor.getEncoder();

    drumPIDController.setReference(0.0, ControlType.kVelocity);

    rateLimiter = new SlewRateLimiter(DrumConstants.drumKRampRate, 0);

    drumPIDController.setP(DrumConstants.drumKP);
    drumPIDController.setI(DrumConstants.drumKI);
    drumPIDController.setD(DrumConstants.drumKD);
    drumPIDController.setFF(DrumConstants.drumKF);
    drumPIDController.setIZone(DrumConstants.drumKIZone);
    drumPIDController.setOutputRange(DrumConstants.drumKMinOutput, DrumConstants.drumKMaxOutput);

  }

  public void reengageRampLimit() {
    if (isRateLimitOff) {
      rateLimiter.reset(drumEncoder.getVelocity());
      isRateLimitOff = false;
    }
  }

  public void extremeSpin() {
    reengageRampLimit();
    lastSetting = 4;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumExtreme), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("Extreme run");
  }

  public void highSpin() {
    reengageRampLimit();
    lastSetting = 3;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumHigh), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("High run");
  }

  public void mediumSpin() {
    reengageRampLimit();
    lastSetting = 2;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumMedium), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("Medium run");
  }

  public void lowSpin() {
    reengageRampLimit();
    lastSetting = 1;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumLow), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("Low run");
  }

  public void drumPowerOff() {
    isRateLimitOff = true;
    lastSetting = 0;
    drumMotor.set(0.0);
    drumEncoder.getVelocity();
    System.out.println("Power off run");
  }

  public boolean shakeDrum() {
    isRateLimitOff = true;
    if (shakeIterations < 10) {
      if (shakeStartTime == -1) {
        drumMotor.set(0.3 * direction);
        shakeStartTime = System.nanoTime() / 1000000;
        direction *= -1;
      }
      if ((System.nanoTime() / 1000000) - shakeStartTime >= 150) {
        drumMotor.set(0.3 * direction);
        direction *= -1;
        shakeIterations += 1;
        shakeStartTime = System.nanoTime() / 1000000;
      }
    } else {
      shakeIterations = 0;
      shakeStartTime = -1;
      direction = 1;
      return true;
    }
    return false;
  }

  public int getLastSetting() {
    return lastSetting;
  }

  public static Drum getInstance() {
    return instance;
  }

  @Override
  public void periodic() {}


  //***** EXPERIMENTAL PERCENT OUTPUT - NOT USED YET *****

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

}