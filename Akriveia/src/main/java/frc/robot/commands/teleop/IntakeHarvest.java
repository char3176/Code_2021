package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.Drum;


public class IntakeHarvest extends InstantCommand {
  Drum mDrum = Drum.getInstance();


  public IntakeHarvest() {
    addRequirements(mDrum);

  }

  @Override
  public void initialize() {
    // System.out.println("IntakeHarvest.initialize executed. ############################################################");
    mDrum.CounterClockwise();
    // mIntake.deployIntake();
    // System.out.println("HARVEST");
  }
}
