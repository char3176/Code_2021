// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.Constants;
import frc.robot.Gains;

public class ExampleSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */
  private static ExampleSubsystem instance = new ExampleSubsystem();

  public ExampleSubsystem() {
     /** Hardware */
	TalonFX _talon = new TalonFX(1);
	Joystick _joy = new Joystick(0);
	
    /** Used to create string thoughout loop */
	StringBuilder _sb = new StringBuilder();
	int _loops = 0;
	
    /** Track button state for single press event */
	boolean _lastButton1 = false;

	/** Save the target position to servo to */
  double targetPositionRotations;

  /* Factory Default all hardware to prevent unexpected behaviour */
  _talon.configFactoryDefault();
		
  /* Config the sensor used for Primary PID and sensor direction */
      _talon.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 
                                          Constants.kPIDLoopIdx,
                                  Constants.kTimeoutMs);

  /* Ensure sensor is positive when output is positive */
  _talon.setSensorPhase(Constants.kSensorPhase);

  /**
   * Set based on what direction you want forward/positive to be.
   * This does not affect sensor phase. 
   */ 
  _talon.setInverted(Constants.kMotorInvert);
  /*
   * Talon FX does not need sensor phase set for its integrated sensor
   * This is because it will always be correct if the selected feedback device is integrated sensor (default value)
   * and the user calls getSelectedSensor* to get the sensor's position/velocity.
   * 
   * https://phoenix-documentation.readthedocs.io/en/latest/ch14_MCSensor.html#sensor-phase
   */
      // _talon.setSensorPhase(true);

  /* Config the peak and nominal outputs, 12V means full */
  _talon.configNominalOutputForward(0, Constants.kTimeoutMs);
  _talon.configNominalOutputReverse(0, Constants.kTimeoutMs);
  _talon.configPeakOutputForward(1, Constants.kTimeoutMs);
  _talon.configPeakOutputReverse(-1, Constants.kTimeoutMs);

  /**
   * Config the allowable closed-loop error, Closed-Loop output will be
   * neutral within this range. See Table in Section 17.2.1 for native
   * units per rotation.
   */
  _talon.configAllowableClosedloopError(0, Constants.kPIDLoopIdx, Constants.kTimeoutMs);

  /* Config Position Closed Loop gains in slot0, tsypically kF stays zero. */
  _talon.config_kF(Constants.kPIDLoopIdx, Constants.kGains.kF, Constants.kTimeoutMs);
  _talon.config_kP(Constants.kPIDLoopIdx, Constants.kGains.kP, Constants.kTimeoutMs);
  _talon.config_kI(Constants.kPIDLoopIdx, Constants.kGains.kI, Constants.kTimeoutMs);
  _talon.config_kD(Constants.kPIDLoopIdx, Constants.kGains.kD, Constants.kTimeoutMs);
  }

	void commonLoop() {
		/* Gamepad processing */
		double leftYstick = _joy.getY();
		boolean button1 = _joy.getRawButton(1);	// X-Button
		boolean button2 = _joy.getRawButton(2);	// A-Button

		/* Get Talon's current output percentage */
		double motorOutput = _talon.getMotorOutputPercent();

		/* Deadband gamepad */
		if (Math.abs(leftYstick) < 0.10) {
			/* Within 10% of zero */
			leftYstick = 0;
		}

		/* Prepare line to print */
		_sb.append("\tout:");
		/* Cast to int to remove decimal places */
		_sb.append((int) (motorOutput * 100));
		_sb.append("%");	// Percent

		_sb.append("\tpos:");
		_sb.append(_talon.getSelectedSensorPosition(0));
		_sb.append("u"); 	// Native units

		/**
		 * When button 1 is pressed, perform Position Closed Loop to selected position,
		 * indicated by Joystick position x10, [-10, 10] rotations
		 */
		if (!_lastButton1 && button1) {
			/* Position Closed Loop */

			/* 10 Rotations * 2048 u/rev in either direction */
			targetPositionRotations = leftYstick * 10.0 * 2048;
			_talon.set(TalonFXControlMode.Position, targetPositionRotations);
		}

		/* When button 2 is held, just straight drive */
		if (button2) {
			/* Percent Output */

			_talon.set(TalonFXControlMode.PercentOutput, leftYstick);
		}

		/* If Talon is in position closed-loop, print some more info */
		if (_talon.getControlMode() == TalonFXControlMode.Position.toControlMode()) {
			/* ppend more signals to print when in speed mode. */
			_sb.append("\terr:");
			_sb.append(_talon.getClosedLoopError(0));
			_sb.append("u");	// Native Units

			_sb.append("\ttrg:");
			_sb.append(targetPositionRotations);
			_sb.append("u");	/// Native Units
		}

		/**
		 * Print every ten loops, printing too much too fast is generally bad
		 * for performance.
		 */
		if (++_loops >= 10) {
			_loops = 0;
			System.out.println(_sb.toString());
		}

		/* Reset built string for next loop */
		_sb.setLength(0);
		
		/* Save button state for on press detect */
		_lastButton1 = button1;
    }
    
	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		commonLoop();
	}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run



  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }
}
