package progetto;

public class doneCommand implements ICommand{
    @Override
    public void Apply(Robot RobotApplyed) {
        if (RobotApplyed.getLoopTracker().getLast() != -1){
            RobotApplyed.setProgramCounter(RobotApplyed.getLoopTracker().getLast());
        } else {
            RobotApplyed.getLoopTracker().removeLast();
        }
    }
}
