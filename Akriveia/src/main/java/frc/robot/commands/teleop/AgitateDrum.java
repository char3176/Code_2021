package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drum;

public class AgitateDrum extends InstantCommand {

  Drum m_Drum = Drum.getInstance();

  public AgitateDrum() {
    addRequirements(m_Drum);
  }

  @Override
  public void initialize() {
    m_Drum.shakeDrum();
  }
}