// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

public class DrumVelocity extends CommandBase {
  /** Creates a new DrumVelocity. */

  Drum m_Drum = Drum.getInstance();
  int button;

  public DrumVelocity(int buttonNumber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drum);
    button = buttonNumber;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // A = 0, B = 1, Y = 2, X = 3, RBumper = 10 (just the final else case here)

    if (button == 0) {
      m_Drum.easeStop();
    } else if (button == 1) {
      m_Drum.lowSpin();
    } else if (button == 2) {
      m_Drum.mediumSpin();
    } else if (button == 3) {
      m_Drum.highSpin();
    } else {
      m_Drum.instantStop();
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
