// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Motor;

public class ChangeVelocity extends CommandBase {
  /** Creates a new Get90Clockwise. */
  private Motor m_Motor = Motor.getInstance();
  int button;
  
  public ChangeVelocity(int b) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Motor);
    this.button = b;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    System.out.println("Command start");
    if (button == 1) {
      m_Motor.lowSpin();
    } else if (button == 3) {
      m_Motor.highSpin();
    } else { // Only other case is B, to stop
      m_Motor.stopSpin();
    }
    System.out.println("Command end");
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
