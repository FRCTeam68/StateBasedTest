// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import javax.net.ssl.TrustManager;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private int loopCnt = 0;
  private int loopPeriod = 0;
  private int logCounter = 0;
  private int designatedLoopPeriod = 20;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and
   * test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
    
    
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
    RobotContainer.swerveDrive.forceRelease();

  }

  @Override
  public void disabledPeriodic() {
    long prevLoopTime = 0;

    while (this.isDisabled()) {
      long currentTime = System.currentTimeMillis();

      if (currentTime - prevLoopTime >= designatedLoopPeriod) {

        loopPeriod = (int) (currentTime - prevLoopTime);
        prevLoopTime = currentTime;
        loopCnt++;

        SmartDashboard.putNumber("FR Thingy", RobotContainer.swerveDrive.getFRDegrees());
        SmartDashboard.putNumber("FL Thingy", RobotContainer.swerveDrive.getFLDegrees());
        SmartDashboard.putNumber("BR Thingy", RobotContainer.swerveDrive.getBRDegrees());
        SmartDashboard.putNumber("BL Thingy", RobotContainer.swerveDrive.getBLDegrees());
    

        RobotContainer.swerveDrive.neutral();
        RobotContainer.swerveDrive.process();
      }

      Timer.delay(0.001);

    }

  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    RobotContainer.swerveDrive.forceRelease();
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    
    long prevLoopTime = 0;

    while (this.isTeleop() && this.isEnabled()) {

      long currentTime = System.currentTimeMillis();
      
      if (currentTime - prevLoopTime >= designatedLoopPeriod) {
        

        loopPeriod = (int) (currentTime - prevLoopTime);
        prevLoopTime = currentTime;
        loopCnt++;

        RobotContainer.swerveDrive.process();

        RobotContainer.sequencer.process();

      }

      Timer.delay(0.001);
    }
  }

  @Override
  public void testInit() {
    
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
}
