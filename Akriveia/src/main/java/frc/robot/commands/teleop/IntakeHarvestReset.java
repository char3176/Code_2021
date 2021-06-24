package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drum;


/**
 * Shakes the Drum briefly and stops the Intake.
 */
public class IntakeHarvestReset extends InstantCommand {
  Drum mDrum = Drum.getInstance();
 

  public IntakeHarvestReset() {
    addRequirements(mDrum);

  }

  @Override
  public void initialize() {
    // System.out.println("IntakeHarvestReset.initialize executed. ############################################################");
    mDrum.shakeDrum();
    // mIntake.retractIntake();
    // System.out.println("SPRING");
  }
}
