/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Gripper;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Motion;
import frc.robot.subsystems.MoveRefGen;
import frc.robot.subsystems.PIDMotor;
import frc.robot.subsystems.Vision;
import frc.robot.OI;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static Climb Hab_Climb = new Climb(); // hab platform cimbing 
    public static DriveTrain Drive = new DriveTrain();//could be redundent , if we delete drivetrain get rid of this
    public static Gripper Grip = new Gripper(); // testing pnuematics, change name later
    public static Lift Elevator = new Lift(); // elevator for gripper
    public static Motion D_Motion = new Motion(); // D_Motion as in drive motion
    public static Vision Cameras = new Vision(); //used for the vision class as needed
    public static MoveRefGen MoveRefGen = new MoveRefGen();// used to change movestates
    public static PIDMotor PID = new PIDMotor(2 , .01, 1 , 0);
    public static OI m_oi;


  Command m_autonomousCommand;
    SendableChooser<Command> m_chooser = new SendableChooser<>();
    SendableChooser<Command> Hab_Climb_Send = new SendableChooser<>();
    SendableChooser<Command> Drive_Send = new SendableChooser<>();
    SendableChooser<Command> PneumaticsTest_Send = new SendableChooser<>();
    SendableChooser<Command> Elevator_Send = new SendableChooser<>();
    SendableChooser<Command> d_Motion_Send = new SendableChooser<>();
    SendableChooser<Command> Cameras_Send = new SendableChooser<>();


 //LoopVision camera;
 //LoopTele tele;
 //LoopAuto auto;
  
 //Robot Mechanisms
  public DriveTrain driveTrain;
  public Lift lift;
  public Gripper gripper;
  public Climb climb;
  public Gyro gyro;
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  
  public void robotInit() {
    m_oi = new OI();

    //Possibly redundant decleration
    //OI.leftJoystick=new Joystick(0);

    // chooser.addOption("My Auto", new MyAutoCommand());
    SmartDashboard.putData("Auto mode", m_chooser);
    //gripper.initDefaultCommand();    
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
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
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }
}
