package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.controller.HolonomicDriveController;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.ProfiledPIDController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.TrajectoryUtil;
import edu.wpi.first.wpilibj.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import frc.robot.commands.auton.AutonDrive;
import frc.robot.commands.auton.FollowGivenPath;
import frc.robot.commands.auton.HolonomicAuton;
import frc.robot.commands.auton.RunAuton;
import frc.robot.commands.auton.Slalom;
import frc.robot.commands.auton.*;
import frc.robot.commands.teleop.*;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.constants.MasterConstants;
import frc.robot.subsystems.AngledShooter;
import frc.robot.subsystems.BallTransfer;
import frc.robot.subsystems.Drum;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.PowerManagement;

import java.util.ArrayList;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class RobotContainer {
  private Controller m_Controller;
  private Compressor m_Compressor;
  private AngledShooter m_AngledShooter;
  private Drum m_Drum;
  private Drivetrain m_Drivetrain;
  private BallTransfer m_BallTransfer;
  private Flywheel m_Flywheel;
  private PowerManagement m_PowerManagement;

  private SendableChooser<String> m_autonChooser;
  private static final String m_autonstatic1= "s_autonstatic1";
  private static final String m_autonstatic2= "s_autonstatic2";
  private static final String m_autonstatic3= "s_autonstatic3";
  private static final String m_autonmutable1= "s_autonmutable1";
  private static final String m_autonmutable2= "s_autonmutable2";
  private static final String m_autonmutable3= "s_autonmutable3";

  public Trajectory m_trajectory;

  private ArrayList<Trajectory> m_trajLibrary; 

  public ProfiledPIDController thetaController;

  public SwerveControllerCommand m_SwerveControllerCommand;

  public RobotContainer() {
    m_Compressor = new Compressor();
    m_Compressor.start();

    m_Controller = Controller.getInstance();

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

    m_autonChooser = new SendableChooser<>();
    m_autonChooser.setDefaultOption("Auton Static 1", m_autonstatic1);
    m_autonChooser.addOption("Auton Static 2", m_autonstatic2);
    m_autonChooser.addOption("Auton Static 3", m_autonstatic3);
    m_autonChooser.addOption("Auton Mutable 1", m_autonmutable1);
    m_autonChooser.addOption("Auton Mutable 2", m_autonmutable2);
    m_autonChooser.addOption("Auton Mutable 3", m_autonmutable3);
    SmartDashboard.putData("Auton Choice", m_autonChooser);


    // Please keep below array trajFilelist in alphabetical order.  Makes it easier to keep track.
    /*
    String[] trajFilelist = {"barrel_racing", "bounce", "easy", "forward_and_back", "forward",
      "galactic_search_pathA_blueBalls", "galactic_search_pathA_redBalls", 
      "galactic_search_pathB_blueBalls", "galactic_search_pathB_redBalls",
      "L_shape", "slalom"};
    m_trajLibrary = new ArrayList<Trajectory>();
    for (String var : trajFilelist)  {
      m_trajLibrary.add(preloadCreateTrajectory(var));
    }
    */
    //preloadTrajectoryFiles();

    SmartDashboard.putData("ATLAS IntStellarAccuracy", new AtlasForInterstellarAccuracy());
    SmartDashboard.putData("ATLAS PowerPort", new AtlasForPowerPort());
    SmartDashboard.putData("ATLAS Global Off", new AtlasOff());
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
    m_Controller.getTurboButton().whenHeld(new SwerveTurboOn());
    m_Controller.getTurboButton().whenReleased(new SwerveTurboOff());
    m_Controller.getOrbitButton().whenHeld(new SwerveOrbit(
      () -> m_Controller.getOrbitSpeed(),
      () -> m_Controller.getPOVTransStick()));

  
    // m_Controller.getDrumUpButton().whenActive(new DrumVelocitySpeed());
    // m_Controller.getDrumDownButton().whenActive(new DrumVelocitySlow());
  
    m_Controller.getTransferStraightButton().whenActive(new BallTransferStraight());
    m_Controller.getTransferPivotButton().whenActive(new BallTransferPivotAndRoll());
  
    m_Controller.getIntakeHarvestButton().whenActive(new IntakeHarvest());
    m_Controller.getIntakeHarvestResetButton().whenActive(new IntakeHarvestReset());
  
    // m_Controller.getDrumAgitateButton().whenActive(new DrumAgitate());
    m_Controller.getDrumAgitatePreShootButton().whenActive(new DrumAgitatePreShoot());
  
    m_Controller.getDrumInputResetButton().whenActive(new DrumInputReset());
    m_Controller.getDrumCCWButton().whenActive(new DrumCCW());
  
    m_Controller.getPOVUp().whenHeld(new AngledShooterUp());    //ANGLE SHOOTER TEST
    m_Controller.getPOVDown().whenHeld(new AngledShooterDown());//ANGLE SHOOTER TEST
    m_Controller.getPOVLeft().whenHeld(new FlywheelSlow());
    m_Controller.getPOVRight().whenHeld(new FlywheelSpeed());

    m_Controller.getAngledShooterOffButton().whenActive(new AngledShooterOff());
  
    m_Controller.getShootButton().whenActive(new Shoot());
    m_Controller.getResetShootButton().whenActive(new ShootReset());

    /* Angled Shooter Test Controls */

    // m_Controller.getUShoot().whenActive(new AngledShooterUp());
    // m_Controller.getDShoot().whenActive(new AngledShooterDown());
    // m_Controller.getHShoot().whenActive(new AngledShooterOff());
    // m_Controller.getPShoot().whenActive(new AngledShooterPIDTuner());

    // m_Controller.getLStick().whenActive(new AngledShooterDown());
    // m_Controller.getLTrigger().whenActive(new AngledShooterUp());
    m_Controller.getDrumUpButton().whenActive(new AngledShooterUp());
    m_Controller.getDrumDownButton().whenActive(new AngledShooterDown());
  }

  public Command getAutonomousCommand() {

    if (m_autonChooser.getSelected().equals("s_autonstatic1")) {
      //return new RunAutonStatic1();
      return new RunAuton(); 
    }
    else if(m_autonChooser.getSelected().equals("s_autonstatic2")) {
      return new RunAutonStatic2();
    }
    else if(m_autonChooser.getSelected().equals("s_autonstatic3")) {
      return new RunAutonStatic3();
    } 
    else if(m_autonChooser.getSelected().equals("s_autonmutable1")) {
      return new RunAutonMutable1();
    }
    else if(m_autonChooser.getSelected().equals("s_autonmutable2")) {
      return new RunAutonMutable2();
    }
    else if (m_autonChooser.getSelected().equals("s_autonmutable3")) {
      return new RunAutonMutable3();
    } else {
      return new RunAuton();
    }
    
    //return new RunAuton();
  }

  public void createTrajectory(String path){
    String trajectoryJSON = "paths/" + path + ".wpilib.json";
    //trajectory = null;
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      m_trajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
  }

  public Trajectory preloadCreateTrajectory(String path){
    String trajectoryJSON = "paths/" + path + ".wpilib.json";
    Trajectory tempTrajectory = null;
    try {
      Path trajectoryPath = Filesystem.getDeployDirectory().toPath().resolve(trajectoryJSON);
      tempTrajectory = TrajectoryUtil.fromPathweaverJson(trajectoryPath);
    } catch (IOException ex) {
      DriverStation.reportError("Unable to open trajectory: " + trajectoryJSON, ex.getStackTrace());
    }
    return tempTrajectory;
  }
}
