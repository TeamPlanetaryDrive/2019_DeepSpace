/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.TurnToGoal;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class auto2 extends CommandGroup {
  /**
   * Creates a new auto2.
   */
  public auto2() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    addSequential(new breakStartLine());
    addSequential(new TurnToGoal());
    addSequential(new moveToGoal());
    addSequential(new aimShooter());
    addSequential(new shootBalls());

  }
}
