// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.commands.AgitateDrum;
import frc.robot.commands.DrumVelocitySpeed;
import frc.robot.commands.DrumVelocitySlow;
import frc.robot.commands.DrumSpinReverse;
import frc.robot.commands.DrumAgitatePreShoot;
import frc.robot.commands.IntakeRoll;
import frc.robot.commands.IntakeSwitch;
import frc.robot.commands.BallTransferPivot;
import frc.robot.commands.BallTransferRoll;
import frc.robot.commands.FlywheelSlow;
import frc.robot.commands.FlywheelSpeed;

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

    m_Controller.getDrumSlowButton().whenPressed(new DrumVelocitySlow());
    m_Controller.getDrumSpeedButton().whenPressed(new DrumVelocitySpeed());
    m_Controller.getDrumAgitateButton().whenPressed(new AgitateDrum());
    m_Controller.getDrumSpinReverseButton().whenPressed(new DrumSpinReverse());
    m_Controller.getDrumPreShootAgitateButton().whenPressed(new DrumAgitatePreShoot());
    // m_Controller.getIntakePistonButton().whenPressed(new IntakeSwitch());
    m_Controller.getIntakeMotorButton().whenPressed(new IntakeRoll());
    m_Controller.getTransferPivotButton().whenPressed(new BallTransferPivot());
    m_Controller.getTransferRollButton().whenPressed(new BallTransferRoll());
    m_Controller.getFlywheelSlowButton().whenPressed(new FlywheelSlow());
    m_Controller.getFlywheelSpeedButton().whenPressed(new FlywheelSpeed());

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