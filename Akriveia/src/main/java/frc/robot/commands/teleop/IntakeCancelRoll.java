package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Intake;

public class IntakeCancelRoll extends InstantCommand {
private final Intake m_Intake = Intake.getInstance();

  public IntakeCancelRoll() {
    addRequirements(m_Intake);
  }

  @Override
  public void initialize() {
    m_Intake.setPercentControl(0);
  }
}
