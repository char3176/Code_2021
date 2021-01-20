package frc.robot.subsystems;

import javax.swing.text.Position;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
    WPI_TalonFX spinMotor = new WPI_TalonFX(1);
    private static Motor instance = new Motor();
    
    
    public void youSpinMotorRightRound() {
        spinMotor.configFactoryDefault();
        //spinMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor);
        double motorPosition = spinMotor.getSelectedSensorPosition();
        System.out.println(motorPosition);
        spinMotor.set(TalonFXControlMode.Position, motorPosition + 512);
        System.out.println("motor method run");
    }

    public static Motor getInstance() {
        return instance;

    }
}
