package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.AngledShooter;

public class ShooterUp extends CommandBase {
  private AngledShooter m_AngledShooter = AngledShooter.getInstance();
  double targetPositonRotations;

  public ShooterUp() {
    addRequirements(m_AngledShooter);
  } 
  
  @Override
  public void initialize() {
     if(m_AngledShooter.getShooterAngle() >= Constants.kMaxDegrees){
       isFinished();
     }
  }

  @Override
  public void execute() {
    
    if(m_AngledShooter.getShooterAngle() <= Constants.kSecondMax){
    m_AngledShooter.setPosition(m_AngledShooter.getEncoderPosition() + Constants.k5Degrees);
    m_AngledShooter.setShooterAngle(m_AngledShooter.getShooterAngle() + Constants.k5Degrees);
    }

    else{
      m_AngledShooter.setPosition(m_AngledShooter.getEncoderPosition() + (Constants.kMaxDegrees - m_AngledShooter.getShooterAngle()));
      m_AngledShooter.setShooterAngle(Constants.kMaxDegrees);
    }
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return true;
  }
}