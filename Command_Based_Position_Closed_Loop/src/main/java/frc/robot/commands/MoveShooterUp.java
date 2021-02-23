// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

// import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AngledShooter;

public class MoveShooterUp extends CommandBase {
  private AngledShooter m_AngledShooter = AngledShooter.getInstance();

  public MoveShooterUp() {
    addRequirements(m_AngledShooter);
  }  
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    
    if(m_AngledShooter.currentShooterAngleTics > Constants.MAX_DEGREES_IN_TIC_UNITS + m_AngledShooter.initialShooterAngle){
      m_AngledShooter.currentShooterAngleTics = Constants.MAX_DEGREES_IN_TIC_UNITS + m_AngledShooter.initialShooterAngle;
      m_AngledShooter.setPosition(m_AngledShooter.currentShooterAngleTics); 
     }

    else if(m_AngledShooter.currentShooterAngleTics <= Constants.MAX_DEGREES_IN_TIC_UNITS - Constants.TICS_EQUAL_TO_5DEGREES+ m_AngledShooter.initialShooterAngle){
    m_AngledShooter.updateCurrentShooterAngleTics(Constants.TICS_EQUAL_TO_5DEGREES);
      m_AngledShooter.setPosition(m_AngledShooter.currentShooterAngleTics);
    }
 
    else{
      
      m_AngledShooter.updateCurrentShooterAngleTics(Constants.MAX_DEGREES_IN_TIC_UNITS+m_AngledShooter.initialShooterAngle - m_AngledShooter.currentShooterAngleTics);
      m_AngledShooter.setPosition(m_AngledShooter.currentShooterAngleTics);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
