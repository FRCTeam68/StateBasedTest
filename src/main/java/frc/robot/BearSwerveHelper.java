package frc.robot;

import static frc.robot.Constants.DRIVE.BACK_LEFT_MODULE_DRIVE_MOTOR;
import static frc.robot.Constants.DRIVE.BACK_LEFT_MODULE_STEER_ENCODER;
import static frc.robot.Constants.DRIVE.BACK_LEFT_MODULE_STEER_MOTOR;
import static frc.robot.Constants.DRIVE.BACK_LEFT_MODULE_STEER_OFFSET;
import static frc.robot.Constants.DRIVE.BACK_RIGHT_MODULE_DRIVE_MOTOR;
import static frc.robot.Constants.DRIVE.BACK_RIGHT_MODULE_STEER_ENCODER;
import static frc.robot.Constants.DRIVE.BACK_RIGHT_MODULE_STEER_MOTOR;
import static frc.robot.Constants.DRIVE.BACK_RIGHT_MODULE_STEER_OFFSET;
import static frc.robot.Constants.DRIVE.FRONT_LEFT_MODULE_DRIVE_MOTOR;
import static frc.robot.Constants.DRIVE.FRONT_LEFT_MODULE_STEER_ENCODER;
import static frc.robot.Constants.DRIVE.FRONT_LEFT_MODULE_STEER_MOTOR;
import static frc.robot.Constants.DRIVE.FRONT_LEFT_MODULE_STEER_OFFSET;
import static frc.robot.Constants.DRIVE.FRONT_RIGHT_MODULE_DRIVE_MOTOR;
import static frc.robot.Constants.DRIVE.FRONT_RIGHT_MODULE_STEER_ENCODER;
import static frc.robot.Constants.DRIVE.FRONT_RIGHT_MODULE_STEER_MOTOR;
import static frc.robot.Constants.DRIVE.FRONT_RIGHT_MODULE_STEER_OFFSET;

import java.util.ArrayList;
import java.util.List;

import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper;
import com.swervedrivespecialties.swervelib.Mk3SwerveModuleHelper.GearRatio;

import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants.AUTO;
import frc.robot.Constants.DRIVE;
import frc.robot.Constants.ROBOT;
import frc.statebasedcontroller.config.DriveSpeedsConfig;
import frc.statebasedcontroller.subsystem.requiredadditions.swervedrive.StateBasedSwerveDrivetrainModel;
import frc.swervelib.Gyroscope;
import frc.swervelib.GyroscopeHelper;
import frc.swervelib.Mk4SwerveModuleHelper;
import frc.swervelib.SwerveConstants;
import frc.swervelib.SwerveModule;
import frc.wpiClasses.QuadSwerveSim;

public class BearSwerveHelper {

        public static List<SwerveModule> realModules;

    public static StateBasedSwerveDrivetrainModel createBearSwerve() {
        passConstants();
        ShuffleboardTab tab = Shuffleboard.getTab("Drivetrain");

        realModules = new ArrayList<SwerveModule>();

        SwerveModule frontLeftModule = Mk4SwerveModuleHelper.createFalcon500(
                tab.getLayout("Front Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(0, 0),
                // This can either be STANDARD or FAST depending on your gear configuration
                Mk4SwerveModuleHelper.GearRatio.L2,
                // This is the ID of the drive motor
                FRONT_LEFT_MODULE_DRIVE_MOTOR,
                // This is the ID of the steer motor
                FRONT_LEFT_MODULE_STEER_MOTOR,
                // This is the ID of the steer encoder
                FRONT_LEFT_MODULE_STEER_ENCODER,
                // This is how much the steer encoder is offset from true zero (In our case,
                // zero is facing straight forward)
                FRONT_LEFT_MODULE_STEER_OFFSET, "FL");
        tab.add("FLDegree", frontLeftModule.getSteerAngle());
        SwerveModule frontRightModule = Mk4SwerveModuleHelper.createFalcon500(
                tab.getLayout("Front Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(0, 0),
                // This can either be STANDARD or FAST depending on your gear configuration
                Mk4SwerveModuleHelper.GearRatio.L2,
                // This is the ID of the drive motor
                FRONT_RIGHT_MODULE_DRIVE_MOTOR,
                // This is the ID of the steer motor
                FRONT_RIGHT_MODULE_STEER_MOTOR,
                // This is the ID of the steer encoder
                FRONT_RIGHT_MODULE_STEER_ENCODER,
                // This is how much the steer encoder is offset from true zero (In our case,
                // zero is facing straight forward)
                FRONT_RIGHT_MODULE_STEER_OFFSET, "FR");
        SwerveModule backLeftModule = Mk4SwerveModuleHelper.createFalcon500(
                tab.getLayout("Back Left Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(4, 0),
                Mk4SwerveModuleHelper.GearRatio.L2,
                BACK_LEFT_MODULE_DRIVE_MOTOR,
                BACK_LEFT_MODULE_STEER_MOTOR,
                BACK_LEFT_MODULE_STEER_ENCODER,
                BACK_LEFT_MODULE_STEER_OFFSET, "BL");
                
       SwerveModule backRightModule = Mk4SwerveModuleHelper.createFalcon500(
                tab.getLayout("Back Right Module", BuiltInLayouts.kList)
                        .withSize(2, 4)
                        .withPosition(4, 0),
                Mk4SwerveModuleHelper.GearRatio.L2,
                BACK_RIGHT_MODULE_DRIVE_MOTOR,
                BACK_RIGHT_MODULE_STEER_MOTOR,
                BACK_RIGHT_MODULE_STEER_ENCODER,
                BACK_RIGHT_MODULE_STEER_OFFSET, "BR");

        Gyroscope gyro = GyroscopeHelper.createnavXMXP();
        ArrayList<SwerveModule> modules = new ArrayList<>(QuadSwerveSim.NUM_MODULES);
        modules.add(frontLeftModule);
        modules.add(frontRightModule);
        modules.add(backLeftModule);
        modules.add(backRightModule);

        return new StateBasedSwerveDrivetrainModel(modules, gyro);
    }

    private static void passConstants() {
        SwerveConstants.MAX_FWD_REV_SPEED_MPS = DRIVE.MAX_FWD_REV_SPEED_MPS_EST;
        SwerveConstants.MAX_STRAFE_SPEED_MPS = DRIVE.MAX_FWD_REV_SPEED_MPS_EST;
        SwerveConstants.MAX_ROTATE_SPEED_RAD_PER_SEC = DRIVE.MAX_ROTATE_SPEED_RAD_PER_SEC_EST;
        SwerveConstants.MAX_VOLTAGE = ROBOT.MAX_VOLTAGE;
        SwerveConstants.DFLT_START_POSE = ROBOT.DFLT_START_POSE;

        SwerveConstants.THETACONTROLLERkP = AUTO.THETACONTROLLERkP;
        SwerveConstants.TRAJECTORYXkP = AUTO.TRAJECTORYXkP;
        SwerveConstants.TRAJECTORYYkP = AUTO.TRAJECTORYYkP;
        
        SwerveConstants.THETACONTROLLERCONSTRAINTS = AUTO.THETACONTROLLERCONSTRAINTS;

        SwerveConstants.TRACKWIDTH_METERS = DRIVE.TRACKWIDTH_METERS;
        SwerveConstants.TRACKLENGTH_METERS = DRIVE.WHEELBASE_METERS;
        SwerveConstants.MASS_kg = ROBOT.ROBOT_MASS_kg;
        SwerveConstants.MOI_KGM2 = ROBOT.ROBOT_MOI_KGM2;
        SwerveConstants.KINEMATICS = DRIVE.KINEMATICS;

        DriveSpeedsConfig.FWD_REV_FAST = DRIVE.MAX_FWD_REV_SPEED_FAST;
        DriveSpeedsConfig.STRAFE_FAST = DRIVE.MAX_STRAFE_SPEED_FAST;
        DriveSpeedsConfig.ROTATION_FAST = DRIVE.MAX_ROTATE_SPEED_FAST;
        DriveSpeedsConfig.FWD_REV_SLOW = DRIVE.MAX_FWD_REV_SPEED_SLOW;
        DriveSpeedsConfig.STRAFE_SLOW = DRIVE.MAX_STRAFE_SPEED_SLOW;
        DriveSpeedsConfig.ROTATION_SLOW = DRIVE.MAX_ROTATE_SPEED_SLOW;
    }
}