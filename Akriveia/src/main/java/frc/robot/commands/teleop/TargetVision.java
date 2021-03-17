// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.VisionClient;
import frc.robot.subsystems.AngledShooter;

public class TargetVision extends CommandBase {
  /** Creates a new TargetVision. */

  AngledShooter m_AngledShooter = AngledShooter.getInstance();
  VisionClient m_VisionClient = VisionClient.getInstance();

  public TargetVision() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_AngledShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    boolean foundTarget = m_VisionClient.targetRecogControlLoop();

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
