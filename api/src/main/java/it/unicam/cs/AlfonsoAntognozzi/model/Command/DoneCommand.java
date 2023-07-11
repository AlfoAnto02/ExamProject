package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

public class DoneCommand<R extends IRobot<IPosition, ICondition>> implements ICommand<R> {

    public void Apply(R RobotApplied) {
        if (!RobotApplied.getLoopTracker().isEmpty() &&  RobotApplied.getLoopTracker().getLast() == -1) {
            RobotApplied.getLoopTracker().removeLast();
        } else if (!RobotApplied.getLoopTracker().isEmpty()){
            RobotApplied.getRobotController().setProgramCounter(RobotApplied.getLoopTracker().getLast() - 1);
        }
    }
}
