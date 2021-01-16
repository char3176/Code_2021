package frc.robot.subsystems;

import javax.swing.text.Position;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
    WPI_TalonFX spinMotor = new WPI_TalonFX(1);
    private static Motor instance = new Motor();
    
    public void youSpinMotorRightRound() {
        double motorPosition = spinMotor.getSelectedSensorPosition();
        spinMotor.set(ControlMode.Position, motorPosition + 512);

    }

    public static Motor getInstance() {
        return instance;

    }
}
