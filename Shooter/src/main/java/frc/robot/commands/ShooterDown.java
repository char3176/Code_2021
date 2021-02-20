package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AngledShooter;

public class ShooterDown extends CommandBase {
  private AngledShooter m_AngledShooter = AngledShooter.getInstance();
  double targetPositonRotations;

  public ShooterDown() {
    addRequirements(m_AngledShooter);
  }
  
  @Override
  public void initialize() {
     if(m_AngledShooter.getShooterAngle() == 0.0){
       isFinished();
     }
  }

  @Override
  public void execute() {
    if(m_AngledShooter.getShooterAngle() >=Constants.k5Degrees){
    m_AngledShooter.setPosition(m_AngledShooter.getEncoderPosition() - Constants.k5Degrees);
    m_AngledShooter.setShooterAngle(m_AngledShooter.getShooterAngle() - Constants.k5Degrees);
    } else {
      m_AngledShooter.setPosition((m_AngledShooter.getEncoderPosition() - (Constants.k5Degrees - m_AngledShooter.getShooterAngle())));
      m_AngledShooter.setShooterAngle(0.0);
    }
  }
  
  @Override
  public void end(boolean interrupted) {
    if(m_AngledShooter.getShooterAngle() == 0.0){
      m_AngledShooter.setShooterAngle(0.0);
      m_AngledShooter.setPosition(m_AngledShooter.initialShooterAngle);
    }
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}