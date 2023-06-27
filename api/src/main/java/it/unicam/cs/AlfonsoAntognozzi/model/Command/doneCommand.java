package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class doneCommand implements ICommand{
    @Override
    public void Apply(Robot RobotApplyed) {
        if (RobotApplyed.getLoopTracker().getLast() != -1){
            RobotApplyed.getRobotController().setProgramCounter(RobotApplyed.getLoopTracker().getLast()-1);
        } else if (!RobotApplyed.getLoopTracker().isEmpty()){
            RobotApplyed.getLoopTracker().removeLast();
        }
    }
}
