/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

//import java.awt.geom.Ellipse2D.Double;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class PIDMotor extends PIDSubsystem{
	
	//PIDController ...
	public SpeedController motor;
	public PIDSource src;
	public double multiplier;
	public double points;
	public double tolerance;

	/**
	 * Init needs to be called
	 */
	public PIDMotor(double Kp, double Ki, double Kd,double kf){
		super("PIDMotor", Kp, Ki, Kd, kf);

		this.setOutputRange(-0.20, 0.50);
		this.setAbsoluteTolerance(0.05);
		this.getPIDController().setContinuous(true);
		multiplier = 1;
	}

	public void init(SpeedController sc, boolean inv, PIDSource en){
		motor = sc;
		motor.setInverted(inv);
		src = en;
	}
	public void setSetpoint(double point){
		this.setSetpoint(point);
	}

	public void setAbsoluteTolerance(double percent){
		this.setPercentTolerance(percent);
	}

	protected double returnPIDInput() {
		return src.pidGet();
	}

	protected void usePIDOutput(double output) {
		motor.pidWrite(output*multiplier);
		//motor.set(output * multiplier);
	}
	//Function is only here because PIDSubsystem requires this method
	@Deprecated
	protected void initDefaultCommand() {
		//this.setSetpoint(10);	
	}
}
