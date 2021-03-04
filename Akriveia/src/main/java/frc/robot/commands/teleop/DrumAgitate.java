package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

public class DrumAgitate extends CommandBase {
  Drum m_Drum = Drum.getInstance();
  private boolean ranThroughSequence;

  public DrumAgitate() {
    addRequirements(m_Drum);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    // BUG ALERT: The motor shakes correctly, but the loop doesn't stop and can't be
    // interrupted very easily by another command.
      ranThroughSequence = m_Drum.shakeDrum();
  }

  @Override
  public void end(boolean interrupted) {
    System.out.println(m_Drum.getLastSetting());  // Getting last state doesn't work yet
    new DrumVelocity(m_Drum.getLastSetting());
  }

  @Override
  public boolean isFinished() {
    return ranThroughSequence;
  }
}
