package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DoNothingAuto extends CommandGroup {
    
    public  DoNothingAuto() {
       addSequential(new DoNothing());
       addSequential(new Calibrategyro());
    }
}
