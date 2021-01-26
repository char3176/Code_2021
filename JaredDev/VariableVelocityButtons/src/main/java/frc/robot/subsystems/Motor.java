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
        //spinPIDControl = spinMotor.getPIDController();
        spinEncoder = spinMotor.getEncoder();

        //spinPIDControl.setP(Constants.kP);
        //spinPIDControl.setI(Constants.kI);
        //spinPIDControl.setD(Constants.kD);
        //spinPIDControl.setIZone(Constants.kIzone);
        //spinPIDControl.setFF(Constants.kF);  
        //spinPIDControl.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);  //Set min & max for output of the PID loop

        //spinPIDControl.setReference(0.0, ControlType.kVelocity, 0);

    }
    
    public void highSpin() {
        // spinPIDControl.setReference(5700, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(5700, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(1.0, ControlType.kVelocity);
        spinMotor.set(1.0);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("High run");
    }

    public void lowSpin() {
        // spinPIDControl.setReference(2350, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(2350, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(0.5, ControlType.kVelocity);
        spinMotor.set(0.2);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("Low run");
    }

    public void stopSpin() {
        // spinPIDControl.setReference(0, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(0, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(0.0, ControlType.kVelocity);
        spinMotor.set(0);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("Stop run");
    }

    public static Motor getInstance() {
        return instance;

    }
}
