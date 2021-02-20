package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.AngledShooter;

public class ShooterReset extends CommandBase {
  private AngledShooter m_AngledShooter = AngledShooter.getInstance();
  
  public ShooterReset() {
    addRequirements(m_AngledShooter);
  }

  @Override
  public void initialize() {
    m_AngledShooter.setPosition(m_AngledShooter.initialShooterAngle);
  }

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}