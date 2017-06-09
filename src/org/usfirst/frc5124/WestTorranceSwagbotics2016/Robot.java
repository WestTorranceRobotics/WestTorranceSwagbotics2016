package org.usfirst.frc5124.WestTorranceSwagbotics2016;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.commands.*;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.subsystems.*;

public class Robot extends IterativeRobot {

    Command autonomousCommand;								// command to run on auto
    public static Command defenseType;						// wanted to make modular auto, didnt have enough time to test
    public static Command defenseSlot;						// this is what remains of it
    DigitalInput onesPlaceSwitch;							// the two switches for auto selection
    DigitalInput tensPlaceSwitch;			
    public static DigitalInput shootSwitch;					// the switch for shooting in auto	
    public static boolean fireWhenReady = false;			// some booleans neamed badly for auto
    public static boolean defenseIsRight = true;			// the direction, not correct :c
    public static boolean holdOnToYourButts = false;		// idk what this even is
    String fireString = "";									// strings for displaying state of auto switches
    String autoString = "";
    String defenseDirection = "";							// for displaying witch direction defense is of tower
    boolean button1pressed = false;							// booleans for toggling
    boolean button2pressed = false;
    boolean button3pressed = false;
    CameraServer usbCamera; 								// camera, this is not needed if only using 1 camera, which i did
    NetworkTable convexReport;								//network table for transfering data for vision
    public static OI oi;									// subsystems
    public static Camera camera;
    public static EncoderDriveTrain encoderDriveTrain;
    public static PIDHandler pidHandler;
    public static DriveTrain driveTrain;
    public static Catapult catapult;
    public static Intake intake;
    public boolean onTargetLong = true;						// unsure what this is

    public void robotInit() {
    	RobotMap.init();									// init robotmap
    	
    	onesPlaceSwitch = new DigitalInput(8);				// initialize switches for auto
    	tensPlaceSwitch = new DigitalInput(7);
    	shootSwitch = new DigitalInput(9);
    
        driveTrain = new DriveTrain();						// initialize subystem objects
        catapult = new Catapult();
        intake = new Intake();
        camera = new Camera();
        encoderDriveTrain = new EncoderDriveTrain();
        pidHandler = new PIDHandler();
        // OI must be constructed after subsystems. If the OI creates Commands		// auto generated comment lol
        //(which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();
        autonomousCommand = new DoNothingAuto();  
        convexReport = NetworkTable.getTable("GRIP/convexHullReport");		// initialize the network table
    }

    public void disabledInit(){
    	fireWhenReady = false;				// set these booleans to their defaults
    	defenseIsRight = true;
    	holdOnToYourButts = false;
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        
     
       // SmartDashboard.putBoolean("shoot", shootSwitch.get());
        
        if(oi.driver.getRawButton(2) && !button2pressed) {  			// use button to toggle defense direction
        	defenseIsRight = !defenseIsRight;
        	button2pressed = true;
        } else if (!oi.driver.getRawButton(2) && button2pressed) {
        	button2pressed = false;
        }
        
        if(defenseIsRight) {
        	defenseDirection = "right";
        } else {
        	defenseDirection = "left";
        }
        
        if(oi.getAutoSelector().getRawButton(4)) {						// get the state of shooting in auto
        	fireString = "SHOOTING";
        	fireWhenReady = true;
        } else {
        	fireString = "NOT SHOOTING";
        	fireWhenReady = false;
        }
        
      /*
        if(!onesPlaceSwitch.get() && !tensPlaceSwitch.get()) {				//old auto selector that was on the robot
        	autoString = "Do Nothing - 00";									// if you want to learn more about auto selectors
        	autonomousCommand = new DoNothing();							// go to the Robot in the 2017 code
        } else if(!onesPlaceSwitch.get() && tensPlaceSwitch.get()) {
        	autoString = "Rock Wall - 10";
        	autonomousCommand = new RockWallAuto();
        } else if(onesPlaceSwitch.get() && tensPlaceSwitch.get()) {
        	autoString = "Everything English - 11";
        	autonomousCommand = new MoatAuto();
        } else if(onesPlaceSwitch.get() && !tensPlaceSwitch.get()) {
        	autoString = "Cheval - 01";
        	autonomousCommand = new CrossChevalDeFrise();
        }
      */
        SmartDashboard.putString("Auto Is:", autoString);					// new auto selector using off robot controller
        SmartDashboard.putString("AutoShoot", fireString);
        SmartDashboard.putString("Defense is: ", defenseDirection);
        if(oi.getAutoSelector().getRawButton(1)) {
        	if(oi.getAutoSelector().getRawButton(5) && !oi.getAutoSelector().getRawButton(2) && !oi.getAutoSelector().getRawButton(3)) {
        		autoString = "!UNTESTED!PORTCULLIS";
        		autonomousCommand = new CrossPorteCullis();
            } else if(!oi.getAutoSelector().getRawButton(5) && !oi.getAutoSelector().getRawButton(2) && !oi.getAutoSelector().getRawButton(3)) {
            	autoString = "Low Bar";
        		autonomousCommand = new LowBarAuto();
            } else if(oi.getAutoSelector().getRawButton(5) && oi.getAutoSelector().getRawButton(2) && !oi.getAutoSelector().getRawButton(3)) {
            	autoString = "ChevaldeFrise";
            	autonomousCommand = new CrossChevalDeFrise();
            } else if(oi.getAutoSelector().getRawButton(5) && oi.getAutoSelector().getRawButton(2) && oi.getAutoSelector().getRawButton(3)) {
            	autoString = "Rough Terrain";
        		autonomousCommand = new RoughTerrainAuto();
            } else if(!oi.getAutoSelector().getRawButton(5) && oi.getAutoSelector().getRawButton(2) && !oi.getAutoSelector().getRawButton(3)) {
            	autoString = "Rock Wall";
        		autonomousCommand = new RockWallAuto();
            } else if(!oi.getAutoSelector().getRawButton(5) && !oi.getAutoSelector().getRawButton(2) && oi.getAutoSelector().getRawButton(3)) {
            	autoString = "Moat";
        		autonomousCommand = new MoatAuto();
            } else if(!oi.getAutoSelector().getRawButton(5) && oi.getAutoSelector().getRawButton(2) && oi.getAutoSelector().getRawButton(3)) {
            	autoString = "Ramparts";
        		autonomousCommand = new RampartAuto();
            }
        } else {
        	autoString = "Do Nothing";
        	autonomousCommand = new DoNothingAuto();
        }
        
    }

    public void autonomousInit() {
    	//Robot.flashLight.off();
    	/*
    	if(!onesPlaceSwitch.get() && !tensPlaceSwitch.get()) {
    		autonomousCommand = new DoNothingAuto();
        } else if(!onesPlaceSwitch.get() && tensPlaceSwitch.get()) {
        	autonomousCommand = new GeneralDefenseAuto();
        } else if(onesPlaceSwitch.get() && tensPlaceSwitch.get()) {
        	autonomousCommand = new CrossPorteCullis();
        } else if(onesPlaceSwitch.get() && !tensPlaceSwitch.get()) {
        	autonomousCommand = new CrossChevalDeFrise();
        }*/
    	/*
    	if(oi.getAutoSelector().getRawButton(1)) {		// set auto using auto selector
        	if(oi.getAutoSelector().getRawButton(5) && !oi.getAutoSelector().getRawButton(2) && !oi.getAutoSelector().getRawButton(3)) {
        		autonomousCommand = new GeneralDefenseAuto();
            } else if(!oi.getAutoSelector().getRawButton(5) && !oi.getAutoSelector().getRawButton(2) && !oi.getAutoSelector().getRawButton(3)) {
        		autonomousCommand = new LowBarAuto();
            } else if(oi.getAutoSelector().getRawButton(5) && oi.getAutoSelector().getRawButton(2) && !oi.getAutoSelector().getRawButton(3)) {
            	autonomousCommand = new CrossChevalDeFrise();
            } else if(oi.getAutoSelector().getRawButton(5) && oi.getAutoSelector().getRawButton(2) && oi.getAutoSelector().getRawButton(3)) {
        		autonomousCommand = new RoughTerrainAuto();
            } else if(!oi.getAutoSelector().getRawButton(5) && oi.getAutoSelector().getRawButton(2) && !oi.getAutoSelector().getRawButton(3)) {
        		autonomousCommand = new RockWallAuto();
            } else if(!oi.getAutoSelector().getRawButton(5) && !oi.getAutoSelector().getRawButton(2) && oi.getAutoSelector().getRawButton(3)) {
        		autonomousCommand = new MoatAuto();
            } else if(!oi.getAutoSelector().getRawButton(5) && oi.getAutoSelector().getRawButton(2) && oi.getAutoSelector().getRawButton(3)) {
        		autonomousCommand = new RampartAuto();
            }
        } else {
        	autonomousCommand = new DoNothingAuto();
        }
    	
    	if (autonomousCommand != null) autonomousCommand.start();*/
    	
        RobotMap.driveTrainRobotDrive.setMaxOutput(0.6);		// full speed ahead
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        //SmartDashboard.putNumber("Encoder", encoderDriveTrain.getEncoder());
        SmartDashboard.putNumber("Encoder Distance", encoderDriveTrain.getEncoderDistance());
        //camera.getReportValues();		// THIS IS IMPORTANT WITHOUT THIS THE CAMERA SUBSYSTEM WITLL NOT GET VISON DATA 
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
        encoderDriveTrain.resetEncoder();
        //Robot.intake.setSetpoint(4.8);
        //Robot.intake.enable();
        //Robot.intake.disable();
        Robot.catapult.setCatapultBooleanDown();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        RobotMap.driveTrainRobotDrive.setMaxOutput(1);
        
        
        //camera.getReportValues();		// THIS IS IMPORTANT WITHOUT THIS THE CAMERA SUBSYSTEM WITLL NOT GET VISON DATA 
        
        //SmartDashboard.putNumber("Pot", intake.getPot());
    }

    public void testPeriodic() {
        LiveWindow.run();
    }
}
