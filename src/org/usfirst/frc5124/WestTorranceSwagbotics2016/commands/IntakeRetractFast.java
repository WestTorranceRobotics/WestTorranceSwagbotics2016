package org.usfirst.frc5124.WestTorranceSwagbotics2016.commands;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class IntakeRetractFast extends Command {

    public IntakeRetractFast() {
        requires(Robot.intake);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.intake.getPIDController().setOutputRange(-0.4, 0.6);
    	Robot.intake.enable();
    	Robot.intake.setSetpoint(4.8);
    	Robot.intake.setIntakeBooleanUp();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.intake.getPIDController().setOutputRange(-0.4, 0.55);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.intake.getPIDController().setOutputRange(-0.4, 0.55);
    }
}
