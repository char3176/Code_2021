// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DrumCCWSet extends CommandBase {
  Drum m_Drum = Drum.getInstance();
  public DrumCCWSet() {
    addRequirements(m_Drum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("CCWPCT", 0);
    m_Drum.CounterClockwise(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double PCT = SmartDashboard.getNumber("CCWPCT", 0);
    m_Drum.CounterClockwise(PCT);
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
