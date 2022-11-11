package frc.robot.subsystems;

import java.util.function.Function;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import frc.robot.Constants;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public class Shooter extends BaseSubsystem<ShooterState> {

private TalonFX shooterLeft;
  private TalonFX shooterRight;

  public boolean toggled = false;
  private double gP = 0;
  private double gI = 0;
  private double gD = 0;
  private double gF = 0; //Feed Forward
  private double gRef = 0; //Setpoint
  private double shootSpeed = 1;

  private double numerator;
 private double denominator;
 private double frac;
 private double circball;
 private double circwheel;
 private double Vi;
 private final double GRAVITY = -32;
 private double xDisplacement = 0;
 private final double LIMELIGHT_HEIGHT_OFF_GROUND = 41.375; //measure, in inches
 private double rpsball;
 private double rps_ratio;
 private double rpsflywheel;
 private static double rpm = 0; 
 private double kP, kI, kD, kF;
 public double targetRPM = 0;

  public Shooter(double targetRPM) {
    super(ShooterState.NEUTRAL);
    kP = 0.0465;
    kI = 0.0005;
    kD = 0.0;
    kF = 0.060;
    //TalonFX Initialization
    shooterLeft = new TalonFX(Constants.SHOOTER.LEFT_SHOOTER_MOTOR, Constants.MANIP_CANBUS); 
    shooterRight = new TalonFX(Constants.SHOOTER.RIGHT_SHOOTER_MOTOR, Constants.MANIP_CANBUS); 
    //shooterLeft.configFactoryDefault();
    shooterRight.configFactoryDefault();
    //shooterLeft.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    shooterRight.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 0);
    
    shooterLeft.follow(shooterRight);
    shooterLeft.setInverted(true);
    

    shooterRight.set(ControlMode.Velocity,0);
    shooterRight.config_kP(0, kP);
    shooterRight.config_kI(0, kI);
    shooterRight.config_kD(0, kD);
    shooterRight.config_kF(0, kF);

    shooterLeft.setNeutralMode(NeutralMode.Coast);

    shooterLeft.setSensorPhase(true);

    shooterRight.setNeutralMode(NeutralMode.Coast);
    
    shooterRight.setSensorPhase(true);

    this.targetRPM = targetRPM;
    //feedForward = new SimpleMotorFeedforward(-5.0424, 0.0002596, 0.0030056);
  }
  public void zeroEncoders(){
    shooterLeft.set(ControlMode.Position, 0); //Maybe works?
    shooterLeft.setSelectedSensorPosition(0); // Test at some point
    shooterRight.set(ControlMode.Position, 0); //Maybe works?
    shooterRight.setSelectedSensorPosition(0); // Test at some point
  }

  public void setSpeed(double speed){
    // We try something new here because ◢▅◣ → ☻
    // Probably will need to find new values
    // shooterRight.set(ControlMode.PercentOutput, feedForward.calculate(speed));

    shooterRight.set(ControlMode.Velocity, speed);
    
  }

  public void setPercentOutput(double speed){
    
    shooterRight.set(ControlMode.PercentOutput, speed);
    
  }

//   public double m_calculateRPM(){

    
//     xDisplacement = Robot.vision.calcDistance();
  
//     numerator = GRAVITY * xDisplacement * xDisplacement;
//     denominator = 2 * (LIMELIGHT_HEIGHT_OFF_GROUND - (xDisplacement * Math.tan(Constants.THETA))) * Math.pow(Math.cos(Constants.THETA), 2);
//     frac = numerator / denominator;
//     Vi = Math.sqrt(frac);

//     circball = (2 * Math.PI * Constants.COMPRESSED_RADIUS) / 12.0; //ft
//     circwheel = (2 * Math.PI * Constants.FLYWHEEL_RADIUS) /12.0; //ft

//     rpsball = Vi / circball; //rotations per second

//     rps_ratio = (circball / circwheel); //ratio of ball rpm to wheel rpm

//     rpsflywheel = rpsball * rps_ratio / Constants.SLIPPERINESS; //rotations per second

//     rpm = 60 * rpsflywheel;
    
//     return (rpm*0.64);
//   }

  public double getCurrentRPM(){
    double vel = shooterRight.getSensorCollection().getIntegratedSensorVelocity();
    double RPM = (vel*2)/2048 * 600;
    return RPM;
  }

  public void speedUp(){
      setRPM(targetRPM);
  }

  public void setRPM(double wheelRPM){
    //Sensor Velocity in ticks per 100ms / Sensor Ticks per Rev * 600 (ms to min) * 2 gear ratio to shooter
    //Motor Velocity in RPM / 600 (ms to min) * Sensor ticks per rev / pulley Ratio 36 to 18
    double motorVelocity = (wheelRPM / 600 * 2048) / 2;
    setSpeed(motorVelocity);
  }

  public double getWheelRPM(){
    return rpm;
  }

  public boolean goodToShoot(double target){
    boolean isTrue = false;
    if((target-100)<getWheelRPM()&&getWheelRPM()>(target+100)){
      isTrue = true;
    }
    else{
      isTrue = false;
    }
    return isTrue;
  }

    @Override
    public void neutral() {
        setSpeed(0.0);    
    }

    @Override
    public boolean abort() {
        setSpeed(0.0);
        return true;
    }
}