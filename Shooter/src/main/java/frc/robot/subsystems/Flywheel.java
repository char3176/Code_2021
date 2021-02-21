
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
    WPI_TalonFX spinMotor = new WPI_TalonFX(Constants.FlywheelCANID);

    private static Flywheel instance = new Flywheel();
    
    public Flywheel() {
        spinMotor.configFactoryDefault();

        spinMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.Flywheel_kPIDLoopIdx, Constants.Flywheel_kTimeoutMs);
        spinMotor.configAllowableClosedloopError(0, Constants.Flywheel_kPIDLoopIdx, Constants.Flywheel_kTimeoutMs);

        spinMotor.config_kF(Constants.Flywheel_kPIDLoopIdx, Constants.Flywheel_kF, Constants.Flywheel_kTimeoutMs);
        spinMotor.config_kP(Constants.Flywheel_kPIDLoopIdx, Constants.Flywheel_kP, Constants.Flywheel_kTimeoutMs);
        spinMotor.config_kI(Constants.Flywheel_kPIDLoopIdx, Constants.Flywheel_kI, Constants.Flywheel_kTimeoutMs);
        spinMotor.config_kD(Constants.Flywheel_kPIDLoopIdx, Constants.Flywheel_kD, Constants.Flywheel_kTimeoutMs);

        spinMotor.setSensorPhase(true);
    }
    
    public void spinFlywheel(double a) {
        double ticsPer100ms = (a * 2048.0) / 600.0;
        spinMotor.set(TalonFXControlMode.Velocity, ticsPer100ms);
    }

    public static Flywheel getInstance() {
        return instance;

    }
}