package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.constants.BallTransferConstants;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.BallTransfer;

public class Shoot extends InstantCommand {
  Drum mDrum = Drum.getInstance();
  Flywheel mFlywheel = Flywheel.getInstance();
  BallTransfer mTransfer = BallTransfer.getInstance();
  
  public Shoot() {
    addRequirements(mDrum);
    addRequirements(mFlywheel);
    addRequirements(mTransfer);
  }

  @Override
  public void initialize() {
    mDrum.simpleSet(0.3);
    mFlywheel.spinVelocityOutputPercent(0.8);
    mTransfer.setPercentControl(BallTransferConstants.BALL_TRANSFER_PERCENT);
    mTransfer.Extend();
  }
}