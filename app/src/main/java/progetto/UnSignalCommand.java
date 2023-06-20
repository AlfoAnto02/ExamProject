package progetto;

public class UnSignalCommand implements ICommand{
    @Override
    public void Apply(Robot RobotApplyed) {
        RobotApplyed.setRobotCondition(null);
    }
}
