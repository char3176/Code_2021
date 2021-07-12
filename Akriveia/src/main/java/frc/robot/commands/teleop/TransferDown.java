package frc.robot.commands.teleop;

import frc.robot.constants.TransferConstants;
import frc.robot.subsystems.Transfer;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class TransferDown extends InstantCommand {

  private Transfer m_BallTransfer = Transfer.getInstance();
  
  public TransferDown() {
    addRequirements(m_BallTransfer);
  }

  @Override
  public void initialize() {
    // System.out.println("BallTranferStraight.initialized executed. ########################################################");
    m_BallTransfer.Extend();
    m_BallTransfer.setPercentControl(TransferConstants.BALL_TRANSFER_PERCENT);
  }
}
