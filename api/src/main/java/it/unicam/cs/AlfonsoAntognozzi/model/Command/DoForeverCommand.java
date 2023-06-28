package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class DoForeverCommand implements ICommand {
    @Override
    public void Apply(Robot RobotApplied) {
        RobotApplied.getLoopTracker().add(RobotApplied.getRobotController().getProgramCounter());
    }
}
