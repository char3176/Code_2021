package frc.robot.subsystems;

//import javax.swing.text.Position;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
    WPI_TalonFX spinMotor = new WPI_TalonFX(5);

    private static Motor instance = new Motor();
    
    public Motor() {
        spinMotor.configFactoryDefault();

        spinMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, Constants.kPIDLoopIdx, Constants.kTimeoutMs);
        spinMotor.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

        spinMotor.config_kF(Constants.kPIDLoopIdx, Constants.kF, Constants.kTimeoutMs);
        spinMotor.config_kP(Constants.kPIDLoopIdx, Constants.kP, Constants.kTimeoutMs);
        spinMotor.config_kI(Constants.kPIDLoopIdx, Constants.kI, Constants.kTimeoutMs);
        spinMotor.config_kD(Constants.kPIDLoopIdx, Constants.kD, Constants.kTimeoutMs);

        spinMotor.setSensorPhase(true);
    }
    
    public void youSpinMotorRightRound(double level) {
        double ticsPer100ms = (level * 2048.0) / 600.0;
        spinMotor.set(TalonFXControlMode.Velocity, ticsPer100ms);
    }

    public static Motor getInstance() {
        return instance;

    }
}
