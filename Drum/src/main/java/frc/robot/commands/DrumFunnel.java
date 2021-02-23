// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.


/************
 * This is an EXPIRIMENTAL command for percent output control. It is NOTU USED right now because it doesn't work.
************/


package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drum;

public class DrumFunnel extends CommandBase {
  /** Creates a new DrumFunnel. */

  Drum m_Drum = Drum.getInstance();
  private int buttonNumber;

  public DrumFunnel(int buttonNumber) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Drum);
    this.buttonNumber = buttonNumber;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (m_Drum.drumPctOutputMode == true) {
      new DrumPercentOutput(buttonNumber);
    } else {
      new DrumVelocity(buttonNumber);
    }    

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {



  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
