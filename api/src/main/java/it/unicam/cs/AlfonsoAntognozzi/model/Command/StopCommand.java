package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/***
 * This class implements the ICommand interface and its task is only to stop the robot Movement (it will not stop signaling
 * a condition)
 * @param <R> type of robot
 */
public class StopCommand <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    @Override
    public void apply(R robotApplied) {
        if(robotApplied==null) throw new NullPointerException("The robot passed is null");
        robotApplied.getRobotController().getCommandList().clear();
    }
}
