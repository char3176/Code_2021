package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    
    private static Controller instance = new Controller();
    private XboxController m_Controller = new XboxController(2);
    private final JoystickButton intakePistonButton = new JoystickButton(m_Controller, Button.kA.value);
    private final JoystickButton intakeMotorButton = new JoystickButton(m_Controller, Button.kB.value);
    private final JoystickButton drumAgitateButton = new JoystickButton(m_Controller, Button.kX.value);
    private final JoystickButton drumSpeedLButton = new JoystickButton(m_Controller, Button.kBumperLeft.value);
    private final JoystickButton drumSpeedRButton = new JoystickButton(m_Controller, Button.kBumperRight.value);
    
    public JoystickButton getIntakePistonButton() {
        return intakePistonButton;
    }

    public JoystickButton getIntakeMotorButton() {
        return intakeMotorButton;
    }

    public JoystickButton getDrumSpeedLButton() {
        return drumSpeedLButton;
    }

    public JoystickButton getDrumSpeedRButton() {
        return drumSpeedRButton;
    }

    public JoystickButton getDrumAgitateButton() {
        return drumAgitateButton;
    }

    /**
     * Returns a universal instance of the Controller.
     * @return Controller -- to be used anywhere the Controller is needed
     */
    public static Controller getInstance() {
        return instance;
    }

}