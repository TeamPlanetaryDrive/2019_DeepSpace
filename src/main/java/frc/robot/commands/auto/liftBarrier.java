/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;

public class liftBarrier extends Command {
  /**
   * Creates a new liftBarrier.
   */
  public liftBarrier() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.MultiSystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.MultiSystem.barrierUp();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end() {
    return;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}