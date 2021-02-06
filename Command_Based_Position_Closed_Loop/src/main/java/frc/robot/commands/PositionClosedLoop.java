// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.Controller;
import frc.robot.subsystems.AngleShooter;

public class PositionClosedLoop extends CommandBase {
  /** Creates a new PositionClosedLoop. */
  public PositionClosedLoop() {
    // Use addRequirements() here to declare subsystem dependencies.
  }
  private AngleShooter m_AngleShooter = new AngleShooter();
  private Controller m_Controller = Controller.getInstance();
  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double targetPositionRotations = m_AngleShooter._talon.getSelectedSensorPosition(0) +.25*4096;/*leftYstick * 10.0 * 4096;*/
			
      m_AngleShooter._talon.set(ControlMode.Position, targetPositionRotations);
      m_Controller._lastButton1 = m_Controller.button1;
  isFinished();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
