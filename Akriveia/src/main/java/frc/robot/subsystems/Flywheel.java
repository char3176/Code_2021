package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.constants.FlywheelConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
    WPI_TalonFX flywheelController = new WPI_TalonFX(5);

    private static Flywheel instance = new Flywheel();
    
    public Flywheel() {
        flywheelController.configFactoryDefault();

        flywheelController.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kTimeoutMs);
        flywheelController.configAllowableClosedloopError(0, FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kTimeoutMs);

        flywheelController.config_kF(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kF, FlywheelConstants.kTimeoutMs);
        flywheelController.config_kP(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kP, FlywheelConstants.kTimeoutMs);
        flywheelController.config_kI(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kI, FlywheelConstants.kTimeoutMs);
        flywheelController.config_kD(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kD, FlywheelConstants.kTimeoutMs);

        flywheelController.setSensorPhase(true);
    }
    
    public void spinVelocityPIDF(double u) {
        double ticsPer100ms = (u * 2048.0) / 600.0;
        flywheelController.set(TalonFXControlMode.Velocity, ticsPer100ms);
    }

    public void spinVelocityOutputPercent(double u) {
        flywheelController.set(TalonFXControlMode.PercentOutput, u);
    }


    public static Flywheel getInstance() {
        return instance;
    }
}
