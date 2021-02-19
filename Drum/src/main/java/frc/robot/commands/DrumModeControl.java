// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

public class DrumModeControl extends CommandBase {
  /** Creates a new DrumModeControl. */

  Drum m_Drum = Drum.getInstance();

  public DrumModeControl() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // switches the public Drum mode variable between percent output and velocity with PID
    if (m_Drum.drumPctOutputMode == true) {
      m_Drum.drumPctOutputMode = false;
      m_Drum.drumPowerOff();
      m_Drum.resetPercentSet(); // resets percent to zero upon selecting this mode for safety
      m_Drum.setDefaultCommand(new DrumPercentOutput(0));
    } else {
      m_Drum.drumPctOutputMode = true;
      m_Drum.drumPowerOff();
      m_Drum.setDefaultCommand(new DrumVelocity(11));
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
