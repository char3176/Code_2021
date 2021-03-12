package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.BallTransferConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class BallTransfer extends SubsystemBase {
  /** Creates a new Transfer. This transfer goes from the Drum to the Shooter. 
   * This transfer has a piston to move it and also a Spark Max to move the Power Cells */
  
  private static BallTransfer instance = new BallTransfer();
  private DoubleSolenoid transferPiston = new DoubleSolenoid(BallTransferConstants.DS_OPEN_ID, BallTransferConstants.DS_CLOSE_ID);
  private CANSparkMax transferMotor = new CANSparkMax(BallTransferConstants.MOTOR_CAN_ID, MotorType.kBrushless);

  public BallTransfer() {}

  public static BallTransfer getInstance() {
    return instance;
  }

  /**
   * @param percent - the percent -1 to 1 inclusive of the transferMotor
   */

  public void setPercentControl(double percent) {
    transferMotor.set(percent);
  }

  /**
   * Extends the transferPiston
   */

  public void Extend() {
    transferPiston.set(Value.kForward);
  }

  /**
   * Retracts the transferPiston
   */
  
  public void Retract() {
    transferPiston.set(Value.kReverse);
  }
}