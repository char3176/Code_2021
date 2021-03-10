package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.BallTransferConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class BallTransfer extends SubsystemBase {

  private static BallTransfer instance = new BallTransfer();
  private DoubleSolenoid transferPiston = new DoubleSolenoid(1, 6);
  private WPI_TalonSRX transferMotor = new WPI_TalonSRX(BallTransferConstants.MOTOR_CAN_ID);
  private boolean pistonSetting = false;
  private double levelSetting = 0;

  /** Creates a new Transfer. This transfer goes from the Drum to the Shooter. This transfer has a piston to move it and also a
   * Talon SRX to move the Power Cells. */
  public BallTransfer() {
    //Sets the piston to a nuetral state
    transferPiston.set(Value.kOff);
  }

  public static BallTransfer getInstance() {
    return instance;
  }

  /**
   * @param percent - the percent -1 to 1 inclusive of the transferMotor
   */

  public void setPercentControl(double percent) {
    levelSetting = percent;
    transferMotor.set(ControlMode.PercentOutput, percent);
  }

  /**
   * Extends the transferPiston
   */

  public void Extend() {
    pistonSetting = true;
    transferPiston.set(Value.kForward);
  }

  /**
   * Retracts the transferPiston
   */
  
  public void Retract() {
    pistonSetting = false;
    transferPiston.set(Value.kReverse);
  }

  public boolean getPistonSetting() {
    return pistonSetting;
  }

  public double getLevelSetting() {
    return levelSetting;
  }
}