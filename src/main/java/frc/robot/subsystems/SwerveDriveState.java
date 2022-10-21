package frc.robot.subsystems;

import java.util.function.Consumer;

import frc.robot.RobotContainer;
import frc.statebasedcontroller.subsystem.fundamental.state.ISubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.state.SubsystemState;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

public enum SwerveDriveState implements ISubsystemState{
    DRIVE((s) -> {s.drive();}), 
    NEUTRAL((s) -> {s.neutral();});
    SubsystemState<SwerveDrive> state;

    SwerveDriveState(Consumer<SwerveDrive> processFunction) {
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
        return RobotContainer.swerveDrive;
    }
    
}
