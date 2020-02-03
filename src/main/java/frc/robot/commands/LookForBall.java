/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.commands.grip.*;

public class LookForBall extends Command {
  NetworkTableInstance inst;
  NetworkTable table;
  NetworkTableEntry contours;
  public LookForBall() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.Cameras);
    requires(Robot.Grip);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("Timmy");
    contours = table.getEntry("/freeTimmy/area");
    inst.startClientTeam(2856);  
    inst.startDSClient();  
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double[] yup = {0};
    if(contours.getDoubleArray(yup).length>0 && contours.getDoubleArray(yup)[0]>1){
      new CloseGripper();
    }else{
      new OpenGripper();
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
