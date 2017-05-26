package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CrossPorteCullis extends CommandGroup {
    
    public  CrossPorteCullis() {
    	//addSequential(new Calibrategyro());
        addSequential(new IntakeGround());
        addSequential(new Wait(2.5));
        addSequential(new IntakePermDisable());
        addSequential(new DriveWithEncoder(250, 0.52, 2.5));
        addSequential(new IntakeFullUp());
        addSequential(new Wait(.8));
        addSequential(new DriveWithEncoder(250, 0.63, 1.75));
        addSequential(new PermissionToFire());
        addSequential(new TurnToDefense());
    	addSequential(new Wait(0.5));
    	addSequential(new LineUpAuto());
    	addSequential(new IntakeFullUp());
    	addSequential(new Wait(1));
    	addSequential(new CheckFire());
    	addSequential(new FullShot());
    	addSequential(new WaitForLineUp());
    }
}
