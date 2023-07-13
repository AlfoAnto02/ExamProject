package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/***
 * This class implements the ICommand interface and its task is to make the robot signal a given condition.
 * @param <R>type of robot
 * @param <C>given condition
 */

public class SignalCommand <R extends IRobot<IPosition,ICondition>, C extends ICondition> implements ICommand <R>{
    private final C label;
    public SignalCommand(C l){
        this.label = l;
    }
    @Override
    public void apply(R robotApplied) {
        robotApplied.setRobotCondition(this.label);
    }
}
