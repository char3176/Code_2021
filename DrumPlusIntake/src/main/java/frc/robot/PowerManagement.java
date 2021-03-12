package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

public class PowerManagement {
    private static PowerDistributionPanel PDP = new PowerDistributionPanel(0);

    public static double getDrumCurrent() {
        return PDP.getCurrent(1);
    }
}
