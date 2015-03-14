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
import org.usfirst.frc.team2084.CMonster2015.drive.processors.LinearRamper;
import org.usfirst.frc.team2084.CMonster2015.drive.processors.LinearRamper.Type;
import org.usfirst.frc.team2084.CMonster2015.drive.processors.RescalingDeadband;
import org.usfirst.frc.team2084.CMonster2015.drive.processors.Scaler;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The command that implements the control, filtering and scaling of our mecanum
 * drive system.
 *
 * @author Ben Wolsieffer
 */
public class MecanumDriveCommand extends Command {

    /**
     * The joystick z-axis value below which the robot will not rotate. this is
     * to prevent accidental small twists of the joystick from affecting its
     * trajectory.
     */
    public static final double ROTATION_DEADBAND = 0.2;
    /**
     * The maximum speed that the robot is allowed to rotate at. The joystick
     * value is scaled down to this value.
     */
    public static final double MAX_ROTATE_SPEED = 0.5;
    public static final double MAX_SPEED = 0.75;
    public static final double MAX_AUTO_ROTATE_SPEED = 0.2;
    public static final double AUTO_ROTATE_RAMP_RATE = 0.8;

    private final RescalingDeadband rotationDeadband = new RescalingDeadband(ROTATION_DEADBAND);
    private final Scaler rotationScaler = new Scaler(MAX_ROTATE_SPEED);
    private final Scaler driveScaler = new Scaler(MAX_SPEED);
    private final LinearRamper autoRotateRamper = new LinearRamper(AUTO_ROTATE_RAMP_RATE, Type.UP);

    private final EncoderGyroMecanumDriveAlgorithm<EncoderWheelController<SpeedController>> mecanumDriveAlgorithm = RobotMap.driveSubsystemMecanumDriveAlgorithm;

    private final boolean fieldOriented;

    /**
     * Creates a field oriented {@link MecanumDriveCommand}.
     */
    public MecanumDriveCommand() {
        this(true);
    }

    /**
     * Creates a field or robot oriented {@link MecanumDriveCommand}.
     * 
     * @param fieldOriented whether to use field oriented control
     */
    public MecanumDriveCommand(boolean fieldOriented) {
        this.fieldOriented = fieldOriented;
        // This command drives, so it requires the drive subsystem.
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveSubsystem);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    /**
     * Resets the setpoint of the heading PID controller.
     */
    @Override
    protected void initialize() {
        mecanumDriveAlgorithm.resetSetpoint();
    }

    /**
     * Updates the robot's speed based on the joystick values.
     */
    @Override
    protected void execute() {
        // This is the joystick that we use as input for driving.
        Joystick driveJoystick = Robot.oi.getDriveJoystick();
        // Store the axis values.
        double x = driveJoystick.getX();
        double y = driveJoystick.getY(); // Forward is negative on joysticks
        double rotation = driveJoystick.getZ();
        // This will hold the scaled rotation value. We scale down this value
        // because otherwise the robot is too hard to control with the joystick
        // twist and we don't need our full possible rotation speed (its pretty
        // fast).
        double scaledRotation = rotationScaler.process(rotationDeadband.process(rotation));

        // Send debugging values.
        SmartDashboard.putNumber("Joystick X", x);
        SmartDashboard.putNumber("Joystick Y", y);
        SmartDashboard.putNumber("Joystick Rotation", rotation);
        SmartDashboard.putNumber("Scaled Rotation", scaledRotation);

        if (!driveJoystick.getTrigger()) {
            x = driveScaler.process(x);
            y = driveScaler.process(y);
        }
        // Annoying inversions
        y *= -1;
        scaledRotation *= -1;
        // Actually drive the robot using the joystick values for x and y and
        // the scaled z value.
        if (fieldOriented) {
            mecanumDriveAlgorithm.driveFieldCartesian(x, y, scaledRotation);

            int pov = driveJoystick.getPOV();
            if (pov != -1) {
                mecanumDriveAlgorithm.driveFieldHeadingCartesian(x, y, Math.toRadians(pov), autoRotateRamper.process(MAX_AUTO_ROTATE_SPEED));
            }
        } else {
            mecanumDriveAlgorithm.driveCartesian(x, y, scaledRotation);
        }
    }

    /**
     * This command never ends on its own but it could be interrupted, for
     * example if we reverted back to our failsafe driving mode.
     *
     * @return false
     */
    @Override
    protected boolean isFinished() {
        return false;
    }

    /**
     * Stops the drive motors.
     */
    @Override
    protected void end() {
        mecanumDriveAlgorithm.stop();
    }

    /**
     * Stops the drive motors.
     */
    @Override
    protected void interrupted() {
        end();
    }
}