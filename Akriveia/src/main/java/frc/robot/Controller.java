package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
//import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.constants.ControllerConstants;

public class Controller {
    private static Controller instance = new Controller();
    public static Controller getInstance() {return instance;}

    /* Controllers */

    private final Joystick transStick; 
    private final Joystick rotStick; 
    private final XboxController op; 

    /* Drivetrain */
    //TODO: Talk about if we should name the buttons by function (Would need two for the same button if we do so) or name the buttons by their name (Kyle - I just wanted a reminder to talk about this) 
    //i.e. An IntakeRoll button and an reverseIntakeRoll button or an 'A' Button
    
    private final JoystickButton orbitButton;
    private final JoystickButton dosadoButton;
    private final JoystickButton visionButton;
    private final JoystickButton defenseButton;
    private final JoystickButton fieldCentricButton;
    private final JoystickButton robotCentricButton;
    private final JoystickButton backRobotCentricButton; 
    private final JoystickButton resetGyroButton;
    private final JoystickButton lockSpinButton;
    // private final JoystickButton slalomButton = new JoystickButton(transStick, 7);
    
    /* Intake */

    private final JoystickButton intakeSpinButton;

    /* Drum */

    // private final JoystickButton drumAgitateButton; = new JoystickButton(op, Button.kStart.value);
    private final Trigger drumDownButton;
    private final JoystickButton drumUpButton;
    //3 MORE BUTTONS
    
    // private final JoystickButton drumSpinReverseButton;
    // private final JoystickButton drumPreShootAgitateButton;
    private final JoystickButton drumCCWButton;
    private final JoystickButton drumCCWSetButton;

    /* Transfer */

    private final JoystickButton transferStraightButton;
    private final Trigger transferPivotButton;

    /* Command Buttons */

    private final JoystickButton shootCMD;
    // private final JoystickButton shift;

    /* TEMP */

    //private final JoystickButton extend;
    //private final JoystickButton retract;
    private final JoystickButton shootButton;
    private final JoystickButton resetShootButton;

    private final POVButton dpadUp;

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
        resetGyroButton = new JoystickButton(rotStick, 8);
        lockSpinButton = new JoystickButton(rotStick, 9);
        // slalomButton = new JoystickButton(transStick, 7);

           
        /* Intake */

        intakeSpinButton = new JoystickButton(op, Button.kA.value);

        /* Drum */

        // private final JoystickButton drumAgitateButton = new JoystickButton(op, Button.kStart.value);
        // B - Drum Speed               | Drum Slow 
        drumDownButton = new JoystickButton(op, Button.kB.value).and(new JoystickButton(op, Button.kBumperLeft.value));
        drumUpButton = new JoystickButton(op, Button.kB.value);
        //3 MORE BUTTONS
    
        // drumSpinReverseButton = new JoystickButton(op, Button.kA.value); //Layer 2
        // drumPreShootAgitateButton = new JoystickButton(op, Button.kStart.value); //Layer 2
        drumCCWButton = new JoystickButton(op, Button.kBack.value); //Layer 1 but not usable
        drumCCWSetButton = new JoystickButton(op, Button.kBack.value); //Layer 2 but usable

        /* Transfer */
            // x-button = Pivot, x-button + LBummper = transferStraight
        transferPivotButton = new JoystickButton(op, Button.kX.value).and(new JoystickButton(op, Button.kBumperLeft.value));
        transferStraightButton = new JoystickButton(op, Button.kX.value);
        //transferPivotButton = new JoystickButton(op, Button.kX.value);

        /* Command Buttons */

        shootCMD = new JoystickButton(op, Button.kStart.value);
        //shift = new JoystickButton(op, Trig);

        /* TEMP */

        //extend = new JoystickButton(op, Button.kBumperLeft.value);
        //retract = new JoystickButton(op, Button.kBumperRight.value);
        shootButton = new JoystickButton(op, Button.kBumperRight.value); 
        resetShootButton = new JoystickButton(op, Button.kY.value);

        dpadUp = new POVButton(op, 90);
    }


    //public boolean getExtend() {return extend.get();}
    //public boolean getRetract() {return retract.get();}

    /* Swerve Axis Data */

    public double getForward() { 
        if (Math.abs(-transStick.getY()) < 0.06) {
            return 0.0;
        } else {
            return ControllerConstants.FORWARD__AXIS_INVERSION * Math.pow(transStick.getY(), 1); }
    }
    
    public double getStrafe() { 
        if (Math.abs(transStick.getX()) < 0.06) {
            return 0.0;
        } else { 
            return ControllerConstants.STRAFE_AXIS_INVERSION * Math.pow(transStick.getX(), 1); }
    }

    public double getSpin() { 
        if (Math.abs(rotStick.getX()) < 0.06) {
            return 0.0;
        } else {
            return ControllerConstants.SPIN_AXIS_INVERSION * (Math.pow(rotStick.getX(), 1) / 7.0); }
    } 

    public double getOrbitSpeed() { 
        if (Math.abs(rotStick.getY()) < 0.06) {
            return 0.0;
        } else {
            return Math.pow(rotStick.getY(), 1) / 7.0; }
    } 
    
    
    public int getPOVTransStick() {
        return transStick.getPOV();
    }

    /* Drivetrain Buttons */

    public JoystickButton getOrbitButton() {return orbitButton;}
    public JoystickButton getDosadoButton() {return dosadoButton;}
    public JoystickButton getVisionButton() {return visionButton;}
    public JoystickButton getDefenseButton() {return defenseButton;}
    public JoystickButton getResetGyroButton() {return resetGyroButton;}
    public JoystickButton getLockSpinButton() {return lockSpinButton;}
    public boolean isGetLockSpinButtonPressed() {return lockSpinButton.get();}
    public boolean isFieldCentricButtonPressed() {return fieldCentricButton.get();}
    public boolean isRobotCentricButtonPressed() {return robotCentricButton.get();}
    public boolean isBackRobotCentricButtonPressed() {return backRobotCentricButton.get();}

    /* Intake Buttons */

    public JoystickButton getIntakeSpinButton() {return intakeSpinButton;}

    /* Drum */

    //public JoystickButton getDrumAgitateButton() {return drumAgitateButton;}
    public JoystickButton getDrumUpButton() {return drumUpButton;}
    public Trigger getDrumDownButton() {return drumDownButton;}
    public JoystickButton getDrumCCWSetButton() {return drumCCWSetButton;}

    /* Transfer */

    public JoystickButton getTransferStraightButton() {return transferStraightButton;}
    public Trigger getTransferPivotButton() {return transferPivotButton;}

    /* Command Buttons */

    public JoystickButton getShootCMDButton() {return shootCMD;}

    /* Triggers and DPAD Get Methods */

    public boolean getPOVRight() { if (op.getPOV() == 90) {return true;} else { return false;}}    //Right
    public POVButton testPOVRight() {return dpadUp;}
    public boolean getPOVLeft() {return op.getPOV() == 270;}    //Left
    public boolean getPOVUp() {return op.getPOV() == 0;}        //Up
    public boolean getPOVDown() {return op.getPOV() == 180;}    //Down
    
    public double getShiftValue() {return op.getTriggerAxis(Hand.kLeft);}
    public double getRTriggerValue() {return op.getTriggerAxis(Hand.kRight);}
  

    public JoystickButton getShootButton() {
        return shootButton;
    }

    public JoystickButton getResetShootButton() {
        return resetShootButton;
    }


    //new JoystickButton(exampleController, XBoxController.Button.kX.value)
    //    .and(new JoystickButton(exampleController, XboxController.Button.kY.value))
    //    .whenActive(new ExampleCommand());

       // https://docs.wpilib.org/en/latest/docs/software/commandbased/binding-commands-to-triggers.html

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

/**
 * Unshifted:                   | Shifted:
 * A - Intake Roll              | Reverse Intake Roll
 * B - Drum Speed               | Drum Slow 
 * X - BallTransferPivotAndRoll | BallTransferPivot 
 * Y - Harvest                  | HarvestRest
 * Start - Agitate Drum         | Agitate Drum Pre Shoot
 * Back - CCW Drum              | 
 * D-Pad Up - Shooter Up        |
 * D-Pad Down - Shooter Down    |
 * D-Pad Left - Flywheel Slow   |
 * D-Pad Right - Flywheel Speed |
 * Left Joy Button -            |
 * Right Joy Button -           |
 * Left Joy Y -                 |
 * Left Joy X -                 |
 * Right Joy Y -                |
 * Left Bumper -  Hold to Shift | Release to Unshift 
 * Right Bumper - Shoot         | ShootReset
 * Left Trigger -               | 
 * Right Trigger -         | 
 * 
 * make a Harvest cmd that executes 1) CCW Drum, 2) Intake Roll
 * make a HarvestReset cmd that executes 1) Intake Reverse, 2) Drum Agitate
 *  
 * 
 * whenActive/whenPressed
 * whileActiveContinuous/whileHeld
 * whileActiveOnce/whenHeld
 * whenInactive/whenReleased
 * toggleWhenActive/toggleWhenPressed
 */

}