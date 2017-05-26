package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class LineUpAuto extends Command {
	
	public double targetDegree = 0;	
	public double initialDegree = 0;

	public LineUpAuto() {
		requires(Robot.driveTrain);	
		setTimeout(2);		// Time out after 2 seconds 
	}
	
	protected void initialize() {
		initialDegree = Robot.driveTrain.getGyro();		// record our current heading
		
		// try to calculate target degree by grabbing the position of the target from the camera
		try{
			// calculate by adding our current heading to (the target's distance from the center pixel * degrees per pixel)
			targetDegree = initialDegree + ((Robot.camera.centerX[Robot.camera.targetIndex()] - 170) * .209375);
		} catch(Exception e) {
			// if there's no target seen, set the target heading to the current heading
			targetDegree = initialDegree;
		}
		
		
		Robot.pidHandler.enableGyroPID();	// enable turning PID
		Robot.pidHandler.setGyroSetpoint(targetDegree);		// set the turning set point the the target degree that was calculated above
		RobotMap.driveTrainRobotDrive.setMaxOutput(1); 		// allow drivetrain full range of motor outputs
		SmartDashboard.putNumber("Target", targetDegree);	// send the target degree to the dashboard
	}

	
	protected void execute() {
		SmartDashboard.putNumber("target", targetDegree);	// send the target degree to the dashboard
		Robot.pidHandler.setPIDOutputs();	// set the PID output of the turning PID to the drivetrain
	}

	
	protected boolean isFinished() {
		return isTimedOut();	// finish after 2 seconds
	}

	
	protected void end() {
		// this use to end the PID after the command ended, but no longer
		/*					
		Robot.pidHandler.disableGyroPID();
		Robot.driveTrain.stop();
		RobotMap.driveTrainRobotDrive.setMaxOutput(.5);
		*/
	}

	
	protected void interrupted() {
			
	}

}