package org.usfirst.frc5124.WestTorranceSwagbotics2016;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.commands.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;

public class OI {
    
    public Joystick driver;			// declarations of buttons and joysticks
    public Joystick operator;
    public Joystick autoSelector;
    public JoystickButton launch;
    public JoystickButton load;
    public JoystickButton takeIn;
    public JoystickButton exhaust;
    public JoystickButton lineUp;
    public JoystickButton stutter;
    public JoystickButton grab;
    public JoystickButton release;
    public JoystickButton intakeUp;
    public JoystickButton intakeDown;
    public JoystickButton intakeDisable;
    public JoystickButton afterLift;
    public JoystickButton afterLift2;
    public JoystickButton SecondaryFire;
    public JoystickButton intakeAllUp;
    public JoystickButton intakeGround;
    public JoystickButton upShoot;
    public JoystickButton intakeUpFast;
    
    public OI() {
        
    	driver = new Joystick(0);				// Initialize joysticks to their ports			
        operator = new Joystick(1);
        autoSelector = new Joystick(2);
        
        exhaust = new JoystickButton(operator, 5);		// initialize buttons to their joysticks, buttons
        exhaust.whileHeld(new IntakeExhaust());			// tell the button what to do
        takeIn = new JoystickButton(operator, 4);		// do that over and over again
        takeIn.whileHeld(new IntakeIntake());
        //load = new JoystickButton(operator, 2);
        //load.whenPressed(new CatapultLoad());
        //launch = new JoystickButton(driver, 7);
        //launch.whenPressed(new FullShot());
        //lineUp = new JoystickButton(driver, 8);
        //lineUp.whileHeld(new LineUp());   
        //stutter = new JoystickButton(driver, 1);
        //stutter.whenPressed(new StutterShot());     
        intakeUp = new JoystickButton(operator, 9);
        intakeUp.whenPressed(new IntakeRetract());
        intakeDown = new JoystickButton(operator, 8);
        intakeDown.whenPressed(new IntakeDown());
        intakeGround = new JoystickButton(operator, 1);
        intakeGround.whenPressed(new IntakeGround());
        intakeDisable = new JoystickButton(operator, 7);
        intakeDisable.whileHeld(new IntakeDisablePID());
        afterLift = new JoystickButton(operator, 1);
        afterLift.whenReleased(new IntakeRetract());
        afterLift2 = new JoystickButton(operator, 7);
        afterLift2.whenReleased(new IntakeRetract());
        upShoot = new JoystickButton(operator, 6);
        upShoot.whenPressed(new IntakeFullUp());
        
        /*
        SmartDashboard.putData(new CatapultLaunch());				// Smartdashboard stuff, mostly for testing
        SmartDashboard.putData("Right", new TurnPID(90));			// the testing stuff should be gone for comps :c
        SmartDashboard.putData("Left", new TurnPID(-90));
        SmartDashboard.putData("Calibrate", new Calibrategyro());
        SmartDashboard.putData("Cross", new CrossPorteCullis());
        SmartDashboard.putBoolean("On Target", Robot.driveTrain.onTarget());
        */
        
    }

    public Joystick getDriver() {
        return driver;
    }

    public Joystick getOperator() {
        return operator;
    }
    
    public Joystick getAutoSelector() {
    	return autoSelector;
    }
    
    public double getSlider() {
    	return (operator.getRawAxis(2)/2);
    }
    
    public double getLeft() {			// get left y axis for tank with a deadzone
    	
    	if(Math.abs(driver.getRawAxis(1)) > 0.15  && Math.abs(driver.getRawAxis(1)) <= 1) {
    		return driver.getRawAxis(1);
    	} else {
    		return 0;
    	}
    }
    	
    public double getRight() {			// get right y axis for tank with a deadzone
        	
       	if(Math.abs(driver.getRawAxis(3)) > 0.15 && Math.abs(driver.getRawAxis(3)) <= 1) {
       		return driver.getRawAxis(3);
       	} else {
       		return 0;
        }	
    }
}

