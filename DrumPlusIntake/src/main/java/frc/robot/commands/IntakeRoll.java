// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;
import frc.robot.constants.IntakeConstants;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class IntakeRoll extends InstantCommand {
  Intake m_Intake = Intake.getInstance();
  public IntakeRoll() {
    addRequirements(m_Intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (m_Intake.getIntakeMotorSpeed() == 0) {
      m_Intake.setPercentControl(IntakeConstants.INTAKE_PERCENT);
    }
    else {
      m_Intake.setPercentControl(0);
    }

  }
}
