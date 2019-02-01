/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.subsystems.MoveRevGen;

public class Decel extends Command {
  public Decel() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.MoveRefGen);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    MoveRevGen.refSpeed -= MoveRevGen.accelRate * RobotMap.PERIODIC_UPDATE_PERIOD;
    if (MoveRevGen.refSpeed <= 0)
    {
      MoveRevGen.refSpeed = 0;
      MoveRevGen.refDist = MoveRevGen.endDist;
      MoveRevGen.settleTimer = 0;
      MoveRevGen.moveState = MoveRevGen.Settle;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
