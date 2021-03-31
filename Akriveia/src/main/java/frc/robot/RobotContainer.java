package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
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
import frc.robot.subsystems.PowerManagement;

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
  private PowerManagement m_PowerManagement;

  private SendableChooser<String> autonChooser;
  private static final String galactic_search_a = "Galactic Search A";
  private static final String galactic_search_b = "Galactic Search B";
  private static final String barrel_racing = "Barrel Racing";
  private static final String bounce_path = "Bounce Path";
  private static final String slalom = "Slalom Path";
  private static final String easy = "Easy";
  private static final String forward = "Forward";
  private static final String forward_and_back = "Forward_and_Back";
  private static final String l_shape = "L_Shape";

  public Trajectory trajectory;

  public ProfiledPIDController thetaController;

  public SwerveControllerCommand m_SwerveControllerCommand;

  public RobotContainer() {
    m_Compressor = new Compressor();
    m_Compressor.start();

    m_Controller = Controller.getInstance();

    m_Intake = Intake.getInstance();
    m_Drum = Drum.getInstance();
    m_BallTransfer = BallTransfer.getInstance();
    m_Flywheel = Flywheel.getInstance();
    m_AngledShooter = AngledShooter.getInstance();
    m_PowerManagement = PowerManagement.getInstance();

    m_PowerManagement.clearFaults();

    m_Drivetrain = Drivetrain.getInstance();
    m_Drivetrain.setDefaultCommand(new SwerveDrive(
      () -> m_Controller.getForward(), 
      () -> m_Controller.getStrafe(),
      () -> m_Controller.getSpin(),
      () -> m_Controller.isFieldCentricButtonPressed(),
      () -> m_Controller.isRobotCentricButtonPressed(),
      () -> m_Controller.isBackRobotCentricButtonPressed()));

    configureButtonBindings();

    autonChooser = new SendableChooser<>();
    autonChooser.addOption("Barrel Racing", barrel_racing);
    autonChooser.addOption("Bounce Path", bounce_path);
    autonChooser.addOption("Forward", forward);
    autonChooser.addOption("Forward and Back", forward_and_back);
    autonChooser.addOption("Easy", easy);
    autonChooser.addOption("Galactic Search A", galactic_search_a);
    autonChooser.addOption("Galactic Search B", galactic_search_b);
    autonChooser.addOption("L_Shape", l_shape);
    autonChooser.addOption("Slalom", slalom);
    SmartDashboard.putData("Auton Choice", autonChooser);

  }

  private void configureButtonBindings() {

    // Drivetrain buttons
    m_Controller.getDefenseButton().whenHeld(new SwerveDefense());
    m_Controller.getVisionButton().whenHeld(new SwerveVision( 
      () -> m_Controller.getForward(), 
      () -> m_Controller.getStrafe()));
    m_Controller.getResetGyroButton().whenHeld(new SwerveResetGyro());
    m_Controller.getResetGyroButton().whenPressed(new SwerveResetGyro());
    m_Controller.getLockSpinButton().whenPressed(new SwerveLockedSpin());
    m_Controller.getOrbitButton().whenHeld(new SwerveOrbit(
      () -> m_Controller.getOrbitSpeed(),
      () -> m_Controller.getPOVTransStick()));

    m_Controller.getIntakeSpinButton().whenActive(new IntakeRoll());
    m_Controller.getIntakeReverseButton().whenActive(new IntakeReverse());
  
    m_Controller.getDrumUpButton().whenActive(new DrumVelocitySpeed());
    m_Controller.getDrumDownButton().whenActive(new DrumVelocitySlow());
  
    m_Controller.getTransferStraightButton().whenActive(new BallTransferStraight());
    m_Controller.getTransferPivotButton().whenActive(new BallTransferPivotAndRoll());
  
    m_Controller.getIntakeHarvestButton().whenActive(new IntakeHarvest());
    m_Controller.getIntakeHarvestResetButton().whenActive(new IntakeHarvestReset());
  
    // m_Controller.getDrumAgitateButton().whenActive(new DrumAgitate());
    m_Controller.getDrumAgitatePreShootButton().whenActive(new DrumAgitatePreShoot());
  
    m_Controller.getDrumInputResetButton().whenActive(new DrumInputReset());
    m_Controller.getDrumCCWButton().whenActive(new DrumCCW());
  
    m_Controller.getPOVUp().whenHeld(new AngledShooterUp());
    m_Controller.getPOVDown().whenHeld(new AngledShooterDown());
    m_Controller.getPOVLeft().whenHeld(new FlywheelSlow());
    m_Controller.getPOVRight().whenHeld(new FlywheelSpeed());

    m_Controller.getAngledShooterOffButton().whenActive(new AngledShooterOff());
  
    m_Controller.getShootButton().whenActive(new Shoot());
    m_Controller.getResetShootButton().whenActive(new ShootReset());
    
      /*
    m_Controller.getIntakeSpinButton().whenPressed(new IntakeRoll());
    m_Controller.getIntakeReverseButton().whenActive(new IntakeReverse());

    m_Controller.getDrumUpButton().whenPressed(new DrumVelocitySpeed());
    m_Controller.getDrumDownButton().whenActive(new DrumVelocitySlow());

    m_Controller.getTransferStraightButton().whenPressed(new BallTransferStraight());
    m_Controller.getTransferPivotButton().whenActive(new BallTransferPivotAndRoll());

    m_Controller.getIntakeHarvestButton().whenPressed(new IntakeHarvest());
    m_Controller.getIntakeHarvestResetButton().whenActive(new IntakeHarvestReset());

    m_Controller.getDrumAgitateButton().whenPressed(new DrumAgitate());
    m_Controller.getDrumAgitatePreShootButton().whenActive(new DrumAgitatePreShoot());

    m_Controller.getDrumCCWButton().whenPressed(new DrumCCW());

    m_Controller.getPOVUp().whenHeld(new AngledShooterUp());
    m_Controller.getPOVDown().whenHeld(new AngledShooterDown());
    m_Controller.getPOVLeft().whenHeld(new FlywheelSlow());
    m_Controller.getPOVRight().whenHeld(new FlywheelSpeed());

    m_Controller.getShootButton().whenPressed(new Shoot());
    m_Controller.getResetShootButton().whenActive(new ShootReset()); */
  }

  public Command getAutonomousCommand() {

        /*TrajectoryConfig config =
        new TrajectoryConfig(
               4.468,
                1.631)
            // Add kinematics to ensure max speed is actually obeyed
            .setKinematics(DrivetrainConstants.DRIVE_KINEMATICS);

    */

    thetaController = new ProfiledPIDController(
      DrivetrainConstants.P_THETA_CONTROLLER, 0, 0, DrivetrainConstants.THETA_CONTROLLER_CONSTRAINTS);
    thetaController.enableContinuousInput(-Math.PI, Math.PI);

    if(autonChooser.getSelected().equals("slalom")) {
      createTrajectory("slalom");
      new FollowGivenPath(trajectory);
    }
    else if(autonChooser.getSelected().equals("easy")) {
      createTrajectory("easy");
      return new FollowGivenPath(trajectory);
    } 
    else if(autonChooser.getSelected().equals("forward")) {
      createTrajectory("forward");
      return new FollowGivenPath(trajectory);
    }
    else if (autonChooser.getSelected().equals("barrel_racing")) {
      createTrajectory("barrel_racing");
      return new FollowGivenPath(trajectory);
    }
    else if(autonChooser.getSelected().equals("bouncePath")) {
      createTrajectory("bounce");
      return new FollowGivenPath(trajectory);
    }
    else if (autonChooser.getSelected().equals("galactic_search_a")) {
      createTrajectory("galactic_search.pathA_redBalls");
      return new FollowGivenPath(trajectory);
    }
    else if (autonChooser.getSelected().equals("galactic_search_b")) {
      createTrajectory("galactic_search>pathA_redBalls");
      return new FollowGivenPath(trajectory);
    }
    else if(autonChooser.getSelected().equals("forward_and_back")) {
      createTrajectory("forward_and_back");
      return new FollowGivenPath(trajectory);
    }
    else if(autonChooser.getSelected().equals("L_shape")) {
      createTrajectory("L_shape");
      return new FollowGivenPath(trajectory);
    }
    //else if (autonChooser.getSelected().equals("")) {
      /*String trajectoryJSON = "paths/forward.wpilib.json";
   trajectory = null;
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }*/

  
    TrajectoryConfig config = 
      new TrajectoryConfig(
      4.48,
      1.631)
      // Add kinematics to ensure max speed is actually obeyed
      .setKinematics(DrivetrainConstants.DRIVE_KINEMATICS);

    // An example trajectory to follow.  All units in meters.
    /*
    Trajectory trajectory =
        TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(new Translation2d(1, 0), new Translation2d(2, 0)),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(3, 0, new Rotation2d(0)),
            config); 
      */
    
    
    Pose2d newpose = trajectory.getInitialPose();
    m_Drivetrain.resetOdometry(trajectory.getInitialPose());
    m_SwerveControllerCommand =
      new SwerveControllerCommand(
      trajectory,
      m_Drivetrain::getCurrentPose, 
      DrivetrainConstants.DRIVE_KINEMATICS,

        // Position controllers
      new PIDController(DrivetrainConstants.P_X_Controller, 0, 0),
      new PIDController(DrivetrainConstants.P_Y_Controller, 0, 0),
      thetaController,
      m_Drivetrain::setModuleStates, //Not sure about setModuleStates
      m_Drivetrain);

    if(m_SwerveControllerCommand == null) { 
       System.out.println("###########  ERROR: RobotContainer.getAutonoumousCommand() m_SwerveControllerCommand is null. #########"); 
    }

    // Reset odometry to the starting pose of the trajectory.
    m_Drivetrain.resetOdometry(trajectory.getInitialPose());

    if(m_SwerveControllerCommand == null) {
       System.out.println("###########  ERROR: RobotContainer.getAutonoumousCommand() m_SwerveControllerCommand is null #2 #########"); 
    }
    if(m_Drivetrain == null) { 
       System.out.println("###########  ERROR: RobotContainer.getAutonoumousCommand() m_Drivetrain is null. #########"); 
    }

    return m_SwerveControllerCommand.andThen(() -> m_Drivetrain.drive(0, 0, 0)); //NULL POINTER ex TODO:FIX PRI
  }
 
  

  public void createTrajectory(String path){
    String trajectoryJSON = "paths/" + path + ".wpilib.json";
    //trajectory = null;
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
  }
}