package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    private static Controller instance = new Controller();
    XboxController duke = new XboxController(2);
    private final JoystickButton aButton = new JoystickButton(duke, Button.kA.value);
    private final JoystickButton bButton = new JoystickButton(duke, Button.kB.value);
    private final JoystickButton xButton = new JoystickButton(duke, Button.kX.value);



    public JoystickButton getAButton() {
        return aButton; 
    }

    public JoystickButton getBButton() {
        return bButton;
    }

    public JoystickButton getXButton() {
        return xButton;
    }

    public static Controller getInstance() {
        return instance;
    }
    
}