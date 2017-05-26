package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeDisablePID extends Command {

    public IntakeDisablePID() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.stop();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.intake.disable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.enable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
