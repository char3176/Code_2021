package frc.robot;
import javax.swing.JFileChooser;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
// import frc.robot.Constants;

public class Controller {
    
    private static Controller instance = new Controller();
    private XboxController m_Controller = new XboxController(Constants.XBoxControllerPort);
    private final JoystickButton aButton = new JoystickButton(m_Controller, Button.kA.value);
    private final JoystickButton bButton = new JoystickButton(m_Controller, Button.kB.value);
    private final JoystickButton yButton = new JoystickButton(m_Controller, Button.kY.value);
    private final JoystickButton xButton = new JoystickButton(m_Controller, Button.kX.value);
    private final JoystickButton rBumper = new JoystickButton(m_Controller, Button.kBumperRight.value);

    public JoystickButton getAButton() {
        return aButton;
    }

    public JoystickButton getBButton() {
        return bButton;
    }

    public JoystickButton getYButton() {
        return yButton;
    }

    public JoystickButton getXButton() {
        return xButton;
    }

    public JoystickButton getRBumper() {
        return rBumper;
    }

    public static Controller getInstance() {
        return instance;
    }

}