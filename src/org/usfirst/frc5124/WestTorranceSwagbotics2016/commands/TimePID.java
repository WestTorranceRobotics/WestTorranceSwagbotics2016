package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimePID extends Command {

	public double initialDegree = 0;
	public double speed = 0;
	
    public TimePID(double time, double speed) {
        requires(Robot.driveTrain);
        setTimeout(time);
        this.speed = speed;
    }

    protected void initialize() {
    	initialDegree = Robot.driveTrain.getGyro();
    	Robot.pidHandler.setGyroSetpoint(initialDegree);
    	Robot.pidHandler.enableGyroPID();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//Robot.pidHandler.timedGyroPID(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
