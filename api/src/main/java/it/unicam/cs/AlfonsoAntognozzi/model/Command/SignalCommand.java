package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;


public class SignalCommand <R extends IRobot, C extends ICondition> implements ICommand <R>{
    private final C label;
    public SignalCommand(C l){
        this.label=l;
    }
    @Override
    public void Apply(R RobotApplied) {
        RobotApplied.setRobotCondition(this.label);
    }
}
