// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.auton;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import frc.robot.subsystems.Vision;
import frc.robot.commands.auton.*;
import frc.robot.commands.teleop.HoodPosUp;
import frc.robot.commands.teleop.HoodPosDown;
import frc.robot.commands.teleop.ShootVision;
import frc.robot.commands.teleop.TransferDown;
import frc.robot.commands.teleop.TransferUp;
import frc.robot.commands.teleop.ShootReset;


public class ShootVisionSetUp extends SequentialCommandGroup {
  Vision m_Vision = Vision.getInstance();
  
  public ShootVisionSetUp() {
    // Use addRequirements() here to declare subsystem dependencies.

    addCommands(
      new SequentialCommandGroup(
        new ParallelCommandGroup(
          new ShootVision(),  //spins up TxrBelt/Rollers and the Drum for "Shooting"
          new AlignVizYawBangBang(),  //uses limelight to align drivetrain such that the horizontal launch angle is within 1-2 degrees of center of recog'd Target
          // new AlignVizYawPLoop(),
          new HoodPosUp()  // just incase Hood isn't already up
        ),
        new SequentialCommandGroup( 
          new TransferDown(),
          // new IrCounter(3),
          new DelayCommand(5)
        ),
        new ParallelCommandGroup( 
          new TransferUp(), 
          new ShootReset() 
          //new HoodPosDown()
        )
      )
    );
  }
}
