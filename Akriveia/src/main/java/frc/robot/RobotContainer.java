package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.commands.auton.FollowGivenPath;
import frc.robot.commands.auton.Slalom;
import frc.robot.commands.teleop.*;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.constants.MasterConstants;
import frc.robot.subsystems.AngledShooter;
import frc.robot.subsystems.BallTransfer;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Intake;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

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
  private static final String galactic_search = "Galactic Search";
  private static final String barrel_racing = "Barrel Racing";
  private static final String bounce_path = "Bounce Path";
  private static final String slalom = "Slalom Path";
  private static final String easy = "Easy";
  private static final String forward = "Forward";
  private static final String forward_and_back = "Forward_and_Back";
  private static final String l_shape = "L_Shape";

  public Trajectory m_Trajectory;

  public ProfiledPIDController thetaController;

  public SwerveControllerCommand swerveControllerCommand;

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
     * Temp code for Integration test of Drum+BallTransfer+Flywheel
     * ############################################################# */
    SmartDashboard.putNumber("DrumOutputPercent", 0);
    SmartDashboard.putNumber("BallTransferOutputPercent", 0);
    SmartDashboard.putNumber("FlywheelOutputPercent", 0);
    SmartDashboard.putBoolean("StopTest06", false);

    configureButtonBindings();

    m_autonChooser = new SendableChooser<>();
    m_autonChooser.addOption("Barrel Racing", barrel_racing);
    m_autonChooser.addOption("Bounce Path", bounce_path);
    m_autonChooser.addOption("Forward", forward);
    m_autonChooser.addOption("Forward and Back", forward_and_back);
    m_autonChooser.addOption("Easy", easy);
    m_autonChooser.addOption("Galactic Search", galactic_search);
    m_autonChooser.addOption("L_Shape", l_shape);
    m_autonChooser.addOption("Slalom", slalom);

  }

  private void configureButtonBindings() {

    // Drivetrain buttons
    m_Controller.getDefenseButton().whenHeld(new SwerveDefense());
    m_Controller.getVisionButton().whenHeld(new SwerveVision( 
      () -> m_Controller.getForward(), 
      () -> m_Controller.getStrafe()));
    m_Controller.getResetGyroButton().whenHeld(new SwerveResetGyro());
    m_Controller.getResetGyroButton().whenPressed(new SwerveResetGyro());
    m_Controller.getLockSpinButton().whenPressed(new SwerveLockedSpin( 
      () -> m_Controller.getForward(), 
      () -> m_Controller.getStrafe()));
    m_Controller.getOrbitButton().whenHeld(new SwerveOrbit(
      () -> m_Controller.getOrbitSpeed(),
      () -> m_Controller.getPOVTransStick()));

    if(m_Controller.getShiftValue() < 0.75) { //REGULAR
      
      if(m_Controller.getPOVUp()) {new AngledShooterUp();}
      if(m_Controller.getPOVDown()) {new AngledShooterDown();}

      // m_Controller.getDrumAgitateButton().whenPressed(new DrumAgitate());
      m_Controller.getDrumCCWSetButton().whenPressed(new DrumCCWSet());
      //m_Controller.getDrumUpButton().whenPressed(/*new DrumInputReset()*/new AngledShooterUp());
      //m_Controller.getDrumDownButton().whenPressed(/*new DrumInputReset()*/new AngledShooterDown());
      m_Controller.getDrumUpButton().whenPressed(new DrumVelocitySpeed2());
      m_Controller.getDrumDownButton().whenActive(new DrumVelocitySlow2());

      m_Controller.getIntakeSpinButton().whenPressed(new IntakeRoll());

      m_Controller.testPOVRight().whenHeld(new FlywheelSpeed());

      if(m_Controller.getPOVRight()) {new FlywheelSpeed();}
      if(m_Controller.getPOVLeft()) {new FlywheelSlow();}

      m_Controller.getTransferStraightButton().whenPressed(new BallTransferStraight());
      m_Controller.getTransferPivotButton().whenActive(new BallTransferPivotAndRoll());

      m_Controller.getShootCMDButton().whenPressed(new DrumTransferFlywheelTest());

      // if(m_Controller.getRTriggerValue() > 0.75) {
      //   new Shoot();
      //   System.out.println("Right Trigger Value" + m_Controller.getRTriggerValue());
      // }

      m_Controller.getShootButton().whenPressed(new Shoot());
      m_Controller.getResetShootButton().whenPressed(new ShootReset());

    } else { //SHIFTED

      // if(m_Controller.getRTriggerValue() > 0.75) {new ShootReset();}
      
    }
  }

  public Command getAutonomousCommand() {

    thetaController = new ProfiledPIDController(
      DrivetrainConstants.P_THETA_CONTROLLER, 0, 0, DrivetrainConstants.THETA_CONTROLLER_CONSTRAINTS);
    thetaController.enableContinuousInput(-Math.PI, Math.PI);

    if(m_autonChooser.getSelected().equals("Galactic Search")) {
      // return new TwoSecondDrive();
    } else if(m_autonChooser.getSelected().equals("Barrel Racing")) {
      // return new ThreeSecondDriveAndShoot();
    } else if(m_autonChooser.getSelected().equals("Bounce")) {
      // return new FarShootAndDrive();
    } else if(m_autonChooser.getSelected().equals("Slalom")) {
      //return new cmd();
      
    }
    return new Slalom(); //Hand Crafted Auton Version
  }

  public void createTrajectory(String path){
    String trajectoryJSON = "paths/" + path + ".wpilib.json";
    m_Trajectory = null;
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      m_Trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
  }
}