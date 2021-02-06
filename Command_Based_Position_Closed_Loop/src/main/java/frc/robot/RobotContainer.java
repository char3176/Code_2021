// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
<<<<<<< HEAD
import frc.robot.subsystems.AngledShooter;
=======
import frc.robot.commands.PositionClosedLoop;
import frc.robot.subsystems.AngleShooter;
>>>>>>> a4097457293ea36c461ae696803a13388502255e
import frc.robot.subsystems.ExampleSubsystem;
import frc.Controller;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
<<<<<<< HEAD
  private final AngledShooter m_AngledShooter = AngledShooter.getInstance();
=======
  private final AngleShooter m_angleShooter = new AngleShooter();
>>>>>>> a4097457293ea36c461ae696803a13388502255e

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final PositionClosedLoop m_PositonClosedLoop = new PositionClosedLoop();

private final Controller m_Controller = Controller.getInstance();

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    m_angleShooter._talon.configFactoryDefault();
		
		/* Config the sensor used for Primary PID and sensor direction */
        m_angleShooter._talon.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 
                                            Constants.kPIDLoopIdx,
				                                    Constants.kTimeoutMs);

		/* Ensure sensor is positive when output is positive */
		m_angleShooter._talon.setSensorPhase(Constants.kSensorPhase);
    
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    if ((!m_Controller._lastButton1) && m_Controller.button1) {
			
		m_PositonClosedLoop.schedule();
			
		}
    
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
