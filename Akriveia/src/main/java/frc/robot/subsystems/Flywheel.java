package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import frc.robot.constants.FlywheelConstants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Flywheel extends SubsystemBase {
    WPI_TalonFX flywheelController = new WPI_TalonFX(FlywheelConstants.MOTOR_CAN_ID);
    private static Flywheel instance = new Flywheel();
    private static int lastSetting = 0;
    private double manualRPMInput;
    
    public Flywheel() {
        manualRPMInput = 0; 

        flywheelController.configFactoryDefault();
        flywheelController.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kTimeoutMs);
        flywheelController.configAllowableClosedloopError(0, FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kTimeoutMs);
        flywheelController.setSensorPhase(true);

        flywheelController.config_kF(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kF, FlywheelConstants.kTimeoutMs);
        flywheelController.config_kP(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kP, FlywheelConstants.kTimeoutMs);
        flywheelController.config_kI(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kI, FlywheelConstants.kTimeoutMs);
        flywheelController.config_kD(FlywheelConstants.kPIDLoopIdx, FlywheelConstants.kD, FlywheelConstants.kTimeoutMs);

    }
    
    /**
     * @param level of speed to multipy by 2048 and then divide by 600 and set it to that velocity
     */
    public void spinVelocityPIDF(int level) {
        lastSetting = level;
        double wantedRPM = FlywheelConstants.FlywheelSpeeds[level];
        spinVelocityPIDFPart2(wantedRPM);
    }

    private void spinVelocityPIDFPart2(double rpmSetPoint) {
        double ticsPer100ms = (rpmSetPoint * 2048.0) / 600.0;
        flywheelController.set(TalonFXControlMode.Velocity, ticsPer100ms);
        SmartDashboard.putNumber("Flywheel RPM Requested", (ticsPer100ms * 600 / 2048));
    }

    /**
     * @param pct The input of a percent to set the motor to that percent
     */
    public void spinVelocityOutputPercent(double pct) {
        flywheelController.set(TalonFXControlMode.PercentOutput, pct);
    }

    /**
     * @return the last speed setting of the Flywheel
     */
    public int getLastSetting() {
        return lastSetting;
    }

    /**
     * @return A single, universal instance to be used anywhere else in the code
     */
    public static Flywheel getInstance() {
        return instance;
    }

    /**
     * Measure Flywheel encoder and calculate RPM
     * @return Double datatype, The Flywheel velocity in RPM as measured by encoder
     */
    private double getVelocity() {
        double speed = flywheelController.getSelectedSensorVelocity(1);
        SmartDashboard.putNumber("FlywheelSensorVelocity_ticsPer100ms", speed);
        double rpm = (speed * 600 / 2048);
    
            // is linear velocity at surface of wheel.  Needs correct diameter instead of 3.25) --> speed = speed * 1 * Units.inchesToMeters(3.25*PI)/SwervePodConstants.DRIVE_ENCODER_UNITS_PER_REVOLUTION;
        SmartDashboard.putNumber("FlywheelSensorVelocity_RPM", rpm);
        return rpm;     
    }

    @Override
    public void periodic() {
        manualRPMInput = SmartDashboard.getNumber("Flywheel RPM Wanted", 0);
        if ( (manualRPMInput != 0) && (lastSetting == 0) ) { 
            spinVelocityPIDFPart2(manualRPMInput);
        } else {
            SmartDashboard.putNumber("Flywheel RPM Wanted", 0);
        }
    }
}