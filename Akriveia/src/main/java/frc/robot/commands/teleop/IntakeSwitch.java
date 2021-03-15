package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;

public class IntakeSwitch extends InstantCommand {

  Intake m_Intake = Intake.getInstance();

  public IntakeSwitch() {
    addRequirements(m_Intake);
  }

  @Override
  public void initialize() {

    /* Finds whatever setting the intake pistons are and does the opposite. */

    if (m_Intake.getPistonCurrentSetting()) {
      m_Intake.Retract();
    } else {
      m_Intake.Extend();
    }

  }
}