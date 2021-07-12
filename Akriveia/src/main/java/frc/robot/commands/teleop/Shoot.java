package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.constants.TransferConstants;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Transfer;
import frc.robot.subsystems.Vision;

/**
 * Shoots a power cell, taking control of the Flywheel, Drum, and BallTransfer
 */
public class Shoot extends InstantCommand {
  Drum mDrum = Drum.getInstance();
  Flywheel mFlywheel = Flywheel.getInstance();
  Transfer mTransfer = Transfer.getInstance();
  Vision m_Vision = Vision.getInstance();
  // AngledShooter mAngle = AngledShooter.getInstance();
  // Timer time = new Timer();
  double visionAngle, visionDistanceX;
  
  public Shoot() {
    addRequirements(mDrum);
    // if (!m_Vision.isAtlasOn()) {addRequirements(mFlywheel);}
    addRequirements(mTransfer);
    addRequirements(mFlywheel);
    // addRequirements(mAngle);
    // System.out.println("Shoot Created");
  }

  @Override
  public void initialize() {
    // System.out.println("Shoot.initialize executed. ############################################################");
    visionAngle = m_Vision.getTargetAngle();
    visionDistanceX = m_Vision.getTargetDistanceX();
    mDrum.pidVelCtrl_setRpmLevel(1);
    // mFlywheel.spinVelocityOutputPercent(1);
    // mAngle.pctCtrl_raiseHoodPosition();
    // if (!m_Vision.isAtlasOn()) {mFlywheel.spinVelocityPIDF(5);}
    mFlywheel.spinVelocityPIDF(8);
    // mTransfer.setPercentControl(BallTransferConstants.BALL_TRANSFER_PERCENT/2);
    // Timer.delay(2);
    mTransfer.setPercentControl(TransferConstants.BALL_TRANSFER_PERCENT);
    // Timer.delay(5);
    // mTransfer.Extend();
    // mAngle.pctCtrl_holdHoodPosition();
  }
}