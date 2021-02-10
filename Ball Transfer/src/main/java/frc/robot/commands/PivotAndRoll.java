package frc.robot.commands;
import frc.robot.subsystems.BallTransfer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

public class PivotAndRoll extends CommandBase {
  private final BallTransfer m_BallTransfer = BallTransfer.getInstance();
  
  public PivotAndRoll() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_BallTransfer);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_BallTransfer.Retract();
    m_BallTransfer.setPercentControl(Constants.BALL_TRANSFER_PERCENT);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_BallTransfer.Retract();
    m_BallTransfer.setPercentControl(Constants.BALL_TRANSFER_PERCENT);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}