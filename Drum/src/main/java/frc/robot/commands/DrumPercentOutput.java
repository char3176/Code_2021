// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

public class DrumPercentOutput extends CommandBase {
  /** Creates a new DrumPercentOutput. */                               // NOT FINISHED YET!

  Drum m_Drum = Drum.getInstance();
  private int buttonNumber;

  public DrumPercentOutput(int buttonNumber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drum);
    this.buttonNumber = buttonNumber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // increases the percent for one button, decreases by 0.05 for another
    // runs normal function (doesn't change percent) when no button is pushed
    if (buttonNumber == 1) {
      m_Drum.changePercentSet(true);
    } else if (buttonNumber == 2) {
      m_Drum.changePercentSet(false);
    } else {
      m_Drum.percentOutputIncrement();
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
