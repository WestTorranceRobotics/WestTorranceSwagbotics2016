package org.usfirst.frc5124.WestTorranceSwagbotics2016.subsystems;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.RobotMap;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class Intake extends PIDSubsystem {
	
	public static boolean intakeIsDown = true;

    public Intake() {
		super(0.7, 0, 1.5);
		setAbsoluteTolerance(0.03);
		getPIDController().setOutputRange(-0.4, 0.5);
        getPIDController().setContinuous(false);
        LiveWindow.addActuator("Intake", "PIDSubsystem Controller", getPIDController());
	}

	private final Spark intakePositioner = RobotMap.intakeIntakePositioner;
    private final Spark intakeMotor = RobotMap.intakeIntakeMotor;
    private final AnalogPotentiometer pot = RobotMap.pot;
    
    
    public void initDefaultCommand() {
        
    }
    
    public void intake() {
    	intakeMotor.set(-.75);
    }
    
    public void exhaust() {
    	intakeMotor.set(.75);
    }
    
    public void stop() {
    	intakeMotor.set(0);
    }
	
	protected double returnPIDInput() {
		return pot.get();
	}

	protected void usePIDOutput(double output) {
		intakePositioner.set(output);
	}
	
	public double getPot() {
		return pot.get();
	}
	
	public void setIntakeBooleanDown() {
		intakeIsDown = true;
	}
	
	public void setIntakeBooleanUp() {
		intakeIsDown = false;
	}
	
	public boolean isDown() {
		return intakeIsDown;
	}
	

    
}

