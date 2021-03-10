// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

//import javax.swing.text.Position;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.constants.FlywheelConstants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
    WPI_TalonFX spinMotor = new WPI_TalonFX(5);

    private static Flywheel instance = new Flywheel();
    private static double lastSetting = 0;
    
    public Flywheel() {
        spinMotor.configFactoryDefault();

        spinMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kTimeoutMs);
        spinMotor.configAllowableClosedloopError(0, FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kTimeoutMs);

        spinMotor.config_kF(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kF, FlywheelConstants.kTimeoutMs);
        spinMotor.config_kP(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kP, FlywheelConstants.kTimeoutMs);
        spinMotor.config_kI(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kI, FlywheelConstants.kTimeoutMs);
        spinMotor.config_kD(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kD, FlywheelConstants.kTimeoutMs);

        spinMotor.setSensorPhase(true);
    }
    
    public void youSpinMotorRightRound(double level) {
        lastSetting = level;
        double ticsPer100ms = (FlywheelConstants.FlywheelSpeeds[(int) level] * 2048.0) / 600.0;
        spinMotor.set(TalonFXControlMode.Velocity, ticsPer100ms);
    }

    public double getLastSetting() {
        return lastSetting;
    }

    public static Flywheel getInstance() {
        return instance;
    }
}