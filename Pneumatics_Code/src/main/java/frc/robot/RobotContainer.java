// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import frc.robot.commands.ExtendPiston;
import frc.robot.commands.RetractPiston;
import frc.robot.commands.ExtendPistonInstant;
import frc.robot.subsystems.Lifter;
import edu.wpi.first.wpilibj2.command.Command;

public class RobotContainer {
  public Lifter m_Lifter;
  private Controller m_Controller;
  private Compressor m_compressor;

  public RobotContainer() {
    m_compressor = new Compressor();
    m_compressor.start();

    m_Lifter = Lifter.getInstance();
    m_Controller = Controller.getInstance();
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    m_Controller.getExtendButton().whenPressed(new ExtendPiston());
    m_Controller.getRetractButton().whenPressed(new RetractPiston());
    m_Controller.getExtendInstButton().whenPressed(new ExtendPistonInstant());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return null;
  }
}