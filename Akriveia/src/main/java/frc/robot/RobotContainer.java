package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.teleop.CancelIntakeRoll;
import frc.robot.commands.teleop.DeployIntake;
import frc.robot.commands.teleop.IntakeDeployAndRoll;
import frc.robot.commands.teleop.IntakeRoll;
import frc.robot.commands.teleop.RetractIntake;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.teleop.AngledShooterDown;
import frc.robot.commands.teleop.AngledShooterUp;
import frc.robot.subsystems.AngledShooter;
import frc.robot.subsystems.Drum;
import frc.robot.commands.teleop.DrumVelocity;
import frc.robot.commands.teleop.AgitateDrum;
import frc.robot.commands.teleop.FlywheelStop;
import frc.robot.commands.teleop.FlywheelMin;
import frc.robot.commands.teleop.FlywheelMed;
import frc.robot.commands.teleop.FlywheelMax;
import frc.robot.commands.teleop.SwerveDefense;
import frc.robot.commands.teleop.SwerveDrive;
import frc.robot.commands.teleop.SwerveReZeroGyro;
import frc.robot.commands.teleop.SwerveVision;
import frc.robot.commands.auton.Slalom;
import frc.robot.subsystems.Drivetrain;

public class RobotContainer {
  public Intake m_Intake;
  private Controller m_Controller;
  private Compressor m_Compressor;
  private AngledShooter m_AngledShooter;
  private Drum m_Drum;
  private Drivetrain m_Drivetrain;

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
    m_AngledShooter = AngledShooter.getInstance();
    m_Drum = Drum.getInstance();
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
    m_Controller.getReZeroGyroButton().whenHeld(new SwerveReZeroGyro());
    m_Controller.getSlalomButton().whenPressed(new Slalom());

    m_Controller.getExtendButton().whenPressed(new DeployIntake());
    m_Controller.getRetractButton().whenPressed(new RetractIntake());
    m_Controller.getRollAndExtendButton().whenPressed(new IntakeDeployAndRoll());
    m_Controller.getRollButton().whenPressed(new IntakeRoll());
    m_Controller.getStopButton().whenPressed(new CancelIntakeRoll());

    m_Controller.getUpDPAD().whenPressed(new AngledShooterUp());
    m_Controller.getDownDPAD().whenPressed(new AngledShooterDown());

    // m_Drum.setDefaultCommand(new DrumVelocity(0));

    // m_Controller.getAButton3().whenPressed(new DrumVelocity(1));
    // m_Controller.getBButton3().whenPressed(new DrumVelocity(2));
    // m_Controller.getYButton3().whenPressed(new DrumVelocity(3));
    // m_Controller.getXButton3().whenPressed(new DrumVelocity(4));
    // m_Controller.getRBumper3().whenPressed(new DrumVelocity(0));

    m_Controller.getAgitateButton().whenPressed(new AgitateDrum());

    // m_Controller.getAButton4().whenPressed(new FlywheelStop()); 
    // m_Controller.getBButton4().whenPressed(new FlywheelMin());
    // m_Controller.getYButton4().whenPressed(new FlywheelMed());
    // m_Controller.getXButton4().whenPressed(new FlywheelMax());
     
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