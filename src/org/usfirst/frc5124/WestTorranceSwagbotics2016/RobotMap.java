package org.usfirst.frc5124.WestTorranceSwagbotics2016;
// AYY LMAO HEY ZACH it's burripshito here 
// how are you doing rn friend 
// I'm incredibly tired right now 
// it's 2/13/16 7:48pm
// I hope you know you are a beloved cinnamon roll too innocent and pure for this world 
// Alrighty m8y I am done here
// godbye
// #ripshita 
// #watergame

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


public class RobotMap {
    public static VictorSP driveTrainLeft1;
    public static VictorSP driveTrainLeft2;
    public static VictorSP driveTrainRight1;
    public static VictorSP driveTrainRight2;
    public static RobotDrive driveTrainRobotDrive;
    public static Compressor driveTrainCompressor;
    public static DoubleSolenoid catapultCatapultSolenoid1;
    public static DoubleSolenoid catapultCatapultSolenoid2;
    public static Spark intakeIntakeMotor;
    public static Spark intakeIntakePositioner;
    public static Encoder encoder;
	public static ADXRS450_Gyro gyro;
	public static AnalogPotentiometer pot;
	public static Compressor drivetrainCompressor;


    public static void init() {
    	
        driveTrainLeft1 = new VictorSP(0);
        //LiveWindow.addActuator("Drive Train", "Left 1", driveTrainLeft1);
        
        driveTrainLeft2 = new VictorSP(1);
        //LiveWindow.addActuator("Drive Train", "Left 2", driveTrainLeft2);
        
        driveTrainRight1 = new VictorSP(2);
        //LiveWindow.addActuator("Drive Train", "Right 1", driveTrainRight1);
        
        driveTrainRight2 = new VictorSP(3);
        //LiveWindow.addActuator("Drive Train", "Right 2", driveTrainRight2);
        
        driveTrainRobotDrive = new RobotDrive(driveTrainLeft1, driveTrainLeft2,
              driveTrainRight1, driveTrainRight2);
        
        driveTrainRobotDrive.setSafetyEnabled(true);
        driveTrainRobotDrive.setExpiration(0.1);
        driveTrainRobotDrive.setSensitivity(0.2);
        driveTrainRobotDrive.setMaxOutput(1.0);
        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontLeft, true);
        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearLeft, true);
        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kFrontRight, true);
        driveTrainRobotDrive.setInvertedMotor(RobotDrive.MotorType.kRearRight, true);
        
        driveTrainCompressor = new Compressor(0);
        driveTrainCompressor.setClosedLoopControl(false);
        
        catapultCatapultSolenoid1 = new DoubleSolenoid(0, 0, 1);
        LiveWindow.addActuator("Catapult", "Catapult Solenoid 1", catapultCatapultSolenoid1);
        
        catapultCatapultSolenoid2 = new DoubleSolenoid(0, 2, 3);
        LiveWindow.addActuator("Catapult", "Catapult Solenoid 2", catapultCatapultSolenoid2);
        
        intakeIntakeMotor = new Spark(4);
        LiveWindow.addActuator("Intake", "Intake Motor", intakeIntakeMotor);
        
        intakeIntakePositioner = new Spark(5);
        intakeIntakePositioner.setInverted(false);
        LiveWindow.addActuator("Intake", "IntakePositioner", intakeIntakePositioner);
        
        encoder = new Encoder(3, 4, false, Encoder.EncodingType.k4X);
        encoder.setReverseDirection(true);
        encoder.setPIDSourceType(PIDSourceType.kDisplacement);
       
        pot = new AnalogPotentiometer(1, 5);
        LiveWindow.addSensor("Intake", "Pot", pot);
        
        drivetrainCompressor = new Compressor(0);
    	drivetrainCompressor.setClosedLoopControl(true);
        
        gyro = new ADXRS450_Gyro();
        LiveWindow.addSensor("Gyro", "Gyro", gyro);
        
        
    }
    
   
    
}
