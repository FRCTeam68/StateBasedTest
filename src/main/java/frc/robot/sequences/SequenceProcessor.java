package frc.robot.sequences;

import javax.sound.midi.Sequence;

import frc.robot.RobotContainer;

public class SequenceProcessor {
    public Drive drive;

    public SequenceProcessor(){

        drive = new Drive(DrivePhase.NEUTRAL,DrivePhase.DRIVE);

    }

    public void process(){
        if (true){
            drive.start(RobotContainer.swerveDrive);
        }

        drive.process();
    }
}