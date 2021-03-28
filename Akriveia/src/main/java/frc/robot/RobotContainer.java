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
import frc.robot.subsystems.PowerManagement;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class RobotContainer {
  public Intake intake;
  private Controller controller;
  private Compressor compressor;
  private AngledShooter angledShooter;
  private Drum drum;
  private Drivetrain drivetrain;
  private BallTransfer ballTransfer;
  private Flywheel flywheel;
  private PowerManagement powerManagement;

  private SendableChooser<String> autonChooser;
  private static final String galactic_search = "Galactic Search";
  private static final String barrel_racing = "Barrel Racing";
  private static final String bounce_path = "Bounce Path";
  private static final String slalom = "Slalom Path";
  private static final String easy = "Easy";
  private static final String forward = "Forward";
  private static final String forward_and_back = "Forward_and_Back";
  private static final String l_shape = "L_Shape";

  public Trajectory trajectory;

  public ProfiledPIDController thetaController;

  public SwerveControllerCommand swerveControllerCommand;

  public RobotContainer() {
    compressor = new Compressor();
    compressor.start();

    controller = Controller.getInstance();

    intake = Intake.getInstance();
    drum = Drum.getInstance();
    ballTransfer = BallTransfer.getInstance();
    flywheel = Flywheel.getInstance();
    angledShooter = AngledShooter.getInstance();
    powerManagement = PowerManagement.getInstance();

    powerManagement.clearFaults();

    drivetrain = Drivetrain.getInstance();
    drivetrain.setDefaultCommand(new SwerveDrive(
      () -> controller.getForward(), 
      () -> controller.getStrafe(),
      () -> controller.getSpin(),
      () -> controller.isFieldCentricButtonPressed(),
      () -> controller.isRobotCentricButtonPressed(),
      () -> controller.isBackRobotCentricButtonPressed()));

    configureButtonBindings();

    autonChooser = new SendableChooser<>();
    autonChooser.addOption("Barrel Racing", barrel_racing);
    autonChooser.addOption("Bounce Path", bounce_path);
    autonChooser.addOption("Forward", forward);
    autonChooser.addOption("Forward and Back", forward_and_back);
    autonChooser.addOption("Easy", easy);
    autonChooser.addOption("Galactic Search", galactic_search);
    autonChooser.addOption("L_Shape", l_shape);
    autonChooser.addOption("Slalom", slalom);

  }

  private void configureButtonBindings() {

    // Drivetrain buttons
    controller.getDefenseButton().whenHeld(new SwerveDefense());
    controller.getVisionButton().whenHeld(new SwerveVision( 
      () -> controller.getForward(), 
      () -> controller.getStrafe()));
    controller.getResetGyroButton().whenHeld(new SwerveResetGyro());
    controller.getResetGyroButton().whenPressed(new SwerveResetGyro());
    controller.getLockSpinButton().whenPressed(new SwerveLockedSpin());
    controller.getOrbitButton().whenHeld(new SwerveOrbit(
      () -> controller.getOrbitSpeed(),
      () -> controller.getPOVTransStick()));

    controller.getIntakeSpinButton().whenActive(new IntakeRoll());
    controller.getIntakeReverseButton().whenActive(new IntakeReverse());
  
    controller.getDrumUpButton().whenActive(new DrumVelocitySpeed());
    controller.getDrumDownButton().whenActive(new DrumVelocitySlow());
  
    controller.getTransferStraightButton().whenActive(new BallTransferStraight());
    controller.getTransferPivotButton().whenActive(new BallTransferPivotAndRoll());
  
    controller.getIntakeHarvestButton().whenActive(new IntakeHarvest());
    controller.getIntakeHarvestResetButton().whenActive(new IntakeHarvestReset());
  
    controller.getDrumAgitateButton().whenActive(new DrumAgitate());
    controller.getDrumAgitatePreShootButton().whenActive(new DrumAgitatePreShoot());
  
    controller.getDrumInputResetButton().whenActive(new DrumInputReset());
    controller.getDrumCCWButton().whenActive(new DrumCCW());
  
    controller.getPOVUp().whenHeld(new AngledShooterUp());
    controller.getPOVDown().whenHeld(new AngledShooterDown());
    controller.getPOVLeft().whenHeld(new FlywheelSlow());
    controller.getPOVRight().whenHeld(new FlywheelSpeed());
  
    controller.getShootButton().whenActive(new Shoot());
    controller.getResetShootButton().whenActive(new ShootReset());
    
      /*
    controller.getIntakeSpinButton().whenPressed(new IntakeRoll());
    controller.getIntakeReverseButton().whenActive(new IntakeReverse());

    controller.getDrumUpButton().whenPressed(new DrumVelocitySpeed());
    controller.getDrumDownButton().whenActive(new DrumVelocitySlow());

    controller.getTransferStraightButton().whenPressed(new BallTransferStraight());
    controller.getTransferPivotButton().whenActive(new BallTransferPivotAndRoll());

    controller.getIntakeHarvestButton().whenPressed(new IntakeHarvest());
    controller.getIntakeHarvestResetButton().whenActive(new IntakeHarvestReset());

    controller.getDrumAgitateButton().whenPressed(new DrumAgitate());
    controller.getDrumAgitatePreShootButton().whenActive(new DrumAgitatePreShoot());

    controller.getDrumCCWButton().whenPressed(new DrumCCW());

    controller.getPOVUp().whenHeld(new AngledShooterUp());
    controller.getPOVDown().whenHeld(new AngledShooterDown());
    controller.getPOVLeft().whenHeld(new FlywheelSlow());
    controller.getPOVRight().whenHeld(new FlywheelSpeed());

    controller.getShootButton().whenPressed(new Shoot());
    controller.getResetShootButton().whenActive(new ShootReset()); */
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
    /*
    else if(autonChooser.getSelected().equals("bouncePath")) {
      return new FarShootAndDrive();
    }
    */
    else if(autonChooser.getSelected().equals("forward")) {
      createTrajectory("forward");
      //return new FollowGivenPath(trajectory);
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
    Trajectory exampleTrajectory =
        TrajectoryGenerator.generateTrajectory(
            // Start at the origin facing the +X direction
            new Pose2d(0, 0, new Rotation2d(0)),
            // Pass through these two interior waypoints, making an 's' curve path
            List.of(new Translation2d(1, 0), new Translation2d(2, 0)),
            // End 3 meters straight ahead of where we started, facing forward
            new Pose2d(3, 0, new Rotation2d(0)),
            config);

*/

  drivetrain.resetOdometry(trajectory.getInitialPose());
      swerveControllerCommand =
    new SwerveControllerCommand(
        trajectory,
        drivetrain::getCurrentPose, 
        DrivetrainConstants.DRIVE_KINEMATICS,

        // Position controllers
        new PIDController(DrivetrainConstants.P_X_Controller, 0, 0),
        new PIDController(DrivetrainConstants.P_Y_Controller, 0, 0),
        thetaController,
        drivetrain::setModuleStates, //Not sure about setModuleStates
        drivetrain);
if(swerveControllerCommand == null) { System.out.println("long thing is null 2"); }

// Reset odometry to the starting pose of the trajectory.
drivetrain.resetOdometry(trajectory.getInitialPose());
    }
    
    else if(autonChooser.getSelected().equals("forward_and_back")) {
      createTrajectory("forward_and_back");
      return new FollowGivenPath(trajectory);
    }

    else if(autonChooser.getSelected().equals("L_shape")) {
      createTrajectory("L_shape");
      return new FollowGivenPath(trajectory);
    }

    if(swerveControllerCommand == null) { System.out.println("long thing is null"); }
    if(drivetrain == null) { System.out.println("drivetrain is null"); }
    return swerveControllerCommand.andThen(() -> drivetrain.drive(0, 0, 0));
  }
 
  

  public void createTrajectory(String path){
    String trajectoryJSON = "paths/" + path + ".wpilib.json";
    trajectory = null;
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
  }
}