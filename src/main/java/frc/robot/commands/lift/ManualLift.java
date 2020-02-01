/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.lift;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ManualLift extends Command {
  /**
   * Creates a new ManualLift.
   */
  public ManualLift() {
    // Use addRequirements() here to declare subsystem dependencies.
    requires(Robot.Elevator);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.Elevator.liftMove(RobotMap.leftJoystick.getZ());
  }

  // Called once the command ends or is interrupted.
  // @Override
  public void end(boolean interrupted) {
    Robot.Elevator.liftMove(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true; //Should be different
  }

}