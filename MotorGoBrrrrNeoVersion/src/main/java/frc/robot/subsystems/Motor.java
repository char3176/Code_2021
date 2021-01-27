package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Motor extends SubsystemBase {
    CANSparkMax spinMotor = new CANSparkMax(Constants.SparkMaxCAN, MotorType.kBrushless);
    CANPIDController spinPIDControl;
    CANEncoder spinEncoder;

    private static Motor instance = new Motor();
    
    public Motor() {
        spinMotor.restoreFactoryDefaults();
        spinPIDControl = spinMotor.getPIDController();
        spinEncoder = spinMotor.getEncoder();

        spinPIDControl.setP(Constants.kP);
        spinPIDControl.setI(Constants.kI);
        spinPIDControl.setD(Constants.kD);
        spinPIDControl.setIZone(Constants.kIzone);
        spinPIDControl.setFF(Constants.kF);
        spinPIDControl.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);

    }
    
    public void youSpinMotorRightRound() {
        double motorPosition = spinEncoder.getPosition();
        System.out.println(motorPosition);
        
        // CANSparkMax runs on 4096 ticks, NOT 2048 per revolution
        // But this code below runs on revolutions
        spinPIDControl.setReference(motorPosition + .25, ControlType.kPosition);
        System.out.println("method run");
    }

    public static Motor getInstance() {
        return instance;

    }
}
