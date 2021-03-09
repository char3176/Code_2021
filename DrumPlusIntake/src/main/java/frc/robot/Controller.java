package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    
    private static Controller instance = new Controller();
    private XboxController m_Controller = new XboxController(2);
    private final JoystickButton intakePistonButton = new JoystickButton(m_Controller, Button.kA.value);
    private final JoystickButton intakeMotorButton = new JoystickButton(m_Controller, Button.kB.value);
    private final JoystickButton drumAgitateButton = new JoystickButton(m_Controller, Button.kY.value);
    private final JoystickButton drumSlowButton = new JoystickButton(m_Controller, Button.kBumperLeft.value);
    private final JoystickButton drumSpeedButton = new JoystickButton(m_Controller, Button.kBumperRight.value);
    private final JoystickButton transferStartButton = new JoystickButton(m_Controller, Button.kStart.value);
    private final JoystickButton transferStopButton = new JoystickButton(m_Controller, Button.kX.value);
    
    public JoystickButton getIntakePistonButton() {
        return intakePistonButton;
    }

    public JoystickButton getIntakeMotorButton() {
        return intakeMotorButton;
    }

    public JoystickButton getDrumSlowButton() {
        return drumSlowButton;
    }

    public JoystickButton getDrumSpeedButton() {
        return drumSpeedButton;
    }

    public JoystickButton getDrumAgitateButton() {
        return drumAgitateButton;
    }

    public JoystickButton getTransferStartButton() {
        return transferStartButton;
    }

    public JoystickButton getTransferStopButton() {
        return transferStopButton;
    }

    /**
     * Returns a universal instance of the Controller.
     * @return Controller -- to be used anywhere the Controller is needed
     */
    public static Controller getInstance() {
        return instance;
    }

}