package progetto;

public class untilCommand implements ICommand{
    @Override
    public void Apply(Robot RobotApplyed) {

        RobotApplyed.getLoopTracker().add(RobotApplyed.getProgrammCounter()-1);
    }
}
