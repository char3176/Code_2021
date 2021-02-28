package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.constants.IntakeConstants;
import frc.robot.subsystems.Intake;

public class IntakeDeployAndRoll extends InstantCommand {
  private final Intake m_Intake = Intake.getInstance();

  public IntakeDeployAndRoll() {
    addRequirements(m_Intake);
  }

  @Override
  public void initialize() {
    m_Intake.Extend();
    m_Intake.setPercentControl(IntakeConstants.INTAKE_PERCENT);
  }
}
