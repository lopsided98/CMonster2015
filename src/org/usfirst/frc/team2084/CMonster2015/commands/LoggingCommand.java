/* 
 * Copyright (c) 2015 RobotsByTheC. All rights reserved.
 *
 * Open Source Software - may be modified and shared by FRC teams. The code must
 * be accompanied by the BSD license file in the root directory of the project.
 */
package org.usfirst.frc.team2084.CMonster2015.commands;

import org.usfirst.frc.team2084.CMonster2015.Robot;
import org.usfirst.frc.team2084.CMonster2015.RobotMap;
import org.usfirst.frc.team2084.CMonster2015.drive.EncoderGyroMecanumDriveAlgorithm;
import org.usfirst.frc.team2084.CMonster2015.drive.EncoderWheelController;
import org.usfirst.frc.team2084.CMonster2015.drive.Location;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Command that is always running and reports debugging values.
 *
 * @author Ben Wolsieffer
 */
public class LoggingCommand extends Command {

    /**
     * The length of a match in seconds.
     */
    public static final double MATCH_LENGTH = 150;

    private final Accelerometer accelerometer = RobotMap.driveSubsystemAccelerometer;
    private final EncoderGyroMecanumDriveAlgorithm<EncoderWheelController<SpeedController>> mecanumDriveAlgorithm = RobotMap.driveSubsystemMecanumDriveAlgorithm;

    public LoggingCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        // Make sure this command still reports data when the
        // robot is disabled.
        setRunWhenDisabled(true);
    }

    /**
     * Does nothing.
     */
    @Override
    protected void initialize() {
    }

    /**
     * Prints out debugging data to the SmartDashboard.
     */
    @Override
    protected void execute() {

        // Send location
        Location location = mecanumDriveAlgorithm.getLocation();
        SmartDashboard.putNumber("X Location", location.getX());
        SmartDashboard.putNumber("Y Location", location.getY());

        // Report gyro values
        SmartDashboard.putNumber("Gyro Angle", mecanumDriveAlgorithm.getHeading());
        SmartDashboard.putNumber("Gyro Rate", mecanumDriveAlgorithm.getRotationRate());

        // Report accelerometer values
        SmartDashboard.putNumber("X Acceleration", accelerometer.getX());
        SmartDashboard.putNumber("Y Acceleration", accelerometer.getY());

        // Report remaining match time
        double matchTime = Timer.getMatchTime();
        SmartDashboard.putNumber("Time Remaining", matchTime < 0 ? 0 : matchTime);

        // Report energy and current
        SmartDashboard.putNumber("Total energy (J)", Robot.pdp.getTotalEnergy());
        SmartDashboard.putNumber("Total current (amps)", Robot.pdp.getTotalCurrent());

        // Report sensors
        SmartDashboard.putBoolean("Container Hook Raised", Robot.containerHookSubsystem.isRaised());

        SmartDashboard.putBoolean("Left Lifter Lowered",
                Robot.toteLifterSubsystem.isLeftLifterLowered());
        SmartDashboard.putBoolean("Right Lifter Lowered",
                Robot.toteLifterSubsystem.isRightLifterLowered());

    }

    /**
     * This command never finishes.
     *
     * @return false
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /**
     * Does nothing.
     */
    @Override
    protected void end() {
    }

    /**
     * Does nothing.
     */
    @Override
    protected void interrupted() {
    }
}
