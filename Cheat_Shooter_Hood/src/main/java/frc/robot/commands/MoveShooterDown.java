// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AngledShooter;

public class MoveShooterDown extends CommandBase {
  private AngledShooter m_AngledShooter = AngledShooter.getInstance();

  public MoveShooterDown() {
    addRequirements(m_AngledShooter);
  }
  
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
     
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    int temp = m_AngledShooter.getEncoderPosition();
    if(temp <= Constants.P1+100 ){
     m_AngledShooter.setPosition(Constants.MIN_TICS);
    }
    else if(temp <= Constants.P2+100 && temp>=Constants.MIN_TICS-100){
       m_AngledShooter.setPosition(Constants.P1);
    }
    else if(temp <= Constants.P3+100 && temp>=Constants.P1-100){
     m_AngledShooter.setPosition(Constants.P2);
   }
   else if(temp <= Constants.MAX_TICS+100 && temp>=Constants.P2-100){
     m_AngledShooter.setPosition(Constants.P3);
   }
   System.out.println("DOWN");
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
