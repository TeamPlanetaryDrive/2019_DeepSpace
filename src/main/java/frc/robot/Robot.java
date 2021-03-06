/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Vision;
import frc.robot.OI;
import frc.robot.commands.auto.breakStartLine;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public static DriveTrain Drive;
  public static Gripper Grip;
  public static Lift Elevator;
  public static Vision Cameras;
  public static OI m_oi;

  //network table instance variables
  //NetworkTableEntry xEntry;
  //NetworkTableEntry yEntry;
  NetworkTableEntry gripState;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */

  public void robotInit() {
    RobotMap.init();
    Drive = new DriveTrain();
    Grip = new Gripper();
    Elevator = new Lift();
    Cameras = new Vision();
    m_oi = new OI();
    Cameras.init();
    Grip.pistonOff();
    SmartDashboard.putData("Auto mode", m_chooser);
    m_chooser.addOption("breakStartLine", new breakStartLine());

    //copied NetworkTable stuff
    NetworkTableInstance inst = NetworkTableInstance.getDefault();
    NetworkTable table = inst.getTable("Timmy");
    //xEntry = table.getEntry("X");
    //yEntry = table.getEntry("Y");
    gripState = table.getEntry("timmyGrip");

    m_chooser.addOption("breakStartLine", new breakStartLine());
    m_chooser.addOption("auto2", new auto2());
  }

  //NetworkTable variables
  double x = 0;
  double y = 0;

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode. You
   * can use it to reset any subsystem information you want to clear when the
   * robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable chooser
   * code works with the Java SmartDashboard. If you prefer the LabVIEW Dashboard,
   * remove all of the chooser code and uncomment the getString code to get the
   * auto name from the text box below the Gyro
   *
   * <p>
   * You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons to
   * the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

     String autoSelected = SmartDashboard.getString("Auto Selector", "Default");
     switch(autoSelected) {
      case "auto2": 
        // change to auto2
        m_autonomousCommand = new breakStartLine(); 
        break; 
      case "Default Auto": 
      default: 
        m_autonomousCommand = new breakStartLine(); 
        break; 
    }

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  //comment
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    SmartDashboard.putData("Encoder", m_chooser);
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */

  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    //xEntry.setDouble(x);
    //yEntry.setDouble(y);
    //x += 0.05;
    //y += 1.0;
    gripState.setString(Grip.getMeSomeSolenoid());
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
