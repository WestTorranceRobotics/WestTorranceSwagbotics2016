package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class FullShot extends CommandGroup {
    
    public  FullShot() {
        addSequential(new CatapultLaunch());
        addSequential(new Wait(0.4));
        addSequential(new CatapultLoad());
    }
}
