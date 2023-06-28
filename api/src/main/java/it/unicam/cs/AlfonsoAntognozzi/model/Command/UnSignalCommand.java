package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class UnSignalCommand implements ICommand{
    @Override
    public void Apply(Robot RobotApplied) {
        RobotApplied.setRobotCondition(null);
    }
}
