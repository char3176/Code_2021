// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SystemPrinter extends SubsystemBase {
  public static SystemPrinter Instance = new SystemPrinter();
  /** Creates a new SystemPrinter. This subsystem interacts with no physical hardware, just system outputs.*/
  public SystemPrinter() {}

  public static SystemPrinter getInstance(){
    return Instance;
  }

  public void printPositivetv() {
    SmartDashboard.putBoolean("Has Target", true);
  }
  public void printNegativetv() {
    SmartDashboard.putBoolean("Has Target", false);
  }
  public void printtoleft(double absOffset) {
    SmartDashboard.putNumber("Degrees", absOffset * -1);
  }
  public void printtright(double absOffset) {
    SmartDashboard.putNumber("Degrees", absOffset);
  }
  public void printinfront() {
    SmartDashboard.putNumber("Degrees", 0);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
