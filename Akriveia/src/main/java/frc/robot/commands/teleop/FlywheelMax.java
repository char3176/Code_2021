package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Flywheel;

public class FlywheelMax extends InstantCommand {
  private Flywheel m_Flywheel = Flywheel.getInstance();

  public FlywheelMax() {
    addRequirements(m_Flywheel);
  }

  @Override
  public void initialize() {
    m_Flywheel.spinVelocityPIDF(6380);
  }
}
