// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class AngledShooter extends SubsystemBase {
  
  private TalonSRX angledShooterTalon = new WPI_TalonSRX(Constants.angledShooterCANID);
  private static AngledShooter instance = new AngledShooter();
  private int absolutePosition = 0;
  //public double currentShooterAngleTics; 
  

  public AngledShooter() {
    angledShooterTalon.configFactoryDefault();
    angledShooterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.angledShooterPIDLoopIdx, Constants.angledShooterTimeoutMs);
    angledShooterTalon.setSensorPhase(Constants.angledShooterSensorPhase);
    angledShooterTalon.setInverted(Constants.angledShooterMotorInvert);
    angledShooterTalon.configNominalOutputForward(0, Constants.angledShooterTimeoutMs);
    angledShooterTalon.configNominalOutputReverse(0, Constants.angledShooterTimeoutMs);
		angledShooterTalon.configPeakOutputForward(1, Constants.angledShooterTimeoutMs);
		angledShooterTalon.configPeakOutputReverse(-1, Constants.angledShooterTimeoutMs);
    angledShooterTalon.configAllowableClosedloopError(0, Constants.angledShooterPIDLoopIdx, Constants.angledShooterTimeoutMs);
    angledShooterTalon.config_kF(Constants.angledShooterPIDLoopIdx, Constants.angledShooter_kF, Constants.angledShooterTimeoutMs);
		angledShooterTalon.config_kP(Constants.angledShooterPIDLoopIdx, Constants.angledShooter_kP, Constants.angledShooterTimeoutMs);
		angledShooterTalon.config_kI(Constants.angledShooterPIDLoopIdx, Constants.angledShooter_kI, Constants.angledShooterTimeoutMs);
    angledShooterTalon.config_kD(Constants.angledShooterPIDLoopIdx, Constants.angledShooter_kD, Constants.angledShooterTimeoutMs);
    
    absolutePosition = angledShooterTalon.getSensorCollection().getPulseWidthPosition();
    absolutePosition &= 0xFFF;
		if(Constants.angledShooterSensorPhase) {absolutePosition *= -1;}
    if(Constants.angledShooterMotorInvert) {absolutePosition *= -1;}
    angledShooterTalon.setSelectedSensorPosition(absolutePosition, Constants.angledShooterPIDLoopIdx, Constants.angledShooterTimeoutMs);
   // currentShooterAngleTics = getEncoderPosition();
    //SmartDashboard.putNumber("InitialShooterAngleTics", currentShooterAngleTics);
    SmartDashboard.putNumber("InitialEncoderPositon", getEncoderPosition());
    angledShooterTalon.set(ControlMode.Position,Constants.MIN_TICS);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public static AngledShooter getInstance() {
    return instance;
  }

  public void setPosition(double targetPosition){
      if (targetPosition>=Constants.MIN_TICS && targetPosition<=Constants.MAX_TICS){
      angledShooterTalon.set(ControlMode.Position, targetPosition);
  }
  else{
    System.out.println("NO");
  }
  SmartDashboard.putNumber("targetPositon",targetPosition);
}

 /* public void updateCurrentShooterAngleTics(double targetPosition){
    currentShooterAngleTics += targetPosition;
    SmartDashboard.putNumber("CurrentShooterAngleTics", currentShooterAngleTics);
    SmartDashboard.putNumber("CurrentEncoderPositon", getEncoderPosition());
  }  */


  public int getEncoderPosition() {
    return angledShooterTalon.getSelectedSensorPosition();
  }
}
