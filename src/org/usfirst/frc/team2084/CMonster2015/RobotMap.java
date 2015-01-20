/* 
 * Copyright (c) 2014 RobotsByTheC. All rights reserved.
 *
 * Open Source Software - may be modified and shared by FRC teams. The code must
 * be accompanied by the BSD license file in the root directory of the project.
 */
package org.usfirst.frc.team2084.CMonster2015;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDSource.PIDSourceParameter;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SynchronizedRadianGyro;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
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

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	public static SynchronizedRadianGyro driveSubsystemGyro;
	public static Accelerometer driveSubsystemAccelerometer;

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
        

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
		driveSubsystemGyro = new SynchronizedRadianGyro(0);
		driveSubsystemAccelerometer = new BuiltInAccelerometer(Range.k8G);
	}
}