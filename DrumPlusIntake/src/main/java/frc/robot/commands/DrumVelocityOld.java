// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

/**
 * <b> Drum Velocity Class </b>
 * <p>
 * Commands Drum to spin at a certain velocity, such as low speed, medium speed,
 * high speed, and extreme speed. A rateLimiter is used to slowly spin and despin the motor.
 * @author Jared Brown, Caleb Walters, Amelia Bingamin
 */
public class DrumVelocityOld extends CommandBase {
  /** Creates a new DrumVelocity. */

  Drum m_Drum = Drum.getInstance();
  int button;
  
  /**
   * <b> Drum Velocity Method </b>
   * <p>
   * Adds the Drum methods and adds a new button called button Number.
   * @param buttonNumber
   * @author Jared Brown, Caleb Walters, Amelia Bingamin
   */
  public DrumVelocityOld(int buttonNumber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drum);
    button = buttonNumber;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    // A = 1, B = 2, Y = 3, X = 4, RBumper = 0 (the final else case).

    if (button == 1) {
      m_Drum.lowSpin();
    } else if (button == 2) {
      m_Drum.mediumSpin();
    } else if (button == 3) {
      m_Drum.highSpin();
    } else if (button == 4) {
      m_Drum.extremeSpin();
    } else {
      m_Drum.drumPowerOff();
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
