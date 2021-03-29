package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.AngledShooter;

public class AngledShooterOff extends InstantCommand {
  AngledShooter mAngledShooter = AngledShooter.getInstance();

  public AngledShooterOff() {
    addRequirements(mAngledShooter);
  }

  @Override
  public void initialize() {
    mAngledShooter.goUpToNextHoodPosition(0);
  }
}
