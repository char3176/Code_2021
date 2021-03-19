package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.subsystems.AngledShooter;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.BallTransfer;
import frc.robot.subsystems.Flywheel;
import frc.robot.commands.auton.Slalom;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.teleop.*;


public class RobotContainer {
  public Intake m_Intake;
  private Controller m_Controller;
  private Compressor m_Compressor;
  private AngledShooter m_AngledShooter;
  private Drum m_Drum;
  private Drivetrain m_Drivetrain;
  private BallTransfer m_BallTransfer;
  private Flywheel m_Flywheel;

  private SendableChooser<String> m_autonChooser;
  private static final String auto1 = "Galactic Search";
  private static final String auto2 = "Barrel Racing";
  private static final String auto3 = "Bounce Path";
  private static final String auto4 = "Slalom Path";

  public RobotContainer() {
    m_Compressor = new Compressor();
    m_Compressor.start();

    m_Controller = Controller.getInstance();

    m_Intake = Intake.getInstance();
    m_Drum = Drum.getInstance();
    m_BallTransfer = BallTransfer.getInstance();
    m_Flywheel = Flywheel.getInstance();
    m_AngledShooter = AngledShooter.getInstance();

    m_Drivetrain = Drivetrain.getInstance();
    m_Drivetrain.setDefaultCommand(new SwerveDrive(
      () -> m_Controller.getForward(), 
      () -> m_Controller.getStrafe(),
      () -> m_Controller.getSpin(),
      () -> m_Controller.isFieldCentricButtonPressed(),
      () -> m_Controller.isRobotCentricButtonPressed(),
      () -> m_Controller.isBackRobotCentricButtonPressed()));

    /* ##############################################################
     * BEGIN:  Temp code for Integration test of Drum+BallTransfer+Flywheel+AngledShooter
     * ############################################################# */
    SmartDashboard.putNumber("DrumOutputPercent", 0);
    SmartDashboard.putNumber("BallTransferOutputPercent", 0);
    SmartDashboard.putNumber("FlywheelOutputPercent", 0);
    SmartDashboard.putBoolean("StopTest06", false);

    configureButtonBindings();
  }

  private void configureButtonBindings() {

    // Drivetrain buttons
    m_Controller.getDefenseButton().whenHeld(new SwerveDefense());
    m_Controller.getVisionButton().whenHeld(new SwerveVision( 
      () -> m_Controller.getForward(), 
      () -> m_Controller.getStrafe()));
    m_Controller.getReZeroGyroButton().whenHeld(new SwerveReZeroGyro());


    while(!m_Controller.getShift()) { //REGULAR
      if(m_Controller.getPOVLocation() == 0) {new AngledShooterUp();}
      else if(m_Controller.getPOVLocation() == 180) {new AngledShooterDown();}
      // m_Controller.getUpDPAD().whenPressed(new AngledShooterUp());
      // m_Controller.getDownDPAD().whenPressed(new AngledShooterDown());

      // m_Controller.getDrumAgitateButton().whenPressed(new DrumAgitate());
      m_Controller.getDrumCCWSetButton().whenPressed(new DrumCCWSet());
      m_Controller.getDrumUpButton().whenPressed(new DrumVelocitySpeed());
      m_Controller.getDrumDownButton().whenPressed(new DrumVelocitySlow());

      m_Controller.getIntakeSpinButton().whenPressed(new IntakeRoll());
      // m_Controller.getIntakeSwitchButton().whenPressed(new IntakeSwitch());

      if(m_Controller.getPOVLocation() == 90) {new FlywheelSpeed();}
      else if(m_Controller.getPOVLocation() == 270) {new FlywheelSlow();}
      // m_Controller.getLeftDPAD().whenPressed(new FlywheelSlow());
      // m_Controller.getRightDPAD().whenPressed(new FlywheelSpeed());

      m_Controller.getTransferStraightButton().whenPressed(new BallTransferStraight());
      m_Controller.getTransferPivotButton().whenPressed(new BallTransferPivotAndRoll());

      m_Controller.getShootCMDButton().whenPressed(new DrumTransferFlywheelTest());

      // m_Controller.getFlywheelLeft().whenPressed(new FlywheelSlow());
      m_Controller.getFlywheelRight().whenPressed(new FlywheelSpeed());
    }
  }

  public Command getAutonomousCommand() {
    if(m_autonChooser.getSelected().equals("Galactic Search")) {
      // return new TwoSecondDrive();
    } else if(m_autonChooser.getSelected().equals("Barrel Racing")) {
      // return new ThreeSecondDriveAndShoot();
    } else if(m_autonChooser.getSelected().equals("Bounce Path")) {
      // return new FarShootAndDrive();
    } else if(m_autonChooser.getSelected().equals("Slalom Path")) {
      //return new cmd();
    }
    return new Slalom(); //Hand Crafted Auton Version
  }
}