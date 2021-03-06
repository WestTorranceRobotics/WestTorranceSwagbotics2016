package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WaitForLineUp extends Command {

    public WaitForLineUp() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.pidHandler.disableGyroPID();
		Robot.driveTrain.stop();
		RobotMap.driveTrainRobotDrive.setMaxOutput(.5);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;/*(Math.abs(Robot.camera.centerX[Robot.camera.targetIndex()] - 177) < 8);*/
    }

    // Called once after isFinished returns true
    protected void end() {
   
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
