package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;

public class Robot extends TimedRobot {
  private Joystick Joy = new Joystick(0);
  private TalonSRX t = new TalonSRX(33);

  @Override
  public void robotInit() {}

  @Override
  public void teleopPeriodic() {
    if(Joy.getRawButton(3)) {t.set(ControlMode.PercentOutput, 1);}
    if(Joy.getRawButton(4)) {t.set(ControlMode.PercentOutput, 0);} 
}
}