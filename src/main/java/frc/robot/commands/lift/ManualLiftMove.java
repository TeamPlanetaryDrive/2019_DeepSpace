/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ManualLiftMove extends Command {
  public double setPoint;

  public ManualLiftMove() {
    requires(Robot.Elevator);
    setPoint = 0;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // System.out.println(RobotMap.liftEncoder.getDistance() + "Encoder");
    if (RobotMap.leftJoystick.getTrigger()) {
      Robot.Elevator.enable();
      System.out.println("leftTrigger reached");
      setPoint += ((-RobotMap.rightJoystick.getZ())-1) / 200;
      Robot.Elevator.setSetpoint(setPoint);
      System.out.println(setPoint);
    }
    if (RobotMap.rightJoystick.getTrigger()) {
      Robot.Elevator.enable();
      System.out.println("RightTrigger reached");
      setPoint += ((RobotMap.rightJoystick.getZ() + 1)) / 200;
      Robot.Elevator.setSetpoint(setPoint);
      System.out.println(setPoint);
     
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !RobotMap.leftJoystick.getTrigger() || !RobotMap.rightJoystick.getTrigger();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
