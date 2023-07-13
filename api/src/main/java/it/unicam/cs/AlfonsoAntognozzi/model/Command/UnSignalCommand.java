package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/***
 * This class implements the ICommand interface and its task is to make the robot un signal the actual Condition.
 * @param <R> type of robot
 */
public class UnSignalCommand <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    @Override
    public void apply(R robotApplied) {
        robotApplied.setRobotCondition(new Condition("_"));
    }
}
