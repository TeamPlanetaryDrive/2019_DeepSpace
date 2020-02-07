/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import frc.robot.commands.lift.*;
import frc.robot.commands.grip.*;
import frc.robot.commands.*;


/**
 * This class is what binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public OI() {
    // gripper controls (button 2 left is open, button 2 right is close)
    RobotMap.button2_left.whenPressed(new OpenGripper());
    RobotMap.button2_right.whenPressed(new CloseGripper());
    RobotMap.button6_left.whenPressed(new StopGripper());
    // RobotMap.button10_left.whenPressed(new CountEncoderValue());
    
    // teleop lift controls
    RobotMap.button8_left.whileHeld(new ManualLift());

    // adjust aim
    RobotMap.button3_left.whenPressed(new TurnToGoal());
    RobotMap.button3_right.whenPressed(new TurnToGoal());
    
    // automated lift controls
    // RobotMap.button8_left.whenPressed(new LiftToLevel(0));
    // RobotMap.button4_left.whenPressed(new LiftToLevel(1));
    // RobotMap.button3_left.whenPressed(new LiftToLevel(2));
    // RobotMap.button5_left.whenPressed(new LiftToLevel(3));
    // RobotMap.button4_right.whenPressed(new LiftToLevel(4));
    // RobotMap.button3_right.whenPressed(new LiftToLevel(5));
    // RobotMap.button5_right.whenPressed(new LiftToLevel(6));
    // RobotMap.button11_left.whenPressed(new ResetLiftEncoder());

  }

}
