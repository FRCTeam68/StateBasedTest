// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final int NAVX = 19;

    public final static class ROBOT {
        public static final double MAX_VOLTAGE = 10.0;
        public static final Pose2d DFLT_START_POSE = new Pose2d(0,0, new Rotation2d(0));
        public static final double ROBOT_MASS_kg = 50;
        public static final double ROBOT_MOI_KGM2 = 51; // ?
    }

    public final static class DRIVE {
        /**
         * The left-to-right distance between the drivetrain wheels
         *
         * Should be measured from center to center.
         */
        public static final double TRACKWIDTH_METERS = 20.5; // FIXME Measure and set trackwidth
        public static final double WHEELBASE_METERS = 26.5; // FIXME Measure and set wheelbase


        public static final SwerveDriveKinematics KINEMATICS = new SwerveDriveKinematics(
                new Translation2d(DRIVE.TRACKWIDTH_METERS / 2.0, DRIVE.WHEELBASE_METERS / 2.0),
                new Translation2d(DRIVE.TRACKWIDTH_METERS / 2.0, -DRIVE.WHEELBASE_METERS / 2.0),
                new Translation2d(-DRIVE.TRACKWIDTH_METERS / 2.0, DRIVE.WHEELBASE_METERS / 2.0),
                new Translation2d(-DRIVE.TRACKWIDTH_METERS / 2.0, -DRIVE.WHEELBASE_METERS / 2.0)
        );

        public static final int MAX_FWD_REV_SPEED_MPS_EST = 10;
        public static final int MAX_ROTATE_SPEED_RAD_PER_SEC_EST = 0;
        public static final int MAX_FWD_REV_SPEED_FAST = 0;
        public static final int MAX_STRAFE_SPEED_FAST = 0;
        public static final int MAX_ROTATE_SPEED_FAST = 0;
        public static final int MAX_FWD_REV_SPEED_SLOW = 0;
        public static final int MAX_STRAFE_SPEED_SLOW = 0;
        public static final int MAX_ROTATE_SPEED_SLOW = 0;

        /**
         * The front-to-back distance between the drivetrain wheels.
         *
         * Should be measured from center to center.
         */

        public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 7; // FIXME Set front left module drive motor ID
        public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 8; // FIXME Set front left module steer motor ID
        public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 18; // FIXME Set front left steer encoder ID
        public static final double FRONT_LEFT_MODULE_STEER_OFFSET = Math.toRadians(100.0); // FIXME Measure and set front left steer offset

        public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 6; // FIXME Set front right drive motor ID
        public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 5; // FIXME Set front right steer motor ID
        public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 15; // FIXME Set front right steer encoder ID
        public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(288.0); // FIXME Measure and set front right steer offset

        public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 3; // FIXME Set back left drive motor ID
        public static final int BACK_LEFT_MODULE_STEER_MOTOR = 4; // FIXME Set back left steer motor ID
        public static final int BACK_LEFT_MODULE_STEER_ENCODER = 17; // FIXME Set back left steer encoder ID
        public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(0); // FIXME Measure and set back left steer offset

        public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 1; // FIXME Set back right drive motor ID
        public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 2; // FIXME Set back right steer motor ID
        public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 16; // FIXME Set back right steer encoder ID
        public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(0); // FIXME Measure and set back right steer offset
    }

    public static final class AUTO {
        public static final int THETACONTROLLERkP = 0;
        public static final int TRAJECTORYXkP = 0;
        public static final int TRAJECTORYYkP = 0;
        public static final TrapezoidProfile.Constraints THETACONTROLLERCONSTRAINTS = new TrapezoidProfile.Constraints(0,0);
    }
}