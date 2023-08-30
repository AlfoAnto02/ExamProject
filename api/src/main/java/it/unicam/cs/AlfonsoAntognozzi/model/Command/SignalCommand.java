package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/**
 * This class implements the ICommand interface and its task is to make the robot signal a given condition.
 *
 * @param <R>type of robot
 * @param <C>given condition
 */

public class SignalCommand <R extends IRobot<IPosition,ICondition>, C extends ICondition> implements ICommand <R>{
    private final C label;

    /**
     * This is the constructor of the class
     *
     * @param l label that has to be signaled
     */
    public SignalCommand(C l){
        this.label = l;
    }

    /**
     * Method used to signal the given label to a robot
     *
     * @param robotApplied robot that has to apply the command
     */
    @Override
    public void apply(R robotApplied) {
        if(robotApplied==null) throw new NullPointerException("The robot passed is null");
        robotApplied.setRobotCondition(this.label);
    }
}
