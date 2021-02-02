package frc.robot.subsystems;

import frc.robot.Constants;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SlewRateLimiter;

public class Motor extends SubsystemBase {
    CANSparkMax spinMotor = new CANSparkMax(Constants.SparkMaxCAN, MotorType.kBrushless);
    CANPIDController spinPIDControl;
    CANEncoder spinEncoder;
    SlewRateLimiter rateLimiter;

    private static Motor instance = new Motor();
    
    public Motor() {
        spinMotor.restoreFactoryDefaults();
        spinPIDControl = spinMotor.getPIDController();
        spinEncoder = spinMotor.getEncoder();
        spinMotor.setIdleMode(CANSparkMax.IdleMode.kCoast);

        rateLimiter = new SlewRateLimiter(Constants.kRampRate, 0.0);

        spinPIDControl.setP(Constants.kP);
        spinPIDControl.setI(Constants.kI);
        spinPIDControl.setD(Constants.kD);
        spinPIDControl.setIZone(Constants.kIzone);
        spinPIDControl.setFF(Constants.kF);  
        spinPIDControl.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);  //Set min & max for output of the PID loop

        spinPIDControl.setReference(0.0, ControlType.kVelocity);
    }

    public void highSpin() {
        spinPIDControl.setReference(rateLimiter.calculate(3000), ControlType.kVelocity);
        // spinPIDControl.setReference(5700, ControlType.kVelocity);
        // spinMotor.set(0.8);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("High run");
    }

    public void lowSpin() {
        spinPIDControl.setReference(rateLimiter.calculate(1000), ControlType.kVelocity);
        // spinPIDControl.setReference(2350, ControlType.kVelocity);
        // spinMotor.set(0.2);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("Low run");
    }

    public void stopSpin() {
        spinPIDControl.setReference(rateLimiter.calculate(0), ControlType.kVelocity);
        // spinPIDControl.setReference(0, ControlType.kVelocity);
        // spinMotor.set(0.0);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("Stop run");
    }

    public void emergencyStop() {
        spinPIDControl.setReference(0, ControlType.kVelocity);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("EM-Stop run");
    }

    public static Motor getInstance() {
        return instance;

    }
}