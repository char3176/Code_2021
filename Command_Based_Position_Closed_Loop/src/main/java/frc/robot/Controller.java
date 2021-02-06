// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    private static Controller instance = new Controller();
    Joystick _joy = new Joystick(0);

    private JoystickButton b1 = new JoystickButton(_joy, 1);
    private JoystickButton b2 = new JoystickButton(_joy, 2);

    public static Controller getInstance() {
        return instance;
    }

    public JoystickButton getButtonOne() {
        return b1;
    }

    public JoystickButton getButtonTwo() {
        return b2;
    }

    public double getYAxis() {
        return _joy.getY();
    }
}
