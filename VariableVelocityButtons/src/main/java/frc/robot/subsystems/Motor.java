package frc.robot.subsystems;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
    // private double spinSpeedVal = 0.0;

    private double LkP;
    private double LkI;
    private double LkD;
    private double LkF;
    private double LkIZone;
    
    public Motor() {
        spinMotor.restoreFactoryDefaults();
        spinPIDControl = spinMotor.getPIDController();
        spinEncoder = spinMotor.getEncoder();

        // spinPIDControl.setP(Constants.kP);
        // spinPIDControl.setI(Constants.kI);
        // spinPIDControl.setD(Constants.kD);
        // spinPIDControl.setIZone(Constants.kIzone);
        // spinPIDControl.setFF(Constants.kF);  
        spinPIDControl.setOutputRange(Constants.kMinOutput, Constants.kMaxOutput);  //Set min & max for output of the PID loop

        // Local constants for testing with ShuffleBoard
        LkP = 6e-5;
        LkI = 0.0;
        LkD = 0.0;
        LkF = 0.0015;
        LkIZone = 0.0;

        spinPIDControl.setP(LkP);
        spinPIDControl.setI(LkI);
        spinPIDControl.setD(LkD);
        spinPIDControl.setFF(LkF);
        spinPIDControl.setIZone(LkIZone);

        spinPIDControl.setReference(0.0, ControlType.kVelocity, 0);

    }
    
    // Shuffleboard testing
    public void boardDisplay() {
        SmartDashboard.putNumber("kP", LkP);
        SmartDashboard.putNumber("kI", LkI);
        SmartDashboard.putNumber("kD", LkD);
        SmartDashboard.putNumber("kF", LkF);
    }

    public void boardRead() {
        double p = SmartDashboard.getNumber("kP", LkP);
        double i = SmartDashboard.getNumber("kI", LkI);
        double d = SmartDashboard.getNumber("kD", LkD);
        double f = SmartDashboard.getNumber("kF", LkF);

        if (p != LkP) {
            LkP = p;
            spinPIDControl.setP(LkP);
        }
        if (i != LkI) {
            LkI = i;
            spinPIDControl.setI(LkI);
        }
        if (d != LkD) {
            LkD = d;
            spinPIDControl.setD(LkD);
        }
        if (f != LkF) {
            LkF = f;
            spinPIDControl.setFF(LkF);
        }
    }

    public void highSpin() {
        spinPIDControl.setReference(5700, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(5700, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(1.0, ControlType.kVelocity);
        // spinSpeedVal = 1.0;
        // spinMotor.set(1.0);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("High run");
        boardRead();
    }

    public void lowSpin() {
        spinPIDControl.setReference(2350, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(2350, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(0.5, ControlType.kVelocity);
        // spinSpeedVal = 0.2;
        // spinMotor.set(0.2);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("Low run");
        boardRead();
    }

    public void stopSpin() {
        spinPIDControl.setReference(0, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(0, ControlType.kVelocity, 0);
        // spinPIDControl.setReference(0.0, ControlType.kVelocity);
        // spinSpeedVal = 0.0;
        // spinMotor.set(0.0);
        System.out.println(spinEncoder.getVelocity());
        System.out.println("Stop run");
        boardRead();
    }

    public static Motor getInstance() {
        return instance;

    }
}
