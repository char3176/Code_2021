// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.coordType;

public class AutonRotate extends CommandBase {

  private Drivetrain drivetrain = Drivetrain.getInstance();
  private double rotation;
  private double degrees;
  private double initialAngle;
  /** Creates a new AutonRotate. */
  public AutonRotate(double rot, double degrees) {
    addRequirements(drivetrain);
   rotation = rot;
   this.degrees = degrees;
  }

  

  @Override
  public void initialize() {
    drivetrain.setCoordType(coordType.ROBOT_CENTRIC);
    initialAngle = drivetrain.getNavxAngle_inDegrees();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.drive(0,0,rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(drivetrain.getNavxAngle_inDegrees() >= initialAngle + degrees ){
      return true;
    }
    return false;
  }
}
