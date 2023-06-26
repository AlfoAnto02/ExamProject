package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

public class FollowLabelCommand implements ICommand{

    private double[] args;

    private Position followedPosition;

    public FollowLabelCommand(Position position, double[] args){
        this.args = args;
        this.followedPosition=position;
    }

    @Override
    public void Apply(Robot RobotApplyed) {
        double dist = this.args[0];
        double speed = this.args[1];
        double x = this.followedPosition.getX();
        double y = this.followedPosition.getY();
        double deltaX = x*speed;
        double deltaY = y*speed;
        RobotApplyed.getRobotPosition().setX(deltaX+RobotApplyed.getRobotPosition().getX());
        RobotApplyed.getRobotPosition().setY(deltaY+RobotApplyed.getRobotPosition().getY());

    }
}
