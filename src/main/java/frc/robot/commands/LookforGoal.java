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

public class LookForGoal extends Command {
  NetworkTableInstance inst;
  NetworkTable table;
  NetworkTableEntry contours;

  boolean close = false;

  public LookForGoal() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.Cameras);
    requires(Robot.DriveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("GRIP/goalContours");
    contours = table.getEntry("area");
    inst.startClientTeam(2856);  
    inst.startDSClient();  
    System.out.println("in it in it in it");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    System.out.println("ExCecuted");
    double[] yup = {.9};
    if(contours.getDoubleArray(yup).length>0){
      System.out.println(contours.getDoubleArray(yup)[0]);
    }else{
      System.out.println("no contours");
    }
    if(contours.getDoubleArray(yup).length>0 && contours.getDoubleArray(yup)[0]>1){
      // Robot.Grip.closeGripper();
      Robot.Drive.drive(-0.1, 0);
      System.out.println("close?");
    }else{
      // Robot.Grip.openGripper();
      System.out.println("open?");
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
