package progetto;

public class StopCommand implements ICommand{
    @Override
    public void Apply(Robot RobotApplyed) {
        RobotApplyed.getListOfCommands().clear();
    }
}
