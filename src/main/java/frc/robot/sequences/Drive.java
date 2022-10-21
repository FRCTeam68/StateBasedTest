package frc.robot.sequences;

import frc.robot.subsystems.SwerveDriveState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;

enum DrivePhase implements ISequencePhase{
    NEUTRAL,
    DRIVE(SwerveDriveState.DRIVE);

    SequencePhase phase;
    
    DrivePhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }
    @Override
    public SequencePhase getPhase() {
       
        return phase;
    }
    
}

public class Drive extends BaseSequence<DrivePhase>{

    public Drive(DrivePhase neutralPhase, DrivePhase startPhase) {
        super(neutralPhase, startPhase);
        
    }

    @Override
    public void process() {
        switch (getPhase()){
            case NEUTRAL:
                break;
            case DRIVE:
                break;
            default:
                break;
        }
        updatePhase();
    }

    @Override
    public boolean abort() {
        return false;
    }

}
