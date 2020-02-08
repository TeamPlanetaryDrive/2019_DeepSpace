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

public class TurnToGoal extends Command {
  NetworkTableInstance inst;
  NetworkTable table;
  NetworkTableEntry goalPosition;
  NetworkTableEntry goalWidth;
  double[] defaultArray;
  final int IMAGEWIDTH = 320;
  double goalX;
  double goodRange;

  boolean close = false;

  public TurnToGoal() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.Cameras);
    requires(Robot.Drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    inst = NetworkTableInstance.getDefault();
    table = inst.getTable("GRIP/goalContours");
    goalPosition = table.getEntry("centerX");
    goalWidth = table.getEntry("width");
    inst.startClientTeam(2856);
    inst.startDSClient();

    defaultArray = new double[1];
    defaultArray[0] = Integer.MAX_VALUE;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (goalPosition.getDoubleArray(defaultArray).length > 0) {
      System.out.println("ran execute()");
      goalX = goalPosition.getDoubleArray(defaultArray)[0] - IMAGEWIDTH / 2;
      System.out.println("goal position: " + goalPosition.getDoubleArray(defaultArray)[0]);
      System.out.println("image width: " + IMAGEWIDTH / 2);
      System.out.println("goalX: " + goalX);
      int direction = (int) (goalX / Math.abs(goalX));
      System.out.println("direction: " + direction);
      Robot.Drive.drive(.35 * direction, (-.35) * direction);
      System.out.println("rotated");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (goalPosition.getDoubleArray(defaultArray).length == 0)
      return true;
    goodRange = goalWidth.getDoubleArray(defaultArray)[0] / 3;
    System.out.println("goodRange: " + goodRange);
    System.out.println("goalX*2: " + goalX * 2);
    System.out.println("END: " + (Math.abs(goalX) * 2 < goodRange));
    return Math.abs(goalX) * 2 < goodRange;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    System.out.println("END");
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
