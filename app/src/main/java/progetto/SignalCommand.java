package progetto;

public class SignalCommand implements ICommand{

    private  Condition label;

    public SignalCommand(Condition l){
        this.label=l;
    }
    @Override
    public void Apply(Robot RobotApplyed) {
        RobotApplyed.setRobotCondition(this.label);
    }
}
