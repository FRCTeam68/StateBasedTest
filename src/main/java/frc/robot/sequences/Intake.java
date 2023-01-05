package frc.robot.sequences;

import frc.robot.RobotContainer;
import frc.robot.subsystems.ShooterState;
import frc.robot.subsystems.SwerveDriveState;
import frc.statebasedcontroller.sequence.fundamental.phase.ISequencePhase;
import frc.statebasedcontroller.sequence.fundamental.phase.SequencePhase;
import frc.statebasedcontroller.sequence.fundamental.sequence.BaseSequence;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
//TODO actually change stuff here!!!!!
enum IntakePhase implements ISequencePhase{
    NEUTRAL,
    SPEED_UP(ShooterState.SPEED_UP);
    SequencePhase phase;
    
    IntakePhase(ISubsystemState... states) {
        phase = new SequencePhase(states);
    }
    @Override
    public SequencePhase getPhase() {
       
        return phase;
    }
    
}

public class Intake extends BaseSequence<IntakePhase>{

    public Intake(IntakePhase neutralPhase, IntakePhase startPhase) {
        super(neutralPhase, startPhase);
        
    }

    @Override
    public void process() {
       
        switch (getPhase()){
            case NEUTRAL:
                if (RobotContainer.Buttons.SPEEDSHOOTER.getButton()){
                    setNextPhase(IntakePhase.SPEED_UP);
                }
                break;
            case SPEED_UP:
                if (RobotContainer.Buttons.SPEEDSHOOTER.getButton()){
                    setNextPhase(IntakePhase.NEUTRAL);
                }
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
