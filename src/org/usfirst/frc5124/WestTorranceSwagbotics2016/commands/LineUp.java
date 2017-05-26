package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class LineUp extends Command {
	
	public double targetDegree = 0;
	public double initialDegree = 0;

	public LineUp() {
		requires(Robot.driveTrain);	
	}
	
	protected void initialize() {
		initialDegree = Robot.driveTrain.getGyro();
		try{
			targetDegree = initialDegree + ((Robot.camera.centerX[Robot.camera.targetIndex()] - 190) * .209375);
		} catch(Exception e) {
			targetDegree = initialDegree;
		}
		Robot.pidHandler.enableGyroPID();
		//Robot.pidHandler.disableEncoderPID();
		Robot.pidHandler.setGyroSetpoint(targetDegree);
		RobotMap.driveTrainRobotDrive.setMaxOutput(1);
		SmartDashboard.putNumber("Target", targetDegree);
	}

	
	protected void execute() {
		SmartDashboard.putNumber("target", targetDegree);
		Robot.pidHandler.setPIDOutputs();
	}

	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	
	protected void end() {
		// TODO Auto-generated method stub
		/*
		Robot.pidHandler.disableGyroPID();
		Robot.driveTrain.stop();
		RobotMap.driveTrainRobotDrive.setMaxOutput(.5);
		*/
	}

	
	protected void interrupted() {
		Robot.driveTrain.stop();
		Robot.pidHandler.disableGyroPID();
		RobotMap.driveTrainRobotDrive.setMaxOutput(.5);		
	}

}