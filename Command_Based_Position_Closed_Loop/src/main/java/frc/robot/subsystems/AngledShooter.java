// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.Joystick;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.*;

public class AngledShooter extends SubsystemBase {
  
  public TalonSRX _talon = new WPI_TalonSRX(33);
  
/** Used to create string thoughout loop */

  double targetPositionRotations;
  


  

  private static AngledShooter instance = new AngledShooter();
  /** Creates a new AngledShooter. */
  public AngledShooter() {
    _talon.configFactoryDefault();
    _talon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 
                                            Constants.kPIDLoopIdx,
                                            Constants.kTimeoutMs);
    _talon.setSensorPhase(Constants.kSensorPhase);
    _talon.setInverted(Constants.kMotorInvert);
    _talon.configNominalOutputForward(0, Constants.kTimeoutMs);
    _talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
		_talon.configPeakOutputForward(1, Constants.kTimeoutMs);
		_talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);
    _talon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    _talon.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
		_talon.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
		_talon.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
    _talon.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
    
    
    int absolutePosition = _talon.getSensorCollection().getPulseWidthPosition();
    absolutePosition &= 0xFFF;
		if (Constants.kSensorPhase) { absolutePosition *= -1; }
    if (Constants.kMotorInvert) { absolutePosition *= -1; }
    _talon.setSelectedSensorPosition(absolutePosition, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
    






  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static AngledShooter getInstance() {
    return instance;
  }
  public void setRotation(double targetRotation){
_talon.set(ControlMode.Position, targetRotation);
System.out.println(":)");
  }
  public void setRotation2(double targetRotation){
    _talon.set(ControlMode.PercentOutput, targetRotation);
      }
}
