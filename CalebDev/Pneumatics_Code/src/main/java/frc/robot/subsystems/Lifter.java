package frc.robot.subsystems;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.controller.PIDController;



public class Lifter extends SubsystemBase {
    //public final double kP = 0;
    //public final double kI = 0;
    //public final double kD = 0;

	static Solenoid piston = new Solenoid(0);
    static Compressor compressor = new Compressor(0);
    //PIDController turnController = new PIDController(kP, kI, kD);
    private static Lifter instance = new Lifter();

    private static boolean lift = false;

    public boolean enabled = compressor.enabled();

    public static void readPeriodicInputs() {
        compressor.setClosedLoopControl(true);

    }

    public static void writePeriodicInputs() {
        piston.set(lift);
    }
    
    public void pistonExtend() {
        lift = true;
        piston.set(lift);
    }

    public void pistonRetract() {
        lift = false;
        piston.set(lift);
    }

    public static Lifter getInstance() {
        return instance;
    }
}