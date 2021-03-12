package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.DoubleSolenoid;
// import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.constants.IntakeConstants;

public class Intake extends SubsystemBase {
  private static Intake instance = new Intake();
  // private DoubleSolenoid leftPiston = new DoubleSolenoid(2, 5);
  // private DoubleSolenoid rightPiston = new DoubleSolenoid(1, 7);
  private WPI_TalonSRX motor = new WPI_TalonSRX(IntakeConstants.MOTOR_CAN_ID);

  public Intake() {
    // leftPiston.set(DoubleSolenoid.Value.kOff);
    // rightPiston.set(DoubleSolenoid.Value.kOff);
  }

  public static Intake getInstance() {
    return instance;
  }

  public void setPercentControl(double percent) {
    motor.set(ControlMode.PercentOutput, percent);
  }

  // public void Extend() {
  //   leftPiston.set(Value.kForward);
  //   rightPiston.set(Value.kForward);
  // }

  // public void Retract() {
  //   leftPiston.set(Value.kReverse);
  //   rightPiston.set(Value.kReverse);
  // }
}
