package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.Timer;

public class RobotContainer {
  public XboxController op;
  public JoystickButton up;
  public Timer time;
  public int printDelay;

  public RobotContainer() {
    op = new XboxController(2);
    up = new JoystickButton(op, op.getPOV(0));
    time = new Timer();
    printDelay = 2;
    time.start();
    configureButtonBindings();
  }

  private void configureButtonBindings() {
    if(op.getTriggerAxis(Hand.kRight) > 0.5) {
      new RTrigger();
    }
    if(op.getTriggerAxis(Hand.kLeft) > 0.5) {
      new LTrigger();
    }
    if(up.get()) {
      new dpadUp0();
    }
    if(op.getPOV() == 0) {new dpadUp();} //Up
    if(op.getPOV() == 90) {new dpadRight();} //Right
    if(op.getPOV() == 180) {new dpadDown();} //Down
    if(op.getPOV() == 270) {new dpadLeft();} //Left
    if(time.get() % printDelay == 0)
      System.out.println("Left Trigger " + op.getTriggerAxis(Hand.kLeft) + " | Right Trigger " + op.getTriggerAxis(Hand.kRight) + " | Up " + up.get() + " | D-Pad Location " + op.getPOV());
  }

  public Command getAutonomousCommand() {return null;}
}