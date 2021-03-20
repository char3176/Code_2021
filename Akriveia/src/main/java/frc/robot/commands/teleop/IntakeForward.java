package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;
import frc.robot.constants.IntakeConstants;

public class IntakeForward extends InstantCommand {

  Intake m_Intake = Intake.getInstance();

  public IntakeForward() {
    addRequirements(m_Intake);
  }

  @Override
  public void initialize() {

    /* Gets the current intake speed and sets it counterclockwise */

    if (m_Intake.getIntakeMotorSpeed() == 0) {
      m_Intake.setPercentControl(IntakeConstants.INTAKE_PERCENT * -1);
    } else {
      m_Intake.setPercentControl(0);
    }
  }
}