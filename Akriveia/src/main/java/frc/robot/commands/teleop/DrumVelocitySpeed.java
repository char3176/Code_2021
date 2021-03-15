package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.constants.DrumConstants;
import frc.robot.subsystems.Drum;

public class DrumVelocitySpeed extends InstantCommand {

  Drum m_Drum = Drum.getInstance();

  public DrumVelocitySpeed() {
    addRequirements(m_Drum);
  }

  @Override
  public void initialize() {

    /* increment based on the lastSetting variable with 2 speeds and a stop */
    
    int tempSetting = m_Drum.getLastSetting();
    if (tempSetting + 1 < DrumConstants.drumSpeeds.length) {
      m_Drum.setSpeed(tempSetting + 1);
    }

  }
}