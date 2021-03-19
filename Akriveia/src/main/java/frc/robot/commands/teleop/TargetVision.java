// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.VisionClient;
import frc.robot.subsystems.AngledShooter;
import frc.robot.constants.AngledShooterConstants;
import frc.robot.subsystems.Flywheel;

public class TargetVision extends CommandBase {
  /** Creates a new TargetVision. */

  AngledShooter m_AngledShooter = AngledShooter.getInstance();
  VisionClient m_VisionClient = VisionClient.getInstance();
  Flywheel m_Flywheel = Flywheel.getInstance();
  private double wantedAngleTicks;
  private double wantedVelocityTicks; // Ticks per 100 ms

  public TargetVision() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_AngledShooter);
    addRequirements(m_Flywheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    /*
    Need to take the velocity and the angle (the only two values from resultArray) and give them to the AngledShooter and Flywheel.
    We should probably not make them instant changes so that the motors aren't trying to start stop spinning too quickly.
    */

    double[] resultArray = m_VisionClient.targetRecogControlLoop();
    if (resultArray != null) {
      wantedAngleTicks = resultArray[1] * (180 / Math.PI) * (AngledShooterConstants.TICS_EQUAL_TO_5DEGREES / 5);

      // might be checked in vision already, this is for safety until we're sure of that
      m_AngledShooter.setPosition(wantedAngleTicks);

      wantedVelocityTicks = resultArray[0]; // in m/s, need to get to ticks/100 ms (rpm to ticks/100 ms conversion in Flywheel subsystem)
      // what's the equation that compares linear and angular velocity?
    }

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
