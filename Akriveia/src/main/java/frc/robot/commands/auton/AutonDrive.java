// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class AutonDrive extends CommandBase {
  /** Creates a new AutonDrive. */
  public AutonDrive() {
    // Use addRequirements() here to declare subsystem dependencies.
  }
  Timer timer;
  private Drivetrain drivetrain = Drivetrain.getInstance();

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

    timer.start();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrain.drive(1,0,0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(timer.get() > .5){
      return true;
    }
    return false;
  }
}
