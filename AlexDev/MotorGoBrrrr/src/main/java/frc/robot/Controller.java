package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    private static Controller instance = new Controller();
    XboxController duke = new XboxController(0);
    private final JoystickButton flywheelSpeedButton = new JoystickButton(duke, Button.kA.value);
    private final JoystickButton minButton = new JoystickButton(duke, Button.kB.value);

    public JoystickButton getAButton(){
        return stopButton; 
    }
    public JoystickButton getBButton(){
        return minButton; 
    }
    public JoystickButton getYButton(){
        return medButton; 
    }
    public JoystickButton getXButton(){
        return maxButton; 
    }

    public static Controller getInstance() {
        return instance;
    }
    
}