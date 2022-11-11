package frc.robot.sequences;

import javax.sound.midi.Sequence;

import frc.robot.RobotContainer;

public class SequenceProcessor {
    public Drive drive;
    public Shoot shoot;

    public SequenceProcessor(){

        drive = new Drive(DrivePhase.NEUTRAL,DrivePhase.DRIVE);
        shoot = new Shoot(ShooterPhase.NEUTRAL,ShooterPhase.NEUTRAL);

    }

    public void process(){
        if (true){
            drive.start(RobotContainer.swerveDrive);
            shoot.start(RobotContainer.shooter);
        }
        shoot.process();
        drive.process();
    }
}