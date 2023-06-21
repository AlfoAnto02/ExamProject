package progetto;

public class doForeverCommand implements ICommand {
    @Override
    public void Apply(Robot RobotApplyed) {
        RobotApplyed.getLoopTracker().add(RobotApplyed.getProgrammCounter());
    }
}
