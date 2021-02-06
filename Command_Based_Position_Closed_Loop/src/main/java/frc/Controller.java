// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.ScheduleCommand;
import frc.robot.commands.PositionClosedLoop;

public class Controller {
    public static Controller instance = new Controller();
    public static Controller getInstance() {
        return instance;
    }
    public Joystick _joy = new Joystick(0);
   public  double leftYstick = _joy.getY();
    public boolean button1 = _joy.getRawButton(1);	
   public boolean _lastButton1 = false;

    
    
    
}












