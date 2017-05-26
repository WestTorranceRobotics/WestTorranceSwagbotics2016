package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ApproachCullis extends CommandGroup {
    
    public  ApproachCullis() {
        addSequential(new IntakePermDisable());
        addSequential(new Forwards());
    }
}
