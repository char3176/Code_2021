package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Controller {
    private static Controller instance = new Controller();
    private XboxController op = new XboxController(Constants.xbControllerPort);
    private final JoystickButton flyStopButton = new JoystickButton(op, Button.kBumperRight.value);
    private final JoystickButton flyMinButton = new JoystickButton(op, Button.kBumperLeft.value);
    private final JoystickButton flyMedButton = new JoystickButton(op, Button.kBack.value);
    private final JoystickButton flyMaxButton = new JoystickButton(op, Button.kY.value);
    private final JoystickButton shootUpButton = new JoystickButton(op,Button.kA.value);
    private final JoystickButton shootDownButton = new JoystickButton(op,Button.kB.value);
    private final JoystickButton shootRButton = new JoystickButton(op,Button.kX.value);

    public static Controller getInstance() {return instance;}

    //A - Shooter Up | B - Shooter Down | X - Shooter Reset | Y - Flywheel Max | Right Bumper - Flywheel Stop | Left Bumper - Flywheel Min | Back - Flywheel Med

    public JoystickButton getFlywheelStopButton(){return flyStopButton;}
    public JoystickButton getFlywheelMinButton(){return flyMinButton;}
    public JoystickButton getFlywheelMedButton(){return flyMedButton;}
    public JoystickButton getFlywheelMaxButton(){return flyMaxButton;}

    public JoystickButton getShooterUpButton(){return shootUpButton;}
    public JoystickButton getShooterDownButton(){return shootDownButton;}
    public JoystickButton getShooterResetButton(){return shootRButton;}
    public double moveAngleShooter(){return op.getY(Hand.kRight);}
}