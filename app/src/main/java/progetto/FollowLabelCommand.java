package progetto;

public class FollowLabelCommand implements ICommand{

    private double[] args;

    public FollowLabelCommand(double args[]){
        this.args = args;
    }

    @Override
    public void Apply(Robot RobotApplyed) {

    }
}
