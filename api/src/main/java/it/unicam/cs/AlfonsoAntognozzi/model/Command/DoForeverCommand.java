package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/***
 * This class implements the ICommand interface and its task is to repeat
 * a set of commands. The repeated commands will be those present in the
 * command list between this and the next Done Command.
 *
 * @param <R> type of robot-
 */
public class DoForeverCommand <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    @Override
    public void apply(R robotApplied) {
        if(robotApplied==null) throw new NullPointerException("The robot passed is null");
        robotApplied.getLoopTracker().add(robotApplied.getRobotController().getProgramCounter());
    }
}
