package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.*;

public class Controller {
    XboxController duke = new XboxController(1);
    public static Controller instance = new Controller();

    public final JoystickButton buttonOff = new JoystickButton(duke, Button.kB.value);
    public final JoystickButton buttonSlow = new JoystickButton(duke, Button.kA.value);
    public final JoystickButton buttonFast = new JoystickButton(duke, Button.kX.value);

    public JoystickButton getOffButton() {
        return buttonOff;
    }

    public JoystickButton getSlowButton() {
        return buttonSlow;
    }

    public JoystickButton getFastButton() {
        return buttonFast;
    }

    public static Controller getInstance() {
        return instance;
    }
}
