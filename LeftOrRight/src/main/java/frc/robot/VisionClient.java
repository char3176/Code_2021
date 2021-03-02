// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/** Add your docs here. */
public class VisionClient {
    public NetworkTableInstance tableInstance;
    public NetworkTable limelightTable;
    public static NetworkTableEntry tv;
    public static NetworkTableEntry tx;

    public VisionClient(){
        tableInstance = NetworkTableInstance.getDefault();
        limelightTable = tableInstance.getTable("limelight");
        tv = limelightTable.getEntry("tv");
        tx = limelightTable.getEntry("tx");
        limelightTable.getEntry("pipeline").setNumber(3);
    }
}
