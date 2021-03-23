package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.constants.ControllerConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Controller {
    private static Controller instance = new Controller();
    public static Controller getInstance() {return instance;}

    /* Controllers */

    private final Joystick transStick; 
    private final Joystick rotStick; 
    private final XboxController op; 

    /* Drivetrain */

    private final JoystickButton orbitButton; 
    private final JoystickButton dosadoButton;
    private final JoystickButton visionButton;
    private final JoystickButton defenseButton;
    private final JoystickButton fieldCentricButton;
    private final JoystickButton robotCentricButton;
    private final JoystickButton backRobotCentricButton; 
    private final JoystickButton reZeroGyroButton;
    // private final JoystickButton slalomButton = new JoystickButton(transStick, 7);
    
    /* Intake */

    private final JoystickButton intakeSpinButton;

    /* Angled Shooter */

    // private final JoystickButton shooterUpButton; = new JoystickButton(op, op.getPOV(180)); //Up on the D-Pad
    // private final JoystickButton shooterDownButton; = new JoystickButton(op, op.getPOV(0)); //Down on the D-Pad

    /* Drum */

    // private final JoystickButton drumAgitateButton; = new JoystickButton(op, Button.kStart.value);
    private final JoystickButton drumDownButton;
    private final JoystickButton drumUpButton;
    //3 MORE BUTTONS
    
    // private final JoystickButton drumSpinReverseButton;
    // private final JoystickButton drumPreShootAgitateButton;
    private final JoystickButton drumCCWButton;
    private final JoystickButton drumCCWSetButton;

    /* Flywheel */
     
    private final JoystickButton flywheelRightButton;
    // private final JoystickButton flywheelLeftButton;

    /* Transfer */

    private final JoystickButton transferStraightButton;
    private final JoystickButton transferPivotButton;

    /* Command Buttons */

    private final JoystickButton shootCMD;
    // private final JoystickButton shift;

    /* TEMP */

    private final JoystickButton extend;
    private final JoystickButton retract;

    public Controller() {

        // Define control sticks: Translation stick, Rotation stick, and XboxController(aka "op")
        transStick = new Joystick(ControllerConstants.TRANSLATION_STICK_ID);
        rotStick = new Joystick(ControllerConstants.ROTATION_STICK_ID);
        op = new XboxController(ControllerConstants.XBOX_CONTROLLER_ID);;

        // All buttons numbers subject to change
        orbitButton = new JoystickButton(transStick, 1);
        dosadoButton = new JoystickButton(rotStick, 3);
        defenseButton = new JoystickButton(transStick, 2);
        visionButton = new JoystickButton(transStick, 3); //Should be part of the xbox controller later
        fieldCentricButton = new JoystickButton(transStick, 4);
        robotCentricButton = new JoystickButton(transStick, 5);
        backRobotCentricButton = new JoystickButton(transStick, 6);
        reZeroGyroButton = new JoystickButton(rotStick, 4);
        // slalomButton = new JoystickButton(transStick, 7);

           
        /* Intake */

        intakeSpinButton = new JoystickButton(op, Button.kA.value);

        /* Angled Shooter */

        // private final JoystickButton shooterUpButton = new JoystickButton(op, op.getPOV(180)); //Up on the D-Pad
        // shooterDownButton = new JoystickButton(op, op.getPOV(0)); //Down on the D-Pad

        /* Drum */

        // private final JoystickButton drumAgitateButton = new JoystickButton(op, Button.kStart.value);
        drumDownButton = new JoystickButton(op, Button.kBumperLeft.value);
        drumUpButton = new JoystickButton(op, Button.kBumperRight.value);
        //3 MORE BUTTONS
    
        // drumSpinReverseButton = new JoystickButton(op, Button.kA.value); //Layer 2
        // drumPreShootAgitateButton = new JoystickButton(op, Button.kStart.value); //Layer 2
        drumCCWButton = new JoystickButton(op, Button.kBack.value); //Layer 1 but not usable
        drumCCWSetButton = new JoystickButton(op, Button.kBack.value); //Layer 2 but usable

        /* Flywheel */
     
        flywheelRightButton = new JoystickButton(op, Button.kY.value); //Right on the D-Pad
        // flywheelLeftButton = new JoystickButton(op, Button.kX.value); //Left on the D-Pad

        /* Transfer */

        transferStraightButton = new JoystickButton(op, Button.kA.value);
        transferPivotButton = new JoystickButton(op, Button.kB.value);

        /* Command Buttons */

        shootCMD = new JoystickButton(op, Button.kStart.value);
        //shift = new JoystickButton(op, Trig);

        /* TEMP */

        extend = new JoystickButton(op, Button.kBumperLeft.value);
        retract = new JoystickButton(op, Button.kBumperRight.value);
    }


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
    
    
    public int getPOVTransStick() {
        return transStick.getPOV();
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

    // public JoystickButton getDrumAgitateButton() {return drumAgitateButton;}
    public JoystickButton getDrumUpButton() {return drumUpButton;}
    public JoystickButton getDrumDownButton() {return drumDownButton;}
    public JoystickButton getDrumCCWSetButton() {return drumCCWSetButton;}

    /* Flywheel */

    // public JoystickButton getFlywheelLeft() {
    //     return flywheelLeftButton;
    // }

    public JoystickButton getFlywheelRight() {
        return flywheelRightButton;
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



