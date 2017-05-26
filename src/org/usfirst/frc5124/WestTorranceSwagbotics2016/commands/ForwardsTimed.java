package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ForwardsTimed extends Command {

    public ForwardsTimed() {
        requires(Robot.driveTrain);
        setTimeout(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.stop();
    	RobotMap.driveTrainRobotDrive.setMaxOutput(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	RobotMap.driveTrainRobotDrive.arcadeDrive(-0.68, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.stop();
    	RobotMap.driveTrainRobotDrive.setMaxOutput(0.7);
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
