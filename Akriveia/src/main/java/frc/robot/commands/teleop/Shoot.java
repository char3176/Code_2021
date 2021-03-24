package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.constants.BallTransferConstants;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.BallTransfer;

public class Shoot extends InstantCommand {
  Drum mDrum = Drum.getInstance();
  Flywheel mFlywheel = Flywheel.getInstance();
  BallTransfer mTransfer = BallTransfer.getInstance();
  // Timer time = new Timer();
  
  public Shoot() {
    addRequirements(mDrum);
    addRequirements(mFlywheel);
    addRequirements(mTransfer);
    System.out.println("Shoot Created");
  }

  @Override
  public void initialize() {
    mDrum.simpleSet(0.3);
    mFlywheel.spinVelocityPIDF(3);
    Timer.delay(2);
    mTransfer.setPercentControl(BallTransferConstants.BALL_TRANSFER_PERCENT);
    mTransfer.Extend();
    System.out.println("Shoot Init");
  }
}