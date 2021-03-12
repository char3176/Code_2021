// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/** Just creates and sets the networktable variables needed for the program to function.
 * 
 */
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
        limelightTable.getEntry("pipeline").setNumber(0);
    }
    public void turnLEDson(){
        limelightTable.getEntry("ledMode").setNumber(3);
    }
    public void blinkLEDs(){
        limelightTable.getEntry("ledMode").setNumber(2);
    }
    public void turnLEDsoff(){
        limelightTable.getEntry("ledMode").setNumber(1);
    }
    /**
     * Sets camera's mode
     * @param mode determines which mode the method is set to, 0 is vision processing, 1 is Driver Cam, and anything else will print an error message.
     */
    public void setCameraMode(boolean mode){
        if(mode){
            limelightTable.getEntry("camMode").setNumber(0);
        }
        else{
            limelightTable.getEntry("camMode").setNumber(1);
        }
    }
    
    /**
     * Sets which pipeline the processor will use.
     * @param pipe sets the pipeline that will be used.
     */
    public void setPipeline(int pipe){
        if(pipe == 0){
            limelightTable.getEntry("pipeline").setNumber(0);
        }
        else if(pipe == 1){
            limelightTable.getEntry("pipeline").setNumber(1);
        }
    }
}
