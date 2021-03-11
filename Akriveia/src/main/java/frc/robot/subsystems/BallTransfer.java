package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.BallTransferConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class BallTransfer extends SubsystemBase {
  /** Creates a new Transfer. This transfer goes from the Drum to the Shooter. This transfer has a piston to move it and also a Talon SRX to move the Power Cells */
  private static BallTransfer instance = new BallTransfer();
  private DoubleSolenoid transferPiston = new DoubleSolenoid(3, 4); //Add to Constants
  private CANSparkMax transferMotor = new CANSparkMax(BallTransferConstants.MOTOR_CAN_ID, MotorType.kBrushless);

  public BallTransfer() {
    //Sets the piston to a nuetral state
    // transferPiston.set(Value.kOff);
  }

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
