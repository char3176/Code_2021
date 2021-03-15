package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.constants.FlywheelConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
    WPI_TalonFX flywheelController = new WPI_TalonFX(FlywheelConstants.MOTOR_CAN_ID);
    private static Flywheel instance = new Flywheel();
    private static double lastSetting = 0;
    
    public Flywheel() {
        flywheelController.configFactoryDefault();
        flywheelController.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kTimeoutMs);
        flywheelController.configAllowableClosedloopError(0, FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kTimeoutMs);
        flywheelController.setSensorPhase(true);

        flywheelController.config_kF(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kF, FlywheelConstants.kTimeoutMs);
        flywheelController.config_kP(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kP, FlywheelConstants.kTimeoutMs);
        flywheelController.config_kI(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kI, FlywheelConstants.kTimeoutMs);
        flywheelController.config_kD(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kD, FlywheelConstants.kTimeoutMs);

    }
    
    /**
     * @param level of speed to multipy by 2048 and then divide by 600 and set it to that velocity
     */

    public void spinVelocityPIDF(double level) {
        lastSetting = level;
        double ticsPer100ms = (FlywheelConstants.FlywheelSpeeds[(int) level] * 2048.0) / 600.0;
        flywheelController.set(TalonFXControlMode.Velocity, ticsPer100ms);
    }

    /**
     * @param pct The input of a percent to set the motor to that percent
     */

    public void spinVelocityOutputPercent(double pct) {
        flywheelController.set(TalonFXControlMode.PercentOutput, pct);
    }

    /**
     * @return the last speed setting of the Flywheel
     */

    public double getLastSetting() {
        return lastSetting;
    }

    /**
     * @return A single, universal Intake instance to be used anywhere else in the code
     */

    public static Flywheel getInstance() {
        return instance;
    }
}