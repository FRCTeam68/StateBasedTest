// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.concurrent.Callable;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.sequences.SequenceProcessor;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.SwerveDrive;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  public static SwerveDrive swerveDrive;
  public static Shooter shooter;
  public static Intake intake;
  public static SequenceProcessor sequencer;

  public static final XboxController m_controller = new XboxController(0);

  public static XboxController getController1(){
    return m_controller;
  }

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Set up the default command for the drivetrain.
    // The controls are for field-oriented driving:
    // Left stick Y axis -> forward and backwards movement
    // Left stick X axis -> left and right movement
    // Right stick X axis -> rotation
    // m_drivetrainSubsystem.setDefaultCommand(new DefaultDriveCommand(
    //         m_drivetrainSubsystem,
    //         () -> -modifyAxis(m_controller.getLeftY()) * SwerveDrive.MAX_VELOCITY_METERS_PER_SECOND,
    //         () -> -modifyAxis(m_controller.getLeftX()) * SwerveDrive.MAX_VELOCITY_METERS_PER_SECOND,
    //         () -> -modifyAxis(m_controller.getRightX()) * SwerveDrive.MAX_ANGULAR_VELOCITY_RADIANS_PER_SECOND
    // ));

    swerveDrive = new SwerveDrive();
    shooter = new Shooter(1500);
    // Configure the button bindings
    sequencer = new SequenceProcessor();
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new InstantCommand();
  }

  private static double deadband(double value, double deadband) {
    if (Math.abs(value) > deadband) {
      if (value > 0.0) {
        return (value - deadband) / (1.0 - deadband);
      } else {
        return (value + deadband) / (1.0 - deadband);
      }
    } else {
      return 0.0;
    }
  }

  private static double modifyAxis(double value) {
    // Deadband
    value = deadband(value, 0.05);

    // Square the axis
    value = Math.copySign(value * value, value);

    return value;
  }

  public enum Buttons{
    SPEEDSHOOTER(() -> RobotContainer.getController1().getXButton());
    Callable<Boolean> callable;

    Buttons(Callable<Boolean> callable) {
        this.callable = callable;
    }

    public boolean getButton() {

        try {

            return callable.call().booleanValue();

        } catch (Exception ex) {

            return false;

        }
    }
  }

  public enum Axes {
    Drive_ForwardBackward(() -> -modifyAxis(RobotContainer.getController1().getLeftY())),
    Drive_LeftRight(() -> -modifyAxis(RobotContainer.getController1().getLeftX())),
    Drive_Rotation(() -> -modifyAxis(RobotContainer.getController1().getRightX()));

    Callable<Double> callable;

    Axes(Callable<Double> callable) {
        this.callable = callable;
    }

    public double getAxis() {

        try {

            return callable.call().doubleValue();

        } catch (Exception ex) {

            return 0.0;

        }
    }
}
}
