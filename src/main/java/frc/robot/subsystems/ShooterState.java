package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.RobotContainer;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public enum ShooterState implements ISubsystemState{
    NEUTRAL((s) -> {s.neutral();}),
    SPEED_UP((s) -> {s.speedUp();});

    SubsystemState<Shooter> state;

    ShooterState(Consumer<Shooter> processFunction) {
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
        return RobotContainer.shooter;
    }
}