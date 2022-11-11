package frc.robot.auton;

import frc.robot.subsystems.SwerveDriveState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseAutonSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.general.swervedrive.BaseDriveSubsystem;

enum BasicAutonPhase implements ISequencePhase {
    NEUTRAL,
    DRIVE(0, SwerveDriveState.DRIVE_AUTON);

    SequencePhase phase;
    
    BasicAutonPhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }
    
    BasicAutonPhase(int pathIndex, ISubsystemState... states) {
        phase = new SequencePhase(pathIndex, states);
    }
    
    @Override
    public SequencePhase getPhase() {
        return phase;
    }
}

public class BasicAuton extends BaseAutonSequence<BasicAutonPhase>{

    public BasicAuton(BasicAutonPhase neutralState, BasicAutonPhase startState, BaseDriveSubsystem driveSubsystem) {
        super(neutralState, startState, driveSubsystem);
    }

    @Override
    public void process() {
        switch(getPhase()){
            case DRIVE:
                setPathPlannerFollowerAtStartOfState(true);
                if (this.getPlannerFollower().isFinished()) {
                    setNextPhase(BasicAutonPhase.NEUTRAL);
                }
                break;
            case NEUTRAL:
                break;
        }
    
        updatePhase();
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }

}