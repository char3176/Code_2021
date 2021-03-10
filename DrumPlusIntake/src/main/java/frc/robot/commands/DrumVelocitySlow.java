// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drum;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class DrumVelocitySlow extends InstantCommand {

  Drum m_Drum = Drum.getInstance();

  public DrumVelocitySlow() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // increment based on the lastSetting variable with 2 speeds and a stop
    int tempSetting = m_Drum.getLastSetting();
    if (tempSetting - 1 >= 0) {
      m_Drum.setSpeed(tempSetting - 1);
    }

  }
}