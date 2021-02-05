package frc.robot.commands;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class CancelRoll extends CommandBase {
    private final Intake m_Intake = Intake.getInstance();
    
    public CancelRoll() {
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(m_Intake);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      m_Intake.setPercentControl(0);
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      m_Intake.setPercentControl(0);
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