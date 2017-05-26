package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LowBarDestroy extends CommandGroup {
    
    public  LowBarDestroy() {
    	addSequential(new DriveWithEncoder(-250, 0.5, 2));
    	addSequential(new IntakeDown());
    	addSequential(new Wait(1.5));
    	addSequential(new DriveWithEncoder(-250, 0.65, 1.65));
    	addSequential(new Wait(1));
    	addSequential(new DriveWithEncoder(250, 0.65, 3.5));
    	addSequential(new Wait(1));
    	addSequential(new DriveWithEncoder(-250, 0.65, 3.5));
    	
    }
}
