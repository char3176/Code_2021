package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Flywheel;

public class FlywheelMax extends CommandBase {
  private Flywheel m_Flywheel = Flywheel.getInstance();
  
  public FlywheelMax() {
    addRequirements(m_Flywheel);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    System.out.println("command executed");
    
    m_Flywheel.spinFlywheel(6380);
    
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}