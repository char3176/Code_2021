// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.VisionConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.*;

public class AutonSelector extends CommandBase{
  private VisionClient mVisionClient = VisionClient.getInstance();
  private Drivetrain mDrivetrain = Drivetrain.getInstance();

  private double angle;

  public AutonSelector(){}

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){
    mVisionClient.controlLoopBallRecog();
    angle = (mVisionClient.hasTarget == 1) ? mVisionClient.ballAngle * -VisionConstants.DEG2RAD : -999;

    addRequirements(mDrivetrain);
  }

  /**
   * KEY:
   * A - red: about 0
   * A - blue: about -.3805063771 radians
   * B - red: about .463647609 radians
   * B - blue: about -.197395598 radians
   */
  @Override
  public void execute(){
    SmartDashboard.putNumber("Received Angle (rad)", angle);
    if(angle == -999){
      // print out an error message
      SmartDashboard.putString("Path", "UNKNOWN");
    } else if(angle < -.29){
      // A - blue
      SmartDashboard.putString("Path", "A - blue");
      mDrivetrain.drive(Math.cos(-.3805063771), Math.sin(-.3805063771), 0);
    } else if(angle < -.08){
      // B - blue
      SmartDashboard.putString("Path", "B - blue");
      mDrivetrain.drive(Math.cos(-.197395598), Math.sin(-.197395598), 0);
    } else if(angle < .15){
      // A - red
      SmartDashboard.putString("Path", "A - red");
      mDrivetrain.drive(Math.cos(0), Math.sin(0), 0);
    } else if(angle < .52){
      // B - red
      SmartDashboard.putString("Path", "B - red");
      mDrivetrain.drive(Math.cos(.463647609), Math.sin(.463647409), 0);
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted){
    mDrivetrain.drive(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished(){
    return false;
  }
}
