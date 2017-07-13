// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc3620.FRC36202017SummerVision.commands;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

import org.usfirst.frc3620.FRC36202017SummerVision.Robot;
import org.usfirst.frc3620.FRC36202017SummerVision.RobotMap;

/**
 *
 */
public class BallAimCommand extends Command {
	NetworkTable visionTable;
	Double[] Default_Value = new Double[0];
	double distancePower = 0.3;
	
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public BallAimCommand() {
    	requires(Robot.circlewithgear);

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    	visionTable = NetworkTable.getTable("GRIP/vision");
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Double Center = (640.0/2.0);
    	Double Deadband = (Center/20);
    	
    	Double[] ballCenters = visionTable.getNumberArray("x", Default_Value);
    	Double motorPower = 0.0;
    	Double ballCenter = 0.0;
    	if (ballCenters.length == 0) {
    		ballCenter = Center;
    	}
    	else {
    		ballCenter = ballCenters[0];
    	}
    	System.out.println(ballCenter);
    	
    	if (ballCenter > (Center+Deadband)) {
    		double Deadspace = (Center+Deadband);
    		double distance = (ballCenter-Deadspace);
    		motorPower = distancePower;
    	}
    	else if (ballCenter < (Center-Deadband)) {
    		double Deadspace = (Center-Deadband);
    		motorPower = -distancePower;
    	} 
    	RobotMap.circlewithgearSpeedController1.set(motorPower);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
