// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class SystemPrinter extends SubsystemBase {
  public static SystemPrinter Instance = new SystemPrinter();
  /** Creates a new SystemPrinter. */
  public SystemPrinter() {}

  public static SystemPrinter getInstance(){
    return Instance;
  }

  public void printPositivetv() {
    System.out.println("I see something.");
  }
  public void printNegativetv() {
    System.out.println("I see nothing.");
  }
  public void printtoleft(double absOffset) {
    System.out.println("It's " + absOffset + " degrees to my left.");
  }
  public void printtright(double absOffset) {
    System.out.println("It's " + absOffset + " degrees to my right.");
  }
  public void printinfront() {
    System.out.println("It's directly ahead of me.");
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
