package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.controller.PIDController;



public class Lifter extends SubsystemBase {
    //public final double kP = 0;
    //public final double kI = 0;
    //public final double kD = 0;

    DoubleSolenoid piston = new DoubleSolenoid(4, 3);
    //PIDController turnController = new PIDController(kP, kI, kD);
    private static Lifter instance = new Lifter();

    public void writePeriodicInputs() {
        piston.set(Value.kOff);
    }
    
    public void pistonExtend() {
        piston.set(Value.kForward);
    }

    public void pistonRetract() {
        piston.set(Value.kReverse);
    }

    public static Lifter getInstance() {
        return instance;
    }
}