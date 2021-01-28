// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Lifter;
import frc.robot.RobotContainer;

public class ExtendPiston extends InstantCommand {
  /** Creates a new Get90Clockwise. */
  private Lifter m_Lifter = Lifter.getInstance();
  
  public ExtendPiston() {
    super();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Lifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_Lifter.pistonExtend();
  }

  // Called every time the scheduler runs while the command is scheduled.
//  @Override
//  public void execute() {
//    System.out.println("command executed");
//    
//    m_Lifter.pistonExtend();
//    
//  }

  // Called once the command ends or is interrupted.
//  @Override
//  public void end(boolean interrupted) {}

  // Returns true when the command should end.
//  @Override
//  public boolean isFinished() {
//    return true;
//  }
}

