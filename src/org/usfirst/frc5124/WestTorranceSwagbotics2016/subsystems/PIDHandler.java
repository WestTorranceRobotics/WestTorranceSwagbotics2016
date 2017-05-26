package org.usfirst.frc5124.WestTorranceSwagbotics2016.subsystems;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PIDHandler extends Subsystem {
    
	public double gyroOutput = 0;
	public double encoderOutput = 0;
	public boolean gyroEnabled = false;
	public boolean encoderEnabled = false;
	
	private final RobotDrive robotDrive = RobotMap.driveTrainRobotDrive;
    

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void enableAll() {
    	Robot.driveTrain.enable();
    	Robot.encoderDriveTrain.enable();
    	gyroEnabled = true;
    	encoderEnabled = true;
    }
    
    public void disableAll() {
    	Robot.driveTrain.disable();
    	Robot.encoderDriveTrain.disable();
    	gyroEnabled = false;
    	encoderEnabled = false;
    	gyroOutput = 0;
    	encoderOutput = 0;
    }
    
    public void enableGyroPID() {
    	Robot.driveTrain.enable();
    	gyroEnabled = true;
    }
    
    public void disableGyroPID() {
    	Robot.driveTrain.disable();
    	gyroEnabled = false;
    	gyroOutput = 0;
    }
    
    public void enableEncoderPID() {
    	Robot.encoderDriveTrain.enable();
    	encoderEnabled = true;
    }
    
    public void disableEncoderPID() {
    	Robot.encoderDriveTrain.disable();
    	encoderEnabled = false;
    	encoderOutput = 0;
    }
    
    public void writeGyroPID(double input) {
    	gyroOutput = input;
    }
    
    public void writeEncoderPID(double input) {
    	encoderOutput = input;
    }
    
    public double returnGyroPID() {
    	return gyroOutput;
    }
    
    public double returnEncoderOutput() {
    	return encoderOutput;
    }
    
    public void setGyroSetpoint(double setpoint) {
    	Robot.driveTrain.setSetpoint(setpoint);
    }
    
    public void setEncoderSetpoint(double setpoint) {
    	Robot.encoderDriveTrain.setSetpoint(setpoint);
    }
    
    public void setAllSetpoints(double encoderSetpoint, double gyroSetpoint) {
    	Robot.encoderDriveTrain.setSetpoint(encoderSetpoint);
    	Robot.driveTrain.setSetpoint(gyroSetpoint);
    }
    
    public void setPIDOutputs() {
    	robotDrive.arcadeDrive(encoderOutput, gyroOutput);
    }
    
    public void setEncoderOutputs() {
    	robotDrive.arcadeDrive(encoderOutput, 0);
    }
    
    
    
}

