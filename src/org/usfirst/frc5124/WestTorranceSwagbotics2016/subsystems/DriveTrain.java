package org.usfirst.frc5124.WestTorranceSwagbotics2016.subsystems;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.RobotMap;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.commands.*;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class DriveTrain extends PIDSubsystem {

    public DriveTrain() {
    	//super("DriveTrain", 0.2, 0.001, 0.1);
    	//super("DriveTrain", 0.16 , 0, 0);
    	super("DriveTrain", 0.2 , 0.02, 0);
        setAbsoluteTolerance(0.85);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("DriveTrain", "PIDSubsystem Controller", getPIDController());
        setOutputRange(-.8, .8);
	}

    private final RobotDrive robotDrive = RobotMap.driveTrainRobotDrive;
    private final Compressor compressor = RobotMap.driveTrainCompressor;
	private final ADXRS450_Gyro gyro = RobotMap.gyro;
	

    protected double returnPIDInput() {
        
        return gyro.getAngle();

    }

    protected void usePIDOutput(double output) {
    	
    	if(output < 0 && output > -0.05) {
    		Robot.pidHandler.writeGyroPID(-.05);
    	} else if(output > 0 && output < 0.05) {
    		Robot.pidHandler.writeGyroPID(.05);
    	} else {
    		Robot.pidHandler.writeGyroPID(output);
    	}
    	//Robot.pidHandler.writeGyroPID(output);
    }
    
    Robot robot = new Robot();
    
    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithJoystick());
    }
    
    public void arcade(Joystick joy) {
    	robotDrive.arcadeDrive(joy);
    }
    
    public void compressorOff() {													/* Turn off the compressor */
    	compressor.setClosedLoopControl(false);
    	compressor.stop();
    }
    
    public void compressorOn() {													/* Turn on the compressor */
    	compressor.setClosedLoopControl(true);
    	compressor.start();
    }
    
    public void stop() {
    	robotDrive.arcadeDrive(0, 0);
    }   
    
    public void tank() {
    	robotDrive.tankDrive(Robot.oi.getLeft(), Robot.oi.getRight());
    }
    
	public double getGyro() {
		return gyro.getAngle();
	}

	public void calibrateGyro() {
		gyro.calibrate();
	}
    
    public double getGyroRotationRate() {
    	return gyro.getRate();
    }
    
}

