package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.*;

public class Controller {
    private static Controller instance = new Controller();

    private final XboxController duke = new XboxController(0);

    private final JoystickButton extendDong = new JoystickButton(duke, Button.kA.value);
    private final JoystickButton retractDong = new JoystickButton(duke, Button.kB.value);
    private final JoystickButton roll = new JoystickButton(duke, Button.kX.value);
    private final JoystickButton rollExtendDong = new JoystickButton(duke, Button.kY.value);

    public static Controller getInstance() {
        return instance;
    }

    public JoystickButton getAButton() {
        return extendDong;
    }

    public JoystickButton getBButton() {
        return retractDong;
    }

    public JoystickButton getXButton() {
        return roll;
    }

    public JoystickButton getYButton() {
        return rollExtendDong;
    }
}