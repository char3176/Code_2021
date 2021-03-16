package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
// import edu.wpi.first.wpilibj.buttons.POVButton;
// import edu.wpi.first.wpilibj.GenericHID.Hand;
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
    private final JoystickButton visionButton = new JoystickButton(op, Button.kY.value);
    private final JoystickButton defenseButton = new JoystickButton(transStick, 2);
    private final JoystickButton fieldCentricButton = new JoystickButton(transStick, 4);
    private final JoystickButton robotCentricButton = new JoystickButton(transStick, 5);
    private final JoystickButton backRobotCentricButton = new JoystickButton(transStick, 6);
    private final JoystickButton reZeroGyroButton = new JoystickButton(rotStick, 4);
    // private final JoystickButton slalomButton = new JoystickButton(transStick, 7);
    
    /* Intake */

    private final JoystickButton intakeSwitchButton = new JoystickButton(op, Button.kB.value);
    private final JoystickButton intakeSpinButton = new JoystickButton(op, Button.kA.value);

    /* Angled Shooter */

    // private final JoystickButton shooterUpButton = new JoystickButton(op, op.getPOV(180)); //Up on the D-Pad
    // private final JoystickButton shooterDownButton = new JoystickButton(op, op.getPOV(0)); //Down on the D-Pad

    /* Drum */

    private final JoystickButton drumAgitateButton = new JoystickButton(op, Button.kStart.value);
    private final JoystickButton drumDownButton = new JoystickButton(op, Button.kBumperLeft.value);
    private final JoystickButton drumUpButton = new JoystickButton(op, Button.kBumperRight.value);
    //3 MORE BUTTONS
    
    private final JoystickButton drumSpinReverseButton = new JoystickButton(op, Button.kA.value); //Layer 2
    private final JoystickButton drumPreShootAgitateButton = new JoystickButton(op, Button.kStart.value); //Layer 2
    private final JoystickButton drumCCWButton = new JoystickButton(op, Button.kBack.value); //Layer 1 but not usable
    private final JoystickButton drumCCWSetButton = new JoystickButton(op, Button.kBack.value); //Layer 2 but usable

    /* Flywheel */
     
    // private final JoystickButton flywheelRightButton = new JoystickButton(op, op.getPOV(90)); //Right on the D-Pad
    // private final JoystickButton flywheelLeftButton = new JoystickButton(op, op.getPOV(270)); //Left on the D-Pad

    /* Transfer */

    private final JoystickButton transferStraightButton = new JoystickButton(op, Button.kA.value);
    private final JoystickButton transferPivotButton = new JoystickButton(op, Button.kB.value);

    /* Command Buttons */

    private final JoystickButton shootCMD = new JoystickButton(op, Button.kX.value);
    // private final JoystickButton shift = new JoystickButton(op, Trig);

    /* TEMP */

    private final JoystickButton extend = new JoystickButton(op, Button.kBumperLeft.value);
    private final JoystickButton retract = new JoystickButton(op, Button.kBumperRight.value);
    public boolean getExtend() {return extend.get();}
    public boolean getRetract() {return retract.get();}

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
    // public JoystickButton getSlalomButton() {return slalomButton;}
    public boolean isFieldCentricButtonPressed() {return fieldCentricButton.get();}
    public boolean isRobotCentricButtonPressed() {return robotCentricButton.get();}
    public boolean isBackRobotCentricButtonPressed() {return backRobotCentricButton.get();}

    /* Intake Buttons */

    public JoystickButton getIntakeSpinButton() {return intakeSpinButton;}
    public JoystickButton getIntakeSwitchButton() {return intakeSwitchButton;}

    /* Angled Shooter*/

    // public boolean getUpDPADB() {
    //     if(op.getPOV() == 180) {return true;}
    //     return false;
    // }

    // public boolean getDownDPADB() {
    //     if(op.getPOV() == 0) {return true;}
    //     return false;
    // }

    // public JoystickButton getUpDPAD() {return shooterUpButton;}
    // public JoystickButton getDownDPAD() {return shooterDownButton;}

    /* Drum */

    public JoystickButton getDrumAgitateButton() {return drumAgitateButton;}
    public JoystickButton getDrumUpButton() {return drumUpButton;}
    public JoystickButton getDrumDownButton() {return drumDownButton;}
    public JoystickButton getDrumCCWSetButton() {return drumCCWSetButton;}

    /* Flywheel */

    public boolean getRightDPAD() {
        if(op.getPOV() == 90) {return true;}
        return false;
    }

    public boolean getLeftDPAD() {
        if(op.getPOV() == 270) {return true;}
        return false;
    }

    // public JoystickButton getRightDPAD() {return flywheelRightButton;}
    // public JoystickButton getLeftDPAD() {return flywheelLeftButton;}

    /* Transfer */

    public JoystickButton getTransferStraightButton() {return transferStraightButton;}
    public JoystickButton getTransferPivotButton() {return transferPivotButton;}

    /* Command Buttons */

    public JoystickButton getShootCMDButton() {return shootCMD;}

    /* Button Get Methods */

    public int getPOVLocation() {return op.getPOV();}
    /**
     * @return If the Left Trigger is pressed for half and then shifts the controls
     */
    public boolean getShift() {
        if(op.getTriggerAxis(Hand.kLeft) >= 0.5) {return true;}
        return false;
    }



    /* ##############################################################
     * BEGIN: EXAMPLE USAGE OF DoubleButton / LoneButton classes
     * ############################################################## 
     *
     *  // This example uses 3 buttons:  blueButton, rightBumperButton, and leftBumperButton
     *  // DoubleButton pairs together two "normal" buttons (ie blueButton & leftBummperButton in
     *  // this example) into one "DoubleButton" object named myNewButton.
     *  // However, the logic of DoubleButton.java could potentially foul-up independent 
     *  // operation of blueButton as a sole button.   
     *  // So we "re-create" (ie reassign) blueButton as a LoneButton object which checks that 
     *  // none of the modifer buttons (ie rightBumperButton or leftBumperButton) are also 
     *  // being presssed.  If that is true, then blueButton (or more specifically loneBlueButton) activate
     *  // "MySecondCommand() when pressed (ie loneBlueButton.whenPressed() etc etc).
     *  
     * // include the DoubleButton class
     *  import frc.robot.util.DoubleButton.java
     * 
     * // Create an object (named myjoystick) of Joystick class
     *  private Joystick myjoystick = new Joystick(Joystick_ID_Number_from_Drivestation); 
     *  
     * // Create an object (named myNewButton in this example) using DoubleButton class.
     * // Pass the "myjoystick" object, and the numbers of the first and second button on the joystick
     * // to the DoubleButton(). 
     *  private Button myNewButton = new DoubleButton(myjoystick, BLUE_BUTTON_ID, LEFT_BUMPER_ID);
     *
     * // MyCommand will now only "fire" when both button numbers 1 & 2 are pressed together
     *   myNewButton.whenPressed(new MyCommand());
     *
     * 
     * // NOW CREATE LONE BUTTONS for buttons blueButton, rightBumperButton, & leftBumperButton using the LoneButton class
     *   JoystickButton blueButton = new JoystickButton(myjoystick, BLUE_BUTTON_ID);
     *   JoystickButton rightBumperButton = new JoystickButton(myjoystick, RIGHT_BUMPER_ID);
     *   JoystickButton leftBumperButton = new JoystickButton(myjoystick, LEFT_BUMPER_ID);

     *   LoneButton loneBlueButton = new LoneButton(blueButton, leftBumperButton, rightBumperButton);
     * 
     *   loneBlueButton.whenPressed(new MySecondCommand());
     *  
     * 
     * ##############################################################
     * END: EXAMPLE USAGE OF DoubleButton / LoneButton classes
     * ############################################################## */

}



