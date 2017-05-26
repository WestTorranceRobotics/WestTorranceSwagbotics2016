package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * 	
 */
public class CrossChevalDeFrise extends CommandGroup {
    
    public  CrossChevalDeFrise() {
    	addSequential(new DriveWithEncoder(350, 0.51, 2.25));
    	addSequential(new IntakeGround());
    	addSequential(new Wait(1.5));
    	addSequential(new DriveWithEncoder(50, 0.6, 1));
    	addParallel(new IntakeRetract());
    	addSequential(new DriveWithEncoder(150, 0.75, 1.4));
    	// this is where vision starts, kinda
    	addSequential(new PermissionToFire());	// check if drivers turned on shooting in auto
    	addSequential(new Wait(0.5));
    	addSequential(new TurnToDefense());	// turn to face the tower
    	addSequential(new Wait(0.5));
    	addSequential(new LineUpAuto());	// line up to the target
    	addSequential(new CheckFire());		// make sure the robot actually sees a target, if not, do not go on
    	addSequential(new IntakeFullUp());	//shoot
    	addSequential(new Wait(1));			//shoot
    	addSequential(new FullShot());		//shoot
    	addSequential(new WaitForLineUp());	//shoot
    }
}
