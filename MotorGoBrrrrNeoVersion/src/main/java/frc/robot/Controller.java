package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    private static Controller instance = new Controller();
    XboxController duke = new XboxController(2);
    private final JoystickButton turnButton = new JoystickButton(duke, Button.kA.value);
    
    public JoystickButton getAButton(){
        return turnButton; 
    }

    public static Controller getInstance() {
        return instance;
    }
    
}