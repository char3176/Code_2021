package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drum;

public class DrumVelocity extends InstantCommand {
  Drum m_Drum = Drum.getInstance();
  int button;
  
  public DrumVelocity(int buttonNumber) {
    addRequirements(m_Drum);
    button = buttonNumber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    // A = 0, B = 1, Y = 2, X = 3, RBumper = 10. (The final else is for button = 11, for the default Drum Power Off metod)

    if (button == 1) {
      m_Drum.lowSpin();
    } else if (button == 2) {
      m_Drum.mediumSpin();
    } else if (button == 3) {
      m_Drum.highSpin();
    } else if (button == 4) {
      m_Drum.extremeSpin();
    } else {
      m_Drum.drumPowerOff();
    }
  }
}