package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;



public class Lifter extends SubsystemBase {
    private static Lifter instance = new Lifter();
    private DoubleSolenoid piston = new DoubleSolenoid(4, 3);

    public Lifter() {
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