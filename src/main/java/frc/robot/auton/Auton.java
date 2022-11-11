package frc.robot.auton;

import frc.pathplanner.PathPlannerFollower;
import frc.robot.RobotContainer;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseAutonSequence;

public enum Auton {
    BASIC_AUTON(new BasicAuton(
            BasicAutonPhase.NEUTRAL,BasicAutonPhase.DRIVE,
            RobotContainer.swerveDrive)),
    BASIC_AUTON2(new BasicAuton2(
            BasicAutonPhase.NEUTRAL,BasicAutonPhase.DRIVE,
            RobotContainer.swerveDrive));

    BaseAutonSequence<? extends ISequencePhase> auton;

    Auton(BaseAutonSequence<? extends ISequencePhase> auton) {
        this.auton = auton;
    }

    public void setPathPlannerFollowers(PathPlannerFollower... pathPlannerFollowers) {
        this.auton.setPathPlannerFollowers(pathPlannerFollowers);
    }

    public BaseAutonSequence<? extends ISequencePhase> getAuton() {
        return auton;
    }
}