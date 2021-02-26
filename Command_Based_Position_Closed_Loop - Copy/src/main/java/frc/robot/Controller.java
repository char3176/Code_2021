// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class Controller {
    private static Controller instance = new Controller();
  
    private XboxController operator = new XboxController(0);
    private final JoystickButton aButton = new JoystickButton(operator,Button.kA.value);
    private final JoystickButton bButton = new JoystickButton(operator,Button.kB.value);
    private final JoystickButton xButton = new JoystickButton(operator,Button.kX.value);
    private final JoystickButton yButton = new JoystickButton(operator,Button.kY.value);

    public static Controller getInstance() {
        return instance;
    }
    
    public JoystickButton getAButton(){
        return aButton;
    }

    public JoystickButton getBButton(){
        return bButton;
    }
    public JoystickButton getXButton(){
        return xButton;
    }
    public JoystickButton getYButton(){
        return yButton;
    }
   /* private JoystickButton b1 = new JoystickButton(_joy, 1);
    private JoystickButton b2 = new JoystickButton(_joy, 2);

    

    public JoystickButton getButtonOne() {
        return b1;
    }

    public JoystickButton getButtonTwo() {
        return b2;
    }
*/
   

    public double moveAngleShooter(){
        return operator.getY(Hand.kRight);
    }
}
//