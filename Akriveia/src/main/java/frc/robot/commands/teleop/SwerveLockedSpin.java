package frc.robot.commands.teleop;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.driveMode;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import frc.robot.util.PIDLoop;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveLockedSpin extends CommandBase {
  private Drivetrain drivetrain = Drivetrain.getInstance();

  public SwerveLockedSpin() {
    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    drivetrain.setDriveMode(driveMode.SPIN_LOCK);
    drivetrain.setSpinLockAngle();
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished() { return false; }

  @Override
  public void end(boolean interrupted) {
    drivetrain.setDriveMode(driveMode.DRIVE);
  }
}
