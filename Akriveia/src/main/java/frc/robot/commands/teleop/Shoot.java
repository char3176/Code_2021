package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.constants.BallTransferConstants;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.BallTransfer;
import frc.robot.VisionClient;

/**
 * Shoots a power cell, taking control of the Flywheel, Drum, and BallTransfer
 */
public class Shoot extends InstantCommand {
  Drum mDrum = Drum.getInstance();
  Flywheel mFlywheel = Flywheel.getInstance();
  BallTransfer mTransfer = BallTransfer.getInstance();
  VisionClient m_VisionClient = VisionClient.getInstance();
  // Timer time = new Timer();
  double visionAngle, visionDistanceX;
  
  public Shoot() {
    addRequirements(mDrum);
    addRequirements(mFlywheel);
    addRequirements(mTransfer);
    System.out.println("Shoot Created");
  }

  @Override
  public void initialize() {
    System.out.println("Shoot.initialize executed. ############################################################");
    visionAngle = m_VisionClient.getTargetAngle();
    visionDistanceX = m_VisionClient.getTargetDistanceX();
    mDrum.pidVelCtrl_setRpmLevel(1);
    mFlywheel.spinVelocityPIDF(5);
    // mTransfer.setPercentControl(BallTransferConstants.BALL_TRANSFER_PERCENT/2);
    // Timer.delay(2);
    mTransfer.setPercentControl(BallTransferConstants.BALL_TRANSFER_PERCENT);
    // Timer.delay(5);
    // mTransfer.Extend();
  }
}