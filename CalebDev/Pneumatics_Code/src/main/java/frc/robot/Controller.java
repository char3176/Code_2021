package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    private static Controller instance = new Controller();
    Joystick stick = new Joystick(0);
    private final JoystickButton extend = new JoystickButton(stick, 1);
    private final JoystickButton retract = new JoystickButton(stick, 3);

    public JoystickButton getExtendButton() {
        return extend;
    }
    
    public JoystickButton getRetractButton() {
        return retract;
    }

    public static Controller getInstance() {
        return instance;
    }
}
