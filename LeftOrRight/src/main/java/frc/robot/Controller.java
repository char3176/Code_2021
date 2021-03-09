// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.*;
/** Creates Controller Objects for use by the RobotContainer, specifically to bind buttons to commands. */
public class Controller {
    private static Controller instance = new Controller();
    private final XboxController controller = new XboxController(0);
    private final JoystickButton runButton = new JoystickButton(controller, Button.kA.value);


    public static Controller getInstance() {
        return instance;
    }
    public JoystickButton getrunButton(){
        return runButton;
    }

    }

