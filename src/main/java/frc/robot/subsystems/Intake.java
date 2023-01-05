// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DigitalInput;
import  frc.robot.Constants;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public class Intake extends BaseSubsystem<IntakeState> {

  private TalonFX intakeMotor;
  private TalonFX indexMotorHigh;
  private TalonFX indexMotorLow;

  private DigitalInput intakeSensor;
  private DigitalInput indexSensor;

  public static Intake intake;

  public static Intake getIntake(){
    if (intake == null){
      intake = new Intake();
    }
    return intake;
  }

  /** Creates a new Intake. */
  public Intake() {
    super(IntakeState.NEUTRAL);

    intakeMotor = new TalonFX(Constants.Intake.INTAKE_MOTOR, Constants.MANIP_CANBUS); // Intake Motor (Change value when known)
    intakeMotor.configPeakOutputForward(1);
    intakeMotor.configPeakOutputReverse(-1);

    indexMotorHigh = new TalonFX(Constants.Intake.INDEX_MOTOR_HIGH, Constants.MANIP_CANBUS); // Change value when known
    indexMotorHigh.configPeakOutputForward(1);
    indexMotorHigh.configPeakOutputReverse(-1);

    indexMotorLow = new TalonFX(Constants.Intake.INDEX_MOTOR_LOW, Constants.MANIP_CANBUS); // Change value when known
    indexMotorLow.configPeakOutputForward(1);
    indexMotorLow.configPeakOutputReverse(-1);

    intakeSensor = new DigitalInput(Constants.Intake.INTAKE_SENSOR);
    indexSensor = new DigitalInput(Constants.Intake.INDEX_SENSOR);

  }

  public boolean getIntakeBeamBreak(){
    return intakeSensor.get();
  } 

  public boolean getIndexBeamBreak(){
    return indexSensor.get();
  } 
  
  public void setIntakeSpeed(double speed){
    intakeMotor.set(ControlMode.PercentOutput, speed);
  }
  public void setIndexHighSpeed(double speed){
    indexMotorHigh.set(ControlMode.PercentOutput, speed);
  }
  public void setIndexLowSpeed(double speed){
    indexMotorLow.set(ControlMode.PercentOutput, speed);
  }

  // Have a combined function because maybe you want to change both at the same time??

  public void setSpeedOfIndexAndIntake(double indexHighSpeed, double indexLowSpeed, double intakeSpeed){
    intakeMotor.set(ControlMode.PercentOutput, -intakeSpeed);
    indexMotorLow.set(ControlMode.PercentOutput, -indexLowSpeed);
    indexMotorHigh.set(ControlMode.PercentOutput, indexHighSpeed);
  }

@Override
public void neutral() {
  indexMotorHigh.set(ControlMode.PercentOutput, 0);    
  indexMotorLow.set(ControlMode.PercentOutput, 0);   
}

@Override
public boolean abort() {
    // TODO Auto-generated method stub
    return false;
}

}
