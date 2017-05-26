package org.usfirst.frc5124.WestTorranceSwagbotics2016.subsystems;


import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.RobotMap;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


/**
 *
 */
public class EncoderDriveTrain extends PIDSubsystem {
	
	public EncoderDriveTrain() {
    	super("EncoderDriveTrain", -0.006, 0, 0);
        setAbsoluteTolerance(11);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("EncoderDriveTrain", "PIDSubsystem Controller", getPIDController());
	}
	
    private final Encoder encoder = RobotMap.encoder;

    protected double returnPIDInput() {
        
       return encoder.get();

    }

    protected void usePIDOutput(double output) {
    	Robot.pidHandler.writeEncoderPID(output);
    }
    
    
    public void initDefaultCommand() {
    }
   
    public int getEncoder() {
    	return encoder.get();
    }
    
    public double getEncoderDistance() {
    	return encoder.getDistance();
    }
    
    public void resetEncoder() {
    	encoder.reset();
    }
    
}

