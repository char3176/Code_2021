package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.BallTransfer;
import frc.robot.subsystems.Flywheel;
import edu.wpi.first.wpilibj2.command.CommandBase;



public class DrumTransferFlywheelTest extends CommandBase {
  Drum m_Drum = Drum.getInstance();
  BallTransfer m_BallTransfer = BallTransfer.getInstance();
  Flywheel m_Flywheel = Flywheel.getInstance();
  int button;
  
  public DrumTransferFlywheelTest(int buttonNumber) {
    addRequirements(m_Drum);
    addRequirements(m_BallTransfer);
    addRequirements(m_Flywheel);
    button = buttonNumber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    double drumOutputPercent = SmartDashboard.getNumber("DrumOutputPercent", 0);
    double transferOutputPercent = SmartDashboard.getNumber("BallTransferOutputPercent", 0);
    double flywheelOutputPercent = SmartDashboard.getNumber("FlywheelOutputPercent", 0);
    m_Drum.setPercentOutputSet(drumOutputPercent);
    m_BallTransfer.setPercentControl(transferOutputPercent);
    m_Flywheel.spinVelocityOutputPercent(flywheelOutputPercent);
  }

  @Override
  public boolean isFinished() { 
    if (SmartDashboard.getBoolean("StopTest06", true)) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public void end(boolean interrupted) { 
    m_Drum.setPercentOutputSet(0.0);
    m_BallTransfer.setPercentControl(0.0);
    m_Flywheel.spinVelocityOutputPercent(0.0);

  }

}