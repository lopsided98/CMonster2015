/* 
 * Copyright (c) 2014 RobotsByTheC. All rights reserved.
 *
 * Open Source Software - may be modified and shared by FRC teams. The code must
 * be accompanied by the BSD license file in the root directory of the project.
 */
package org.usfirst.frc.team2084.CMonster2015.commands;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.usfirst.frc.team2084.CMonster2015.Robot;
import org.usfirst.frc.team2084.CMonster2015.drive.GyroMecanumDriveAlgorithm;
import org.usfirst.frc.team2084.CMonster2015.drive.WheelController;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author Ben Wolsieffer
 */
public class GyroLoggingCommand extends Command {

	public static final String NUM_SAMPLES_KEY = "Gyro Log Samples";
	public static final int DEFAULT_NUM_SAMPLES = 50000;
	public static final String LOG_FILE = "/home/lvuser/gyro-log.csv";

	static {
		SmartDashboard.putNumber(NUM_SAMPLES_KEY, DEFAULT_NUM_SAMPLES);
	}

	private class LoggingThread implements Runnable {

		@Override
		public void run() {
			int numSamples = GyroLoggingCommand.this.numSamples;
			long timeLog[] = new long[numSamples];
			double headingLog[] = new double[numSamples];
			double rateLog[] = new double[numSamples];

			for (int i = 0; i < numSamples; i++) {
				if (!running) {
					break;
				}
				timeLog[i] = System.nanoTime();
				GyroMecanumDriveAlgorithm<WheelController<SpeedController>> drive = Robot.driveSubsystem.getMecanumDriveAlgorithm();
				headingLog[i] = drive.getGyroAngle();
				rateLog[i] = drive.getGyroRate();
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
				}
			}

			File logFile = new File(LOG_FILE);
			try {
				logFile.createNewFile();
				try (PrintStream stream = new PrintStream(logFile)) {
					for (int i = 0; i < numSamples; i++) {
						if (!running) {
							break;
						}
						stream.println(timeLog[i] + "," + headingLog[i] + "," + rateLog[i]);
					}
				}
			} catch (IOException e) {
				System.err.println("Unable to create log file: " + LOG_FILE);
			}
		}
	}

	private boolean running = false;
	private int numSamples;
	private Thread loggingThread;

	public GyroLoggingCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	@Override
	protected void initialize() {
		numSamples = (int) SmartDashboard.getNumber(NUM_SAMPLES_KEY, DEFAULT_NUM_SAMPLES);

		running = true;
		loggingThread = new Thread(new LoggingThread());
		loggingThread.start();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return !loggingThread.isAlive();
	}

	@Override
	protected void end() {
		running = false;
	}

	@Override
	protected void interrupted() {
		end();
	}
}