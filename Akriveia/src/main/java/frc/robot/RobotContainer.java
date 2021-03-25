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

    configureButtonBindings();
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
    m_Controller.getResetShootButton().whenActive(new ShootReset());
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