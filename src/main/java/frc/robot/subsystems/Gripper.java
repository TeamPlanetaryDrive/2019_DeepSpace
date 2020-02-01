/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;

// use double solenoids
//use compressor

//Use this for opening and closing the grippers onto the plate and the ball
public class Gripper extends Subsystem {
  DoubleSolenoid testSolenoid = new DoubleSolenoid(RobotMap.GRIPPER_CHANNEL_A, RobotMap.GRIPPER_CHANNEL_B);
  private boolean state;

  public void initDefaultCommand() {
    // todo: use new system
    // setDefaultCommand(new MoveGripPiston(RobotMap.GRIPPER_RETRACT));
    state = true;
  }

  public void setState(boolean newState) {
    state = newState;
  }

  public void update() {
    // if (state == RobotMap.GRIPPER_EXTEND && RobotMap.pneumaticsStart) {
    //   pullPiston();
    // } else {
    //   pushPiston();
    // }
    // // DoubleSolenoid.Value.kReverse.
  }

  public void openGripper() {
    testSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  public void closeGripper() {
    testSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  public void pistonOff() {
    testSolenoid.set(DoubleSolenoid.Value.kOff);
  }
  
  public String getMeSomeSolenoid(){
    String forTimmy = "idk";
    switch(testSolenoid.get()){
      case kReverse:
        forTimmy = "back";
        break;
      case kForward:
        forTimmy = "front";
        break;
      case kOff:
        forTimmy = "off";
        break;
    }
    return forTimmy;
  }

}
