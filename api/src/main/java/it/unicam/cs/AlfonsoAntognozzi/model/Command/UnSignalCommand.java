package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/**
 * This class implements the ICommand interface and its task is to make the robot un signal the actual Condition.
 *
 * @param <R> type of robot
 */
public class UnSignalCommand <R extends IRobot<IPosition, ICondition>,C extends ICondition> implements ICommand <R>{

    private final C label;

    /**
     * This is the constructor of the class
     *
     * @param l label that has to be unsignaled
     */
    public UnSignalCommand(C l){
        this.label = l;
    }

    /**
     * Method used to unsignaled a robot with a particular label
     *
     * @param robotApplied robot that has to apply the command
     */
    @Override
    public void apply(R robotApplied) {
        if(robotApplied==null) throw new NullPointerException("The robot passed is null");
        if(robotApplied.getRobotCondition().equals(this.label)) robotApplied.setRobotCondition(new Condition("_"));
    }
}
