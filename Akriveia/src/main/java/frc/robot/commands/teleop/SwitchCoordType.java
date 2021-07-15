package frc.robot.commands.teleop;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.constants.DrivetrainConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Drivetrain.coordType;

public class SwitchCoordType extends InstantCommand {
  private Drivetrain m_Drivetrain = Drivetrain.getInstance();


  public SwitchCoordType() {
    addRequirements(m_Drivetrain);
  }

  @Override
  public void initialize() {
    if ( m_Drivetrain.getCurrentCoordType() == coordType.FIELD_CENTRIC ) {
      m_Drivetrain.setCoordType(coordType.ROBOT_CENTRIC);
    } else if ( m_Drivetrain.getCurrentCoordType() == coordType.ROBOT_CENTRIC ) {
      m_Drivetrain.setCoordType(coordType.FIELD_CENTRIC);
    }
  }
}
