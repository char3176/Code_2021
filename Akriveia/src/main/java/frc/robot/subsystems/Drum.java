
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.constants.DrumConstants;

public class Drum extends SubsystemBase {
  /** Creates a new Drum. */

  private CANSparkMax drumMotor = new CANSparkMax(DrumConstants.drumMotorCANID, MotorType.kBrushless);
  private CANPIDController drumPIDController;
  private CANEncoder drumEncoder;
  private SlewRateLimiter rateLimiter;
  private static Drum instance = new Drum();
  public boolean drumPctOutputMode = false;
  private boolean isRateLimitOff = false;
  private int lastSetting; 
  private int direction = 1;
  private double shakeStartTime = -1;
  private double shakeIterations = 0;

  private double percentOutputSet = 0.0;     // for unused percent output control

  public Drum() {
    drumMotor.restoreFactoryDefaults();
    drumPIDController = drumMotor.getPIDController();
    drumEncoder = drumMotor.getEncoder();

    drumPIDController.setReference(0.0, ControlType.kVelocity);

    rateLimiter = new SlewRateLimiter(DrumConstants.drumKRampRate, 0);

    drumPIDController.setP(DrumConstants.drumKP);
    drumPIDController.setI(DrumConstants.drumKI);
    drumPIDController.setD(DrumConstants.drumKD);
    drumPIDController.setFF(DrumConstants.drumKF);
    drumPIDController.setIZone(DrumConstants.drumKIZone);
    drumPIDController.setOutputRange(DrumConstants.drumKMinOutput, DrumConstants.drumKMaxOutput);

  }

  public void reengageRampLimit() {
    if (isRateLimitOff) {
      rateLimiter.reset(drumEncoder.getVelocity());
      isRateLimitOff = false;
    }
  }

  public void extremeSpin() {
    reengageRampLimit();
    lastSetting = 4;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumExtreme), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("Extreme run");
  }

  public void highSpin() {
    reengageRampLimit();
    lastSetting = 3;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumHigh), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("High run");
  }

  public void mediumSpin() {
    reengageRampLimit();
    lastSetting = 2;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumMedium), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("Medium run");
  }

  public void lowSpin() {
    reengageRampLimit();
    lastSetting = 1;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumLow), ControlType.kVelocity);
    System.out.println(drumEncoder.getVelocity());
    System.out.println("Low run");
  }

  public void drumPowerOff() {
    isRateLimitOff = true;
    lastSetting = 0;
    drumMotor.set(0.0);
    drumEncoder.getVelocity();
    System.out.println("Power off run");
  }

  public boolean shakeDrum() {
    isRateLimitOff = true;
    if (shakeIterations < 10) {
      if (shakeStartTime == -1) {
        drumMotor.set(0.3 * direction);
        shakeStartTime = System.nanoTime() / 1000000;
        direction *= -1;
      }
      if ((System.nanoTime() / 1000000) - shakeStartTime >= 150) {
        drumMotor.set(0.3 * direction);
        direction *= -1;
        shakeIterations += 1;
        shakeStartTime = System.nanoTime() / 1000000;
      }
    } else {
      shakeIterations = 0;
      shakeStartTime = -1;
      direction = 1;
      return true;
    }
    return false;
  }

  public int getLastSetting() {
    return lastSetting;
  }

  public static Drum getInstance() {
    return instance;
  }

  public void setPercentOutputSet(double percentOutputSet) {
    System.out.println("Drum.java::setPercentOutputSet" + percentOutputSet);
    this.percentOutputSet = percentOutputSet;
    drumMotor.set(percentOutputSet);
  }

  public void setSpeed(int i) {} //FILLER FOR NOW WILL FILL IN TONIGHT FROM CODE AT THE BOTTOM

  @Override
  public void periodic() {}


  //***** EXPERIMENTAL PERCENT OUTPUT - NOT USED YET *****

  // Percent Output control, runs every loop to print the stuff
  public void percentOutputIncrement() {
    //drumMotor.set(percentOutputSet);
    drumEncoder.getVelocity();
    System.out.println("Percent output run at " + percentOutputSet);
  }

  // called once when the percent is triggered to be increased or decreased
  public void changePercentSet(boolean upDown) {
    if ((upDown == true) && (percentOutputSet < 1.0)) {
      percentOutputSet += 0.05;
    } else if ((upDown == false) && (percentOutputSet > -1.0)) {
      percentOutputSet -= 0.05;
    }
  }

  public void resetPercentSet() {
    percentOutputSet = 0.0;
  }
}

/*
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.SlewRateLimiter;
import frc.robot.constants.DrumConstants;


 * <b> The Drum subsystem </b>
 * <p>
 * This mechanism holds up to 5 power cells, after the Intake and before the ball transfer / Flywheel. It spins at variable
 * speeds to unload power cells, and can shake to dislodge power cells stuck on top of one another inside the Drum.
 * @author Jared Brown, Caleb Walters, Amelia Bingamin


public class Drum extends SubsystemBase {
   Creates a new Drum.

  private CANSparkMax drumMotor = new CANSparkMax(DrumConstants.drumMotorCANID, MotorType.kBrushless);
  private CANPIDController drumPIDController;
  private CANEncoder drumEncoder;
  private SlewRateLimiter rateLimiter;
  private static Drum instance = new Drum();
  public boolean drumPctOutputMode = false;
  private boolean isRateLimitOff = false;
  private int lastSetting;
  private int direction = 1;
  private double shakeStartTime = -1;
  private double shakeIterations = 0;

  // private double percentOutputSet = 0.0;     // for unused percent output control

  
   * Initializes the Drum subsystem once at code deploy. <b> Does not run at each enable! </b>
   * <p>
   * Sets the Drum's PIDF constants, located in the Constants class, and creates rateLimiter and encoder objects.
   * @author Jared Brown, Caleb Walters, Amelia Bingamin
  
  public Drum() {

    // Set defaults and get all Motor components and set type
    // Set PID constants

    drumMotor.restoreFactoryDefaults();
    drumPIDController = drumMotor.getPIDController();
    drumEncoder = drumMotor.getEncoder();

    drumPIDController.setReference(0.0, ControlType.kVelocity);

    rateLimiter = new SlewRateLimiter(DrumConstants.drumKRampRate, 0);

    drumPIDController.setP(DrumConstants.drumKP);
    drumPIDController.setI(DrumConstants.drumKI);
    drumPIDController.setD(DrumConstants.drumKD);
    drumPIDController.setFF(DrumConstants.drumKF);
    drumPIDController.setIZone(DrumConstants.drumKIZone);
    drumPIDController.setOutputRange(DrumConstants.drumKMinOutput, DrumConstants.drumKMaxOutput);

  }

  
   * Called one during each run of a nonzero spin speed method. This is to turn the rateLimiter back on at the Drum motor's current
   * velocity if it was turned off during the drumPowerOff and shakeDrum methods. It prevents sudden, violent jumps in speed.
   * @see Drum.drumPowerOff
   * @see Drum.shakeDrum
  
  public void reengageRampLimit() {
    if (isRateLimitOff) {
      rateLimiter.reset(drumEncoder.getVelocity());
      isRateLimitOff = false;
    }
  }

  
   * <b> Spin (I Am Speed. K-CHOW!): </b>
   * <p>
   * Sets the Drum motor to whatever the index level is in the drumSpeeds array.
   * @see commands.teleop.DrumVelocitySlow
   * @see commands.teleop.DrumVelocitySpeed
  
  public void setSpeed(int level) {
    reengageRampLimit();
    lastSetting = level;
    drumPIDController.setReference(rateLimiter.calculate(DrumConstants.drumSpeeds[level]), ControlType.kVelocity);
  }


  
   * <b> Shaking the Drum: </b>
   * <p>
   * Shakes the Drum rapidly, 5 times in each direction plus one extra time in the positive direction to slow it down initially.
   * Utilizes System.nanoTime to create a delay in between each change in direction.
   * <p>
   * The returned boolean tells the AgitateDrum command whether or not to keep running. Avoids using a loop as <b> loops
   * confuse the CommandScheduler</b>.
   * @see commands.AgitateDrum
   * @return Boolean -- whether or not the Drum has changed direction the specified number of times
  
  public boolean shakeDrum() {
    isRateLimitOff = true;
    if (shakeIterations < 10) {
      if (shakeStartTime == -1) {
        drumMotor.set(DrumConstants.drumShakePct * direction);
        shakeStartTime = System.nanoTime() / 1000000;
        direction *= -1;
      }
      if ((System.nanoTime() / 1000000) - shakeStartTime >= 150) {
        drumMotor.set(DrumConstants.drumShakePct * direction);
        direction *= -1;
        shakeIterations += 1;
        shakeStartTime = System.nanoTime() / 1000000;
      }
    } else {
      shakeIterations = 0;
      shakeStartTime = -1;
      direction = 1;
      return true;
    }
    return false;
  }
    
     * <b> Never giving it up! </b>
     * <p>
     * It'll never give up it's last state
     * @return lastSetting
     * @author Rick Astley
     
  public int getLastSetting() {
    return lastSetting;
  }

  
   * <b> Returns Drum Instance </b>
   * <p>
   * Returns the instance created above for Drum.
   * @return instance
   
  public static Drum getInstance() {
    return instance;
  }

  // Periodic NOT USED for now
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
}
*/