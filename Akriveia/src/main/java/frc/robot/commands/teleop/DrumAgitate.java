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
    System.out.println("DrumAgitate.initialize executed. ############################################################");
    m_Drum.shakeDrum();
  }

  /*
  From Jared:
    This probably has to be a normal (not instant) command because of how the method in the subsystem works for interruptability.
    When I made it, the call to shakeDrum saved the returned boolean to a variable. Then, isFinished returned that variable, which the
    method in Drum makes true if the shake cycle is done, and false otherwise. This helps the commandScheduler not get stuck because of
    loops, and also allows the shaking to be interrupted before the cycle is complete.
  */

}