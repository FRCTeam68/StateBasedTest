package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public enum IntakeState implements ISubsystemState{
    NEUTRAL((s) -> {s.neutral();}),
    INTAKE((s) -> {
        if(RobotContainer.intake.getIndexBeamBreak() && RobotContainer.intake.getIntakeBeamBreak()){
            RobotContainer.intake.setSpeedOfIndexAndIntake(0, 0, 0);
        }
        else if(RobotContainer.intake.getIndexBeamBreak()){
            RobotContainer.intake.setSpeedOfIndexAndIntake(0, .25, .8);
        } else{
            RobotContainer.intake.setSpeedOfIndexAndIntake(.15, .25, .8);
        }
    });

    SubsystemState<Intake> state;

    IntakeState(Consumer<Intake> processFunction) {
        this.state = new SubsystemState<>(this, processFunction);
    }

    @Override
    public SubsystemState getState() {
        // TODO Auto-generated method stub
        return state;
    }

    @Override
    public BaseSubsystem getSubsystem() {
        // TODO Auto-generated method stub
        return RobotContainer.intake;
    }
}