package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    private static Controller instance = new Controller();
    XboxController duke = new XboxController(2);
    private final JoystickButton stopButton = new JoystickButton(duke, Button.kA.value);
    private final JoystickButton minButton = new JoystickButton(duke, Button.kB.value);
    private final JoystickButton medButton = new JoystickButton(duke, Button.kY.value);
    private final JoystickButton maxButton = new JoystickButton(duke, Button.kX.value);

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