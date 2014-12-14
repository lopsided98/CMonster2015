/* 
 * Copyright (c) 2014 RobotsByTheC. All rights reserved.
 *
 * Open Source Software - may be modified and shared by FRC teams. The code must
 * be accompanied by the BSD license file in the root directory of the project.
 */
package org.usfirst.frc2084.CMonster2015;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.RadianGyro;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TempSensor;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static SpeedController driveSubsystemFrontLeftJaguar;
	public static SpeedController driveSubsystemFrontRightJaguar;
	public static SpeedController driveSubsystemRearLeftJaguar;
	public static SpeedController driveSubsystemRearRightJaguar;
	public static Encoder driveSubsystemRearRightEncoder;
	public static Solenoid sweeperSubsystemSolenoid;
	public static SpeedController sweeperSubsystemJaguar;
	public static Solenoid catcherSubsystemSolenoid;
	public static DigitalOutput ledSubsystemPin0;
	public static DigitalOutput ledSubsystemPin1;
	public static DigitalOutput ledSubsystemPin2;
	public static DigitalOutput ledSubsystemPin3;
	public static DigitalOutput ledSubsystemPin4;
	public static DigitalOutput ledSubsystemPin5;
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static RadianGyro driveSubsystemSteeringGyro;
	public static TempSensor driveSubsystemSteeringGyroTemp;
	public static ADXL345_I2C driveSubsystemAccelerometer;

	public static void init() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		driveSubsystemFrontLeftJaguar = new Jaguar(0);
		LiveWindow.addActuator("Drive Subsystem", "Front Left Jaguar", (Jaguar) driveSubsystemFrontLeftJaguar);

		driveSubsystemFrontRightJaguar = new Jaguar(1);
		LiveWindow.addActuator("Drive Subsystem", "Front Right Jaguar", (Jaguar) driveSubsystemFrontRightJaguar);

		driveSubsystemRearLeftJaguar = new Jaguar(2);
		LiveWindow.addActuator("Drive Subsystem", "Rear Left Jaguar", (Jaguar) driveSubsystemRearLeftJaguar);

		driveSubsystemRearRightJaguar = new Jaguar(3);
		LiveWindow.addActuator("Drive Subsystem", "Rear Right Jaguar", (Jaguar) driveSubsystemRearRightJaguar);

		driveSubsystemRearRightEncoder = new Encoder(0, 1, false, EncodingType.k4X);
		LiveWindow.addSensor("Drive Subsystem", "Rear Right Encoder", driveSubsystemRearRightEncoder);
		driveSubsystemRearRightEncoder.setDistancePerPulse(0.002908882);
		driveSubsystemRearRightEncoder.setPIDSourceParameter(PIDSourceParameter.kDistance);
		sweeperSubsystemSolenoid = new Solenoid(0, 0);
		LiveWindow.addActuator("Sweeper Subsystem", "Solenoid", sweeperSubsystemSolenoid);

		sweeperSubsystemJaguar = new Jaguar(4);
		LiveWindow.addActuator("Sweeper Subsystem", "Jaguar", (Jaguar) sweeperSubsystemJaguar);

		catcherSubsystemSolenoid = new Solenoid(0, 1);
		LiveWindow.addActuator("Catcher Subsystem", "Solenoid", catcherSubsystemSolenoid);

		ledSubsystemPin0 = new DigitalOutput(2);
		LiveWindow.addActuator("Led Subsystem", "Pin 0", ledSubsystemPin0);

		ledSubsystemPin1 = new DigitalOutput(3);
		LiveWindow.addActuator("Led Subsystem", "Pin 1", ledSubsystemPin1);

		ledSubsystemPin2 = new DigitalOutput(4);
		LiveWindow.addActuator("Led Subsystem", "Pin 2", ledSubsystemPin2);

		ledSubsystemPin3 = new DigitalOutput(5);
		LiveWindow.addActuator("Led Subsystem", "Pin 3", ledSubsystemPin3);

		ledSubsystemPin4 = new DigitalOutput(6);
		LiveWindow.addActuator("Led Subsystem", "Pin 4", ledSubsystemPin4);

		ledSubsystemPin5 = new DigitalOutput(7);
		LiveWindow.addActuator("Led Subsystem", "Pin 5", ledSubsystemPin5);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		driveSubsystemSteeringGyro = new RadianGyro(1);
		driveSubsystemSteeringGyroTemp = new TempSensor(2);
		driveSubsystemAccelerometer = new ADXL345_I2C(I2C.Port.kOnboard, Accelerometer.Range.k4G);
	}
}
