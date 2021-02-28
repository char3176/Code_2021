package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;

public class DeployIntake extends InstantCommand {
  private final Intake m_Intake = Intake.getInstance();

  public DeployIntake() {
    addRequirements(m_Intake);
  }

  @Override
  public void initialize() {
    m_Intake.Extend();
  }
}
