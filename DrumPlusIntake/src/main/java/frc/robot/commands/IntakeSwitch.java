// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeSwitch extends InstantCommand {

  Intake m_Intake = Intake.getInstance();

  public IntakeSwitch() {

    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Intake);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    // Finds whatever setting the intake pistons are at and does the opposite.
    if (m_Intake.getPistonCurrentSetting()) {
      m_Intake.Retract();
    } else {
      m_Intake.Extend();
    }

  }
}
