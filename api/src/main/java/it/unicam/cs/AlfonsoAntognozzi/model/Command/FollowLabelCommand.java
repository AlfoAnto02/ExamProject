package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class FollowLabelCommand implements ICommand{

    private double[] args;

    public FollowLabelCommand(double args[]){
        this.args = args;
    }

    @Override
    public void Apply(Robot RobotApplyed) {
        double dist = this.args[0];
        double speed = this.args[1];
    }
}
