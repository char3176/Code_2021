// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.teleop;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.AngledShooter;
import frc.robot.subsystems.BallTransfer;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Flywheel;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class TrenchRunMode extends InstantCommand {
  AngledShooter mAngledShooter = AngledShooter.getInstance();
  Drum mDrum = Drum.getInstance();
  BallTransfer mTransfer = BallTransfer.getInstance();
  Flywheel mFlywheel = Flywheel.getInstance();

  public TrenchRunMode() {
    addRequirements(mAngledShooter);
    addRequirements(mDrum);
    addRequirements(mTransfer);
    addRequirements(mFlywheel);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mAngledShooter.moveBottom();
    mDrum.pctCtrl_set(0);
    mTransfer.setPercentControl(0);
    mFlywheel.spinVelocityOutputPercent(0);
  }
}
