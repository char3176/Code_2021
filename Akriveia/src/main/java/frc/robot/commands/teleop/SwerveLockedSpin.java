package frc.robot.commands.teleop;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.driveMode;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import frc.robot.util.PIDLoop;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class SwerveLockedSpin extends CommandBase {
  private Drivetrain drivetrain = Drivetrain.getInstance();

  private DoubleSupplier forwardCommand;
  private DoubleSupplier strafeCommand;

  private boolean stop;

  private PIDLoop spinPID;
  private double spinCommand;

  private double lockAngle;

  public SwerveLockedSpin(DoubleSupplier forwardCommand, DoubleSupplier strafeCommand) {
    this.forwardCommand = forwardCommand;
    this.strafeCommand = strafeCommand;

    lockAngle = drivetrain.getGyroAngle();
    spinPID = new PIDLoop(0.3, 0.0, 0.0, 0.0);

    addRequirements(drivetrain);
  }

  @Override
  public void initialize() {
    // If already locked we know we're already running this command and need to cancel it
    if(drivetrain.getCurrentDriveMode() == driveMode.SPIN_LOCK) { stop = true; }
    drivetrain.setDriveMode(driveMode.SPIN_LOCK);
  }

  @Override
  public void execute() {
    spinCommand = spinPID.returnOutput(drivetrain.getGyroAngle(), lockAngle);
    drivetrain.drive(forwardCommand.getAsDouble(), strafeCommand.getAsDouble(), spinCommand);
  }

  @Override
  public boolean isFinished() { return stop; }

  @Override
  public void end(boolean interrupted) {
    if(!interrupted) {
      drivetrain.setDriveMode(driveMode.DRIVE);
    }
  }
}
