package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Condition;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class SignalCommand implements ICommand{
    private final Condition label;
    public SignalCommand(Condition l){
        this.label=l;
    }
    @Override
    public void Apply(Robot RobotApplied) {
        RobotApplied.setRobotCondition(this.label);
    }
}
