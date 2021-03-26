package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.PowerManagementConstants;
import frc.robot.util.ElectricalData;

public class PowerManagement extends SubsystemBase {
    private PowerDistributionPanel PDP; 
    private static PowerManagement instance = new PowerManagement();

    /* We assume an update rate of 1 update per 0.02 sec; therefore, we collect 5 values in the Current History,
     * which represents a window of 0.1 sec wide (10% of a second).
     * Consider expanding to 15 values which would represent a 0.25sec wide window. */
   
    private ElectricalData HoodPM; 
    private ElectricalData DrumPM; 
   
    public PowerManagement() {
        PDP = new PowerDistributionPanel(PowerManagementConstants.PDP_CAN_ID);
        HoodPM = new ElectricalData(PowerManagementConstants.ANGLED_SHOOTER_PDP_CHANNEL, "Angled Shooter", 5);
        DrumPM = new ElectricalData(PowerManagementConstants.DRUM_PDP_CHANNEL, "Drum", 5);
    }

    public double getAngledShooterInstantAmp() {
        return HoodPM.getInstantaneousAmp();
    }

    public double getAngledShooterAvgAmp() {
        return HoodPM.mean();
    }

    public double getDrumInstantAmp() {
        return DrumPM.getInstantaneousAmp();
    }

    public double getDrumAvgAmp() {
        return DrumPM.mean();
    }

    @Override
    public void periodic() {
        HoodPM.addAmpData();
        DrumPM.addAmpData();
    }

    public static PowerManagement getInstance() {
        return instance; 
    }

}
