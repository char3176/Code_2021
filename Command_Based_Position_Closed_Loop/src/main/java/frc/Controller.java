// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc;
import edu.wpi.first.wpilibj.Joystick;

public class Controller {
    private static Controller instance = new Controller();
    Joystick _joy = new Joystick(0);
    double leftYstick = _joy.getY();
    boolean button1 = _joy.getRawButton(1);	// X-Button
	boolean button2 = _joy.getRawButton(2);	// A-Button














}
