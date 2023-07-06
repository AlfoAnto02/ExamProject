package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;

public class DoneCommand<R extends IRobot> implements ICommand<R> {

    public void Apply(R RobotApplied) {
        if (!RobotApplied.getLoopTracker().isEmpty() && (int) RobotApplied.getLoopTracker().getLast() == -1) {
            RobotApplied.getLoopTracker().removeLast();
        } else if (!RobotApplied.getLoopTracker().isEmpty()){
            RobotApplied.getRobotController().setProgramCounter((int) RobotApplied.getLoopTracker().getLast() - 1);
        }
    }
}
