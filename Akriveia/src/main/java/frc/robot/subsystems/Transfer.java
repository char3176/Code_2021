package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.TransferConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Responsible for transferring the Power Cells from the Drum to the AngledShooter. Uses a piston and motor to do so.
 */
public class Transfer extends SubsystemBase {
  private static Transfer instance = new Transfer();
  private DoubleSolenoid transferPiston = new DoubleSolenoid(TransferConstants.DS_OPEN_ID, TransferConstants.DS_CLOSE_ID);
  private CANSparkMax transferMotor = new CANSparkMax(TransferConstants.MOTOR_CAN_ID, MotorType.kBrushless);
  private boolean pistonSetting = false;
  private double levelSetting = 0;

  private DigitalInput lineBreak = new DigitalInput(1);

  public Transfer() {}

  public static Transfer getInstance() {
    return instance;
  }

  public void stopMotors() {
    transferMotor.set(0);
  }
  /**
   * @param percent - the percent -1 to 1 inclusive of the transferMotor
   */
  public void setPercentControl(double percent) {
    levelSetting = percent;
    transferMotor.set(percent);
  }

  /**
   * Extends the transferPiston if the line break isn't broken
   */
  public void Extend() {
    if(lineBreak.get()) {
      pistonSetting = true;
      transferPiston.set(Value.kForward);
    }
  }

  /**
   * Retracts the transferPiston
   */
  public void Retract() {
    pistonSetting = false;
    transferPiston.set(Value.kReverse);
  }

  /**
   * @return Gets the piston setting incase its extended or retracted
   */
  public boolean getPistonSetting() {
    return pistonSetting;
  }

  /**
   * @return Gets the percentage of the motor from the last time it was set
   */
  public double getLevelSetting() {
    return levelSetting;
  }
}