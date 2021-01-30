package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class Lifter extends SubsystemBase {
    private static Lifter instance = new Lifter();
    private DoubleSolenoid piston = new DoubleSolenoid(5, 2);

    public Lifter() {
        piston.set(DoubleSolenoid.Value.kOff);
    }

    public static Lifter getInstance() {
        return instance;
    }
    
    public void pistonExtend() {
        System.out.println("Extend: " + piston.get());
        piston.set(Value.kForward);
        System.out.println("Piston Extend Method Did Something");
    }

    public void pistonRetract() {
        System.out.println("Retract: " + piston.get());
        piston.set(Value.kReverse);
        System.out.println("Piston Retract Method Did Something");
    }    
}