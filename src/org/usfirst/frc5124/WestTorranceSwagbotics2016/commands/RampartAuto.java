package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class RampartAuto extends CommandGroup {
    
    public  RampartAuto() {
    	addSequential(new IntakeRetract());
    	addSequential(new DriveWithEncoder(100, 0.75, 0.7));
    	addSequential(new DriveWithEncoder(400, 0.85, 1.7));
    	addSequential(new DriveWithEncoder(200, .75, 0.2));
    	addSequential(new PermissionToFire());
    	addSequential(new Wait(0.5));
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
