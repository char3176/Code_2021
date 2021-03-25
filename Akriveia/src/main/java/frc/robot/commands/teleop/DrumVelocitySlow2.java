// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

public class DrumVelocitySlow2 extends CommandBase {
  /** Creates a new DrumVelocitySlow2. */
  Drum m_Drum = Drum.getInstance();
  int tempSetting;
  public DrumVelocitySlow2() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drum);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    System.out.println("DrumVelocitySlow2.initialized executed. ########################################################");
    tempSetting = m_Drum.getLastSetting();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (tempSetting - 1 >= 0) {
      m_Drum.setSpeed(tempSetting - 1);
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
