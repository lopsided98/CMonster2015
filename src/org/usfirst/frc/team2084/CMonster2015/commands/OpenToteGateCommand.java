/* 
 * Copyright (c) 2015 RobotsByTheC. All rights reserved.
 *
 * Open Source Software - may be modified and shared by FRC teams. The code must
 * be accompanied by the BSD license file in the root directory of the project.
 */
package org.usfirst.frc.team2084.CMonster2015.commands;

import org.usfirst.frc.team2084.CMonster2015.Robot;
import org.usfirst.frc.team2084.CMonster2015.subsystems.ToteLifterSubsystem.GateState;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Opens the tote gate (which is really a tab on the floor), allowing totes to
 * slide out the front.
 * 
 * @author Ben Wolsieffer
 */
public class OpenToteGateCommand extends Command {

    public OpenToteGateCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.toteLifterSubsystem);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    /**
     * Opens the tote gate.
     */
    @Override
    protected void initialize() {
        Robot.toteLifterSubsystem.setGateState(GateState.OPEN);
    }

    @Override
    protected void execute() {
    }

    /**
     * The gate opens fast so this returns true immediately.
     * 
     * @return true
     */
    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {
    }

    @Override
    protected void interrupted() {
    }
}
