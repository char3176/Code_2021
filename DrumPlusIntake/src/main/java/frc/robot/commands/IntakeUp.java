// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
/**
 * <b> Go Up Class </b>
 * <p>
 * Creates command that pushes the intake up by retracting the pistons.
 * @author Caleb Walters
 */
public class IntakeUp extends CommandBase {
  private final Intake m_Intake = Intake.getInstance();
  /**
   * Add commands from Intake subsystem.
   * @author Caleb Walters
   */
  public IntakeUp() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // Finds whatever setting the intake pistons are at and does the opposite.
    if (m_Intake.getPistonCurrentSetting()) {
      m_Intake.Retract();
    } else {
      m_Intake.Extend();
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
