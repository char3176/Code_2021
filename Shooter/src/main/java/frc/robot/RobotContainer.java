// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ShooterDown;
import frc.robot.commands.ShooterUp;
import frc.robot.commands.ShooterReset;
import frc.robot.commands.FlywheelStop;
import frc.robot.commands.FlywheelMin;
import frc.robot.commands.FlywheelMed;
import frc.robot.commands.FlywheelMax;


public class RobotContainer {
  private Controller m_Controller = Controller.getInstance();

  public RobotContainer() {
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    m_Controller.getFlywheelStopButton().whenPressed(new FlywheelStop());
    m_Controller.getFlywheelMinButton().whenPressed(new FlywheelMin());
    m_Controller.getFlywheelMedButton().whenPressed(new FlywheelMed());
    m_Controller.getFlywheelMaxButton().whenPressed(new FlywheelMax());

    m_Controller.getShooterUpButton().whenPressed(new ShooterUp());
    m_Controller.getShooterDownButton().whenPressed(new ShooterDown()); 
    m_Controller.getShooterResetButton().whenPressed(new ShooterReset());
  }

  public Command getAutonomousCommand() {
    return null;
  }
}