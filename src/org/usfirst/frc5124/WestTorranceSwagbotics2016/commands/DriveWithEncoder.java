package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveWithEncoder extends Command {
	
	public double encoderTicks = 0;
	public double max;
	public int last;

    public DriveWithEncoder(double inches, double maxOutput, double timeout) {
        encoderTicks = inches * 11.46511627906977;
        max = maxOutput;
        setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	last = Robot.encoderDriveTrain.getEncoder();
    	Robot.encoderDriveTrain.setOutputRange(-max, max);
    	Robot.encoderDriveTrain.resetEncoder();
    	Robot.encoderDriveTrain.setSetpoint(encoderTicks);
    	Robot.encoderDriveTrain.enable();
    	//Robot.driveTrain.setSetpoint(Robot.driveTrain.getGyro());
    	//Robot.driveTrain.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.pidHandler.setEncoderOutputs();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return ((Math.abs(Robot.encoderDriveTrain.getEncoder() - (last + encoderTicks)) < 13) || isTimedOut());
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.pidHandler.disableAll();
    	Robot.encoderDriveTrain.setOutputRange(-1, 1);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
