package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class breakStartLine extends Command {
  public breakStartLine() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.Drive);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.Drive.drive(-0.25,-0.25);
    //System.out.println("");
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    return true;
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