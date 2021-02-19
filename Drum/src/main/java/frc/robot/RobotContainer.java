// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drum;
import frc.robot.commands.HighDrumVelocity;
import frc.robot.commands.MediumDrumVelocity;
import frc.robot.commands.LowDrumVelocity;
import frc.robot.commands.DrumModeControl;
import frc.robot.commands.DrumPercentOutput;
import frc.robot.commands.DrumVelocity;
import frc.robot.commands.EaseStopDrumVelocity;
import frc.robot.commands.InstantStopDrumVelocity;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  private Controller m_Controller;
  private Drum m_Drum;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_Controller = Controller.getInstance();
    m_Drum = Drum.getInstance();

    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    
    m_Drum.setDefaultCommand(new DrumVelocity(11));

    m_Controller.getLBumper().whenPressed(new DrumModeControl());

    // different commands for percent output vs velocity mode
    if (m_Drum.drumPctOutputMode == true) {
      m_Controller.getAButton().whenPressed(new DrumPercentOutput(2));
      m_Controller.getBButton().whenPressed(new DrumPercentOutput(1));
    } else {
      m_Controller.getAButton().whenPressed(new DrumVelocity(0));
      m_Controller.getBButton().whenPressed(new DrumVelocity(1));
      m_Controller.getYButton().whenPressed(new DrumVelocity(2));
      m_Controller.getXButton().whenPressed(new DrumVelocity(3));
      m_Controller.getRBumper().whenPressed(new DrumVelocity(10));
    }
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
