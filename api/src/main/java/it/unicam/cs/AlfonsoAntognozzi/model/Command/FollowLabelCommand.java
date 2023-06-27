package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

public class FollowLabelCommand implements ICommand{

    private final double[] args;

    private final Position followedPosition;

    public FollowLabelCommand(Position position, double[] args){
        this.args = args;
        this.followedPosition=position;
    }

    @Override
    public void Apply(Robot RobotApplyed) {
        double speed = this.args[1];
        double x = this.followedPosition.getX();
        double y = this.followedPosition.getY();
        double deltaX = x*speed;
        double deltaY = y*speed;
        RobotApplyed.setRobotPosition(deltaX+RobotApplyed.getRobotPosition().getX(), deltaY+RobotApplyed.getRobotPosition().getY());
    }
}
