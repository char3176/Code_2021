package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

public class DrumCCW extends CommandBase {
  
  Drum m_Drum = Drum.getInstance();

  public DrumCCW() {
    addRequirements(m_Drum);
  }

  @Override
  public void initialize() {
    // System.out.println("DrumCCW.initialize executed. ############################################################");
  }

  @Override
  public void execute() {
    m_Drum.CounterClockwise();
  }

  @Override
  public boolean isFinished() { 
    return false; 
  }

  @Override
  public void end(boolean interrupted) {  

  }
  
}
