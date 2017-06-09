package org.usfirst.frc5124.WestTorranceSwagbotics2016.subsystems;

import org.usfirst.frc5124.WestTorranceSwagbotics2016.Robot;
import org.usfirst.frc5124.WestTorranceSwagbotics2016.RobotMap;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Catapult extends Subsystem {

    public static boolean catapultIsDown = true;
    private final DoubleSolenoid catapultSolenoid1 = RobotMap.catapultCatapultSolenoid1;
    private final DoubleSolenoid catapultSolenoid2 = RobotMap.catapultCatapultSolenoid2;

    public void initDefaultCommand() {
    }
    
    public void load() {
    	if(Robot.intake.isDown()) {
    		catapultSolenoid1.set(Value.kReverse);
    		catapultSolenoid2.set(Value.kReverse);
    	}
    }
    
    public void fire(){
    	if(Robot.intake.isDown()) {
    		catapultSolenoid1.set(Value.kForward);
        	catapultSolenoid2.set(Value.kForward);
    	}
    }
    
    public void setCatapultBooleanDown() {
    	catapultIsDown = true;
    }
    
    public void setCatapultBooleanUp() {
    	catapultIsDown = false;
    }
    
    public boolean isDown() {
    	return catapultIsDown;
    }
    
    
}

