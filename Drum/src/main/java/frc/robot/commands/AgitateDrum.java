// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;
//import java.lang.Object.wait;

public class AgitateDrum extends CommandBase {
  /** Creates a new AgitateDrum. */

  Object obj = new Object();
  Drum m_Drum = Drum.getInstance();

  public AgitateDrum() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    for (int e = 1; e <= 10; e++) {
      m_Drum.shakeDrum(true);
      Thread.sleep(500, 0);
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
