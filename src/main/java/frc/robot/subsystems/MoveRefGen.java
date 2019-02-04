/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;
import frc.robot.RobotMap;
/**
 * Add your docs here.
 */
public class MoveRefGen extends Subsystem {
  // Put methods for controlling this subsystem
	// here. Call these from Commands.
	@Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
	}
	
 	// State machine enumeration
	 private static enum MoveState {
		Idle,
		Accel,
		SteadyState,
		Decel,
		Settle
	}

	// Parameters
	private double accelRate;
	private double maxSpeed;
	private double settleTime;

	// States
	private MoveState moveState;
	private double decelStart;
	private double direction;
	private double endDist;
	private double refDist;
	private double refSpeed;
	private double settleTimer;

	public MoveRefGen() {
		moveState = MoveState.Idle;
	}
	
	public void configure(double accelRate, double maxSpeed, double settleTime) {
		this.accelRate = accelRate;
		this.maxSpeed = maxSpeed;
		this.settleTime = settleTime;
	}

	public double getRefPosition() {
		return direction * refDist;
	}

	public double getRefVelocity() {
		return direction * refSpeed;
	}

	public boolean isActive() {
		return moveState != MoveState.Idle;
	}

	public void start(double distance) {
		double decelDist;
		
		if (distance != 0) {
			
			if (distance > 0) {
				
				direction = 1;
				endDist = distance;
			
			} else {
			
				direction = -1;
				endDist = -distance;
			}
			
			decelDist = (maxSpeed * maxSpeed) / (2 * accelRate);
			
			if ((2 * decelDist) > endDist) {
				decelDist = endDist / 2;
			}
			
			decelStart = endDist - decelDist;
			refDist = 0;
			refSpeed = 0;
			moveState = MoveState.Accel;
		}
	}

	public void stop() {
		refSpeed = 0;
		moveState = MoveState.Idle;
	}

	public void update() {
		switch (moveState) {
			case Idle:
				// Do nothing
				break;
			case Accel:
				refSpeed += accelRate * RobotMap.PERIODIC_UPDATE_PERIOD;
				if (refSpeed >= maxSpeed)
				{
					refSpeed = maxSpeed;
					moveState = MoveState.SteadyState;
				}
				refDist += refSpeed * RobotMap.PERIODIC_UPDATE_PERIOD;
				if (refDist >= decelStart)
				{
					moveState = MoveState.Decel;
				}
				break;
			case SteadyState:
				refDist += refSpeed * RobotMap.PERIODIC_UPDATE_PERIOD;
				if (refDist >= decelStart)
				{
					moveState = MoveState.Decel;
				}
				break;
			case Decel:
	            refSpeed -= accelRate * RobotMap.PERIODIC_UPDATE_PERIOD;
	            if (refSpeed <= 0)
	            {
	                refSpeed = 0;
	                refDist = endDist;
	                settleTimer = 0;
	                moveState = MoveState.Settle;
	            }
	            else
	            {
	                refDist += refSpeed * RobotMap.PERIODIC_UPDATE_PERIOD;
	                if (refDist >= endDist)
	                {
	                    refSpeed = 0;
	                    refDist = endDist;
	                    if (settleTime != 0)
	                    {
		                    settleTimer = 0;
		                    moveState = MoveState.Settle;
	                    }
	                    else
	                    {
	                    	moveState = MoveState.Idle;
	                    }
	                }
	            }
				break;
			case Settle:
	            settleTimer += RobotMap.PERIODIC_UPDATE_PERIOD;
	            if (settleTimer >= settleTime)
	            {
	                moveState = MoveState.Idle;
	            }
				break;
		}
	}
}