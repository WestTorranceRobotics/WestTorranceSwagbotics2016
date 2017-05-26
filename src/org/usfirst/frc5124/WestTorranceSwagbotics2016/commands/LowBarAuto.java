package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarAuto extends CommandGroup {
    
    public  LowBarAuto() {
    	//addSequential(new Calibrategyro());
    	addSequential(new DriveWithEncoder(-250, 0.5, 2));
    	addSequential(new IntakeDown());
    	addSequential(new Wait(1.5));
    	addSequential(new DriveWithEncoder(-250, 0.65, 1.5));
    	addSequential(new PermissionToFire());
    	addSequential(new IntakeRetract());
    	addSequential(new Wait(2));
    	addSequential(new TurnPID(15));
    	addSequential(new DriveWithEncoder(-250, 0.65, 1.4));
    	addSequential(new TurnPID(27));
    	addSequential(new Wait(.25));
    	//addSequential(new DriveWithEncoder(-50, 0.7, 1.8));
    	addSequential(new IntakeFullUp());
    	addSequential(new Wait(1));
    	addSequential(new LineUpAuto());
    	addSequential(new FullShot()); 
    	addSequential(new WaitForLineUp());
    	//line up then shoot
    }
}
