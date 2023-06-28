package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class DoneCommand implements ICommand {

    public void Apply(Robot RobotApplied) {
        if (!RobotApplied.getLoopTracker().isEmpty() && RobotApplied.getLoopTracker().getLast() == -1) {
            RobotApplied.getLoopTracker().removeLast();
        } else if (!RobotApplied.getLoopTracker().isEmpty()){
            RobotApplied.getRobotController().setProgramCounter(RobotApplied.getLoopTracker().getLast() - 1);
        }
    }
}
