package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class StutterShot extends CommandGroup {
    
    public  StutterShot() {
       addSequential(new CatapultLaunch());
       addSequential(new Wait(0.16));
       addSequential(new CatapultLoad());
    }
}
