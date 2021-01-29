// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Lifter;

public class ExtendPiston extends CommandBase {
  private Lifter m_Lifter = Lifter.getInstance();
  
  public ExtendPiston() {
    addRequirements(m_Lifter);
  }

  @Override
  public void initialize() {
    m_Lifter.pistonExtend();
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
