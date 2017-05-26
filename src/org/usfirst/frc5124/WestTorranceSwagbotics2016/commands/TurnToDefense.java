package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TurnToDefense extends Command {

	double degreesToTurn = 0;
	
    public TurnToDefense() {
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.defenseIsRight){
    		degreesToTurn = 155;
    	} else {
    		degreesToTurn = -155;
    	}
    	Robot.pidHandler.setGyroSetpoint(degreesToTurn + Robot.driveTrain.getGyro());
    	Robot.pidHandler.enableGyroPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pidHandler.setPIDOutputs();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.driveTrain.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pidHandler.disableGyroPID();
    	Robot.driveTrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.pidHandler.disableGyroPID();
    	Robot.driveTrain.stop();
    }
}
