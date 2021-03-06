package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.AngledShooterConstants;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

public class AngledShooter extends SubsystemBase {
  
  private TalonSRX angledShooterTalon = new WPI_TalonSRX(AngledShooterConstants.angledShooterCANID);
  private static AngledShooter instance = new AngledShooter();
  private int absolutePosition = 0;  

  public AngledShooter() {
	  
	  /* Setting up the Motor */
	  
    angledShooterTalon.configFactoryDefault();
    angledShooterTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, AngledShooterConstants.angledShooterPIDLoopIdx, AngledShooterConstants.angledShooterTimeoutMs);
    angledShooterTalon.setSensorPhase(AngledShooterConstants.angledShooterSensorPhase);
    angledShooterTalon.setInverted(AngledShooterConstants.angledShooterMotorInvert);
    angledShooterTalon.configNominalOutputForward(0, AngledShooterConstants.angledShooterTimeoutMs);
    angledShooterTalon.configNominalOutputReverse(0, AngledShooterConstants.angledShooterTimeoutMs);
		angledShooterTalon.configPeakOutputForward(1, AngledShooterConstants.angledShooterTimeoutMs);
		angledShooterTalon.configPeakOutputReverse(-1, AngledShooterConstants.angledShooterTimeoutMs);
    angledShooterTalon.configAllowableClosedloopError(0, AngledShooterConstants.angledShooterPIDLoopIdx, AngledShooterConstants.angledShooterTimeoutMs);
    angledShooterTalon.config_kF(AngledShooterConstants.angledShooterPIDLoopIdx, AngledShooterConstants.pArray[3], AngledShooterConstants.angledShooterTimeoutMs);
		angledShooterTalon.config_kP(AngledShooterConstants.angledShooterPIDLoopIdx, AngledShooterConstants.pArray[0], AngledShooterConstants.angledShooterTimeoutMs);
		angledShooterTalon.config_kI(AngledShooterConstants.angledShooterPIDLoopIdx, AngledShooterConstants.pArray[1], AngledShooterConstants.angledShooterTimeoutMs);
    angledShooterTalon.config_kD(AngledShooterConstants.angledShooterPIDLoopIdx, AngledShooterConstants.pArray[2], AngledShooterConstants.angledShooterTimeoutMs);
    absolutePosition = angledShooterTalon.getSensorCollection().getPulseWidthPosition();
    absolutePosition &= 0xFFF;
		if(AngledShooterConstants.angledShooterSensorPhase) {absolutePosition *= -1;}
    if(AngledShooterConstants.angledShooterMotorInvert) {absolutePosition *= -1;}
    angledShooterTalon.setSelectedSensorPosition(absolutePosition, AngledShooterConstants.angledShooterPIDLoopIdx, AngledShooterConstants.angledShooterTimeoutMs);
    angledShooterTalon.set(ControlMode.Position, AngledShooterConstants.MIN_TICS);
  }

  @Override
  public void periodic() {}

  public static AngledShooter getInstance() {return instance;}

  /**
   * Sets the positon of the Angled Shooter Hood
   * @param targetPosition - Gets the target position that it should go if its inside of the MIN and MAX constants
   * @see commands.teleop.AngledShooterUp
   * @see commands.teleop.AngledShooterDown
   */

  public void setPosition(double targetPosition) {
    if (targetPosition >= AngledShooterConstants.MIN_TICS && targetPosition <= AngledShooterConstants.MAX_TICS){
      angledShooterTalon.set(ControlMode.Position, targetPosition);
    }
  }

	/**
	 * @return the encoder position by using .getSelectedSensorPosition()
	 */
	
  public double getEncoderPosition() {
    return angledShooterTalon.getSelectedSensorPosition();
  }
}