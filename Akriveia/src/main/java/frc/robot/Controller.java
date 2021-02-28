package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import frc.robot.constants.ControllerConstants;

public class Controller {
    private static Controller instance = new Controller();
    public static Controller getInstance() {return instance;}

    /* Controllers */

    private final Joystick transStick = new Joystick(ControllerConstants.TRANSLATION_STICK_ID);
    private final Joystick rotStick = new Joystick(ControllerConstants.ROTATION_STICK_ID);
    private final XboxController op = new XboxController(ControllerConstants.XBOX_CONTROLLER_ID);;

    /* Drivetrain */

    private final JoystickButton orbitButton = new JoystickButton(rotStick, 2);
    private final JoystickButton dosadoButton = new JoystickButton(rotStick, 3);
    private final JoystickButton visionButton = new JoystickButton(transStick, 3); // or op Y //Should be part of the xbox controller later
    private final JoystickButton defenseButton = new JoystickButton(transStick, 2);
    private final JoystickButton fieldCentricButton = new JoystickButton(transStick, 4);
    private final JoystickButton robotCentricButton = new JoystickButton(transStick, 5);
    private final JoystickButton backRobotCentricButton = new JoystickButton(transStick, 6);
    private final JoystickButton reZeroGyroButton = new JoystickButton(rotStick, 4);
    private final JoystickButton slalomButton = new JoystickButton(transStick, 7);
    
    /* Intake */

    private final JoystickButton extendButton = new JoystickButton(rotStick, 11);
    private final JoystickButton retractButton = new JoystickButton(rotStick, 12);
    private final JoystickButton rollExtendButton = new JoystickButton(rotStick, 13);
    private final JoystickButton rollButton = new JoystickButton(rotStick, 14);
    private final JoystickButton iStopButton = new JoystickButton(rotStick, 15);

    /* Angled Shooter */

    private final JoystickButton shooterUpButton = new JoystickButton(op, op.getPOV(180)); //Up on the D-Pad
    private final JoystickButton shooterDownButton = new JoystickButton(op, op.getPOV(0)); //Down on the D-Pad

    /* Drum */

    private final JoystickButton agitateDrumButton = new JoystickButton(op, Button.kStart.value);
    private final JoystickButton drumDownButton = new JoystickButton(op, Button.kBumperLeft.value);
    private final JoystickButton drumUpButton = new JoystickButton(op, Button.kBumperRight.value);

    /* Flywheel */
    
     
    private final JoystickButton flywheelRightButton = new JoystickButton(op, op.getPOV(90)); //Right on the D-Pad
    private final JoystickButton flywheelLeftButton = new JoystickButton(op, op.getPOV(270)); //Left on the D-Pad


    /* Swerve Axis Data */

    public double getForward() { 
        if (Math.abs(-transStick.getY())<.06) {
            return 0.0;
        } else {
            return -1 * (Math.pow((transStick.getY()),1)*1);}
    }
    
    public double getStrafe() { 
        if (Math.abs(transStick.getX())<.06) {
            return 0.0;
        } else {
            return -1 * (Math.pow((-1 * transStick.getX()),1) * 1);}
    }

    public double getSpin() { 
        if (Math.abs(rotStick.getX())<.06) {
            return 0.0;
        } else {
            return Math.pow(rotStick.getX(),1) / 7.0;}
    }

    /* Drivetrain Buttons */

    public JoystickButton getOrbitButton() {return orbitButton;}
    public JoystickButton getDosadoButton() {return dosadoButton;}
    public JoystickButton getVisionButton() {return visionButton;}
    public JoystickButton getDefenseButton() {return defenseButton;}
    public JoystickButton getReZeroGyroButton() {return reZeroGyroButton;}
    public JoystickButton getSlalomButton() {return slalomButton;}
    public boolean isFieldCentricButtonPressed() {return fieldCentricButton.get();}
    public boolean isRobotCentricButtonPressed() {return robotCentricButton.get();}
    public boolean isBackRobotCentricButtonPressed() {return backRobotCentricButton.get();}

    /* Intake Buttons */

    public JoystickButton getExtendButton() {return extendButton;}
    public JoystickButton getRetractButton() {return retractButton;}
    public JoystickButton getRollAndExtendButton() {return rollExtendButton;}
    public JoystickButton getRollButton() {return rollButton;}
    public JoystickButton getStopButton() {return iStopButton;}

    /* Angled Shooter*/

    public boolean getUpDPADB() {
        if(op.getPOV() == 180) {return true;}
        return false;
    }

    public boolean getDownDPADB() {
        if(op.getPOV() == 0) {return true;}
        return false;
    }

    public JoystickButton getUpDPAD() {return shooterUpButton;}
    public JoystickButton getDownDPAD() {return shooterDownButton;}

    /* Drum */

    public JoystickButton getAgitateButton() {return agitateDrumButton;}
    public JoystickButton getDrumUpButton() {return drumUpButton;}
    public JoystickButton getDrumDownButton() {return drumDownButton;}

    /* Flywheel */

    public boolean getRightDPADB() {
        if(op.getPOV() == 90) {return true;}
        return false;
    }

    public boolean getLeftDPADB() {
        if(op.getPOV() == 270) {return true;}
        return false;
    }

    public JoystickButton getRightDPAD() {return flywheelRightButton;}
    public JoystickButton getLeftDPAD() {return flywheelLeftButton;}
}