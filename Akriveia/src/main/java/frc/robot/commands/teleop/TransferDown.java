package frc.robot.commands.teleop;

import frc.robot.constants.TransferConstants;
import frc.robot.subsystems.Transfer;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class TransferDown extends CommandBase {

  private Transfer m_Transfer = Transfer.getInstance();
  private boolean didTransferExtend; 
  
  public TransferDown() {
    addRequirements(m_Transfer);
  }

  @Override
  public void initialize() {
    // System.out.println("BallTranferStraight.initialized executed. ########################################################");
  }

  @Override
  public void execute(){
    didTransferExtend = m_Transfer.Extend();
    m_Transfer.setPercentControl(TransferConstants.BALL_TRANSFER_PERCENT);
   
  }

  @Override
  public void end(boolean interrupted) {
   
  }

  @Override
  public boolean isFinished() {
    return didTransferExtend;
  }
}

