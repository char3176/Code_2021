// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.commands.AgitateDrum;
import frc.robot.commands.CancelRoll;
import frc.robot.commands.DownAndRoll;
import frc.robot.commands.DrumVelocity;
import frc.robot.commands.DrumVelocityOld;
import frc.robot.commands.IntakeDown;
import frc.robot.commands.IntakeUp;
import frc.robot.commands.Roll;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private Controller m_Controller;
  private Compressor m_compressor;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {

    m_compressor = new Compressor();
    m_compressor.start();
    m_Controller = Controller.getInstance();

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

    m_Controller.getAButton().whenPressed(new DrumVelocityOld(1));
    m_Controller.getBButton().whenPressed(new DrumVelocityOld(2));
    m_Controller.getYButton().whenPressed(new DrumVelocityOld(3));
    m_Controller.getXButton().whenPressed(new DrumVelocityOld(4));
    m_Controller.getRBumper().whenPressed(new DrumVelocityOld(0));
    m_Controller.getLBumper().whenPressed(new AgitateDrum());

    m_Controller.getAButton().whenPressed(new IntakeDown());
    m_Controller.getBButton().whenPressed(new IntakeUp());
    m_Controller.getXButton().whenPressed(new DownAndRoll());
    m_Controller.getLBumper().whenPressed(new Roll());
    m_Controller.getRBumper().whenPressed(new CancelRoll());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
