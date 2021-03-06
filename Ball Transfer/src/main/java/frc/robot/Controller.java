package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.*;

public class Controller {
    private static Controller instance = new Controller();

    private final XboxController duke = new XboxController(0);

    private final JoystickButton transferStraight = new JoystickButton(duke, Button.kA.value);
    private final JoystickButton transferPivotRoll = new JoystickButton(duke, Button.kB.value);

    public boolean isCancel = false;

    public static Controller getInstance() {
        return instance;
    }

    public JoystickButton getTransferStraightButton() {
        return transferStraight;
    }

    public JoystickButton getTransferPivotRollButton() {
        return transferPivotRoll;
    }
}