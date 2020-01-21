/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import frc.robot.RobotMap;

//use to move the grippers up and down on the elevator
public class Lift extends PIDSubsystem {

  public Lift() {
    super("Lift", 0.25, 0.01, 0);
    //super("Lift", 5.75, 0.000001, 0);

    // setOutputRange(-0.5, 0.75);
    // setAbsoluteTolerance(1);
    // getPIDController().setContinuous(false);
    // RobotMap.liftEncoder.setPIDSourceType(PIDSourceType.kDisplacement);
    // RobotMap.liftEncoder.setReverseDirection(false);
    // setInputRange(0, 30.85);
    // setSetpoint(0);
  }

  public void resetPosition(){
    RobotMap.liftEncoder.reset();
  }

  // public double inchesToSetpoint(double inches) {
  //   setpoint = inches;
  //   return setpoint;
  // }

  public void initDefaultCommand() {
    //setDefaultCommand(new PIDLift());
    //setDefaultCommand(new ManualLift());
  }

  // inherited methods
  protected double returnPIDInput() {
    return 23423; 
  }

  protected void usePIDOutput(double output) {
    liftMove(output);
  }

  public void liftMove(double speed) {
    // if( ((getPosition() < 0) && (speed < 0)) || ((getPosition() > 33.71) && (speed > 0)) )
    //   RobotMap.lift.set(0);
    // else
    RobotMap.lift.set(speed);
  }

}
