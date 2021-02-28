package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.AngledShooter;
import frc.robot.constants.AngledShooterConstants;


public class AngledShooterUp extends InstantCommand {
  private AngledShooter m_AngledShooter = AngledShooter.getInstance();

  public AngledShooterUp() {
    addRequirements(m_AngledShooter);
  }

  @Override
  public void initialize() {
    double temp = m_AngledShooter.getEncoderPosition();
    if(temp >= AngledShooterConstants.pos[3] - 100) {
      m_AngledShooter.setPosition(AngledShooterConstants.pos[4]);
    }
    else if(temp >= AngledShooterConstants.pos[2] - 100 && temp <= AngledShooterConstants.pos[4] + 100) {
      m_AngledShooter.setPosition(AngledShooterConstants.pos[3]);
    }
    else if(temp >= AngledShooterConstants.pos[1] - 100 && temp <= AngledShooterConstants.pos[3] + 100) {
      m_AngledShooter.setPosition(AngledShooterConstants.pos[2]);
    }
    else if(temp >= AngledShooterConstants.pos[0] - 100 && temp <= AngledShooterConstants.pos[2] + 100){
      m_AngledShooter.setPosition(AngledShooterConstants.pos[1]);
    }
  }
}