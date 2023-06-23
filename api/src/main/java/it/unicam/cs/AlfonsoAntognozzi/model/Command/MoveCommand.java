package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class MoveCommand implements ICommand{

    private final double x;
    private final double y;
    private final double speed;

    public MoveCommand (double[] args){
        if(args[0] < -1 || args[0] > 1 || args[1] < -1 || args[1] > 1 ) throw new IllegalArgumentException("Can't accept value of x & y < -1 or > 1");
        this.x=args[0];
        this.y=args[1];
        this.speed=args[2];

    }

    public void Apply(Robot RobotApplyed) {
        double deltaX = this.x*this.speed;
        double deltaY = this.y*this.speed;
        RobotApplyed.getRobotPosition().setX(deltaX+RobotApplyed.getRobotPosition().getX());
        RobotApplyed.getRobotPosition().setY(deltaY+RobotApplyed.getRobotPosition().getY());
        }
    }

