package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drum;

public class DrumAgitate extends InstantCommand {

  Drum m_Drum = Drum.getInstance();

  public DrumAgitate() {
    addRequirements(m_Drum);
  }

  @Override
  public void initialize() {
    m_Drum.shakeDrum();
  }

  /*
    This probably has to be a normal (not instant) command because of how the method in the subsystem works for interruptability.
                                                                                                                - Jared
  */

}