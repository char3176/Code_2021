// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.VisionClient;
import frc.robot.subsystems.SystemPrinter;

public class LeftOrRight extends CommandBase {
  SystemPrinter msysprint = SystemPrinter.getInstance();
  public double hastarget = VisionClient.tx.getDouble(0);
  public double targetX = VisionClient.tx.getDouble(0);
  /** Creates a new LeftOrRight. */
  public LeftOrRight() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(msysprint);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    hastarget = VisionClient.tv.getDouble(0);
    targetX = VisionClient.tx.getDouble(0);
    double absOffset = Math.abs(targetX);
    if (hastarget == 1){
      msysprint.printPositivetv();
      if (targetX <= 1 && targetX >= -1){
        msysprint.printinfront();
      }
      else if(targetX >= 1){
        msysprint.printtright(absOffset);
      }
      else if(targetX <= -1){
        msysprint.printtoleft(absOffset);
      }
      else{
        System.out.println("You broke it. Good Job.");
      }
    }
    else if (hastarget == 0){
      msysprint.printNegativetv();
    }
    else{
      System.out.println("You broke it. Good job.");
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
