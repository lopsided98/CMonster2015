// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
// RobotBuilder Version: 1.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.
package org.usfirst.frc2084.CMonster2014.commands;

import edu.wpi.first.wpilibj.ADXL345_I2C;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2084.CMonster2014.RobotMap;
import org.usfirst.frc2084.CMonster2014.TargetTrackingCommunication;

/**
 *
 */
public class FunCommand extends Command {

    private double pi;
    private double pi4;
    private boolean piPositive = true;
    private long piIndex = 1;

    public FunCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        setRunWhenDisabled(true);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Calculate Pi!!!!
        pi4 += (piPositive ? 1.0 : -1.0) / piIndex;
        piPositive = !piPositive;
        piIndex += 2.0;
        pi = pi4 * 4.0;
        DriverStationLCD.getInstance().println(DriverStationLCD.Line.kUser1, 1, "Pi: " + pi);
        DriverStationLCD.getInstance().updateLCD();
        // Pi
        // report misc values
        SmartDashboard.putNumber("Gyro Rate", RobotMap.driveSubsystemSteeringGyro.getRate());
        SmartDashboard.putNumber("Gyro Angle", RobotMap.driveSubsystemSteeringGyro.getAngle());
        ADXL345_I2C.AllAxes accel = RobotMap.driveSubsystemAccelerometer.getAccelerations();
        SmartDashboard.putNumber("X Acceleration", accel.XAxis);
        SmartDashboard.putNumber("Y Acceleration", accel.YAxis);
        String targetState;
        switch (TargetTrackingCommunication.getState().value) {
            case TargetTrackingCommunication.State.HOT_VALUE:
                targetState = "Hot";
                break;
            case TargetTrackingCommunication.State.NOT_HOT_VALUE:
                targetState = "Not hot";
                break;
            default:
            case TargetTrackingCommunication.State.UNKNOWN_VALUE:
                targetState = "Unknown";
                break;
        }
        SmartDashboard.putString("Goal State", targetState);

        SmartDashboard.putNumber("Encoder Distance", RobotMap.driveSubsystemRearRightEncoder.getDistance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
