/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot;

import frc.robot.commands.lift.*;
import frc.robot.commands.CountEncoderValue;
import frc.robot.commands.ResetLiftEncoder;
import frc.robot.commands.grip.*;

/**
 * This class is what binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public OI() {
    // Gripper controls
    // button 2 left is open, button 2 right is close
    RobotMap.button2_left.whenPressed(new OpenGripper());
    RobotMap.button2_right.whenPressed(new CloseGripper());
    RobotMap.button6_left.whenPressed(new StopGripper());

    // button2_left.whenPressed(new ExtendGripPiston());

    // manual lift controls
    RobotMap.button8_right.whenPressed(new PartialPneumaticClose());
    RobotMap.button9_left.whenPressed(new ManualLift());
    RobotMap.button10_left.whenPressed(new CountEncoderValue());
    
    // automated lift controls
    RobotMap.button8_left.whenPressed(new LiftToLevel(0));
    RobotMap.button4_left.whenPressed(new LiftToLevel(1));
    RobotMap.button3_left.whenPressed(new LiftToLevel(2));
    RobotMap.button5_left.whenPressed(new LiftToLevel(3));
    RobotMap.button4_right.whenPressed(new LiftToLevel(4));
    RobotMap.button3_right.whenPressed(new LiftToLevel(5));
    RobotMap.button5_right.whenPressed(new LiftToLevel(6));

    RobotMap.button11_left.whenPressed(new ResetLiftEncoder());

    // button4_left.whenPressed(new LiftLevelOne());
    // button3_left.whenPressed(new LiftLevelTwo());
    // button5_left.whenPressed(new LiftLevelThree());
    // button4_right.whenPressed(new LiftLevelFour());
    // button3_right.whenPressed(new LiftLevelFive());
    // button5_right.whenPressed(new LiftLevelSix());

  }
}
