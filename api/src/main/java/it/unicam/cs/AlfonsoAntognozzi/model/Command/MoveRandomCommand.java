package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

import java.util.Random;

public class MoveRandomCommand implements ICommand{

    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;
    private final double speed;


    public MoveRandomCommand(double[] args){
        this.x1=args[0];
        this.x2=args[1];
        this.y1=args[2];
        this.y2=args[3];
        this.speed=args[4];
    }
    @Override
    public void Apply(Robot RobotApplyed) {
        Random random = new Random();
        double targetX = this.x1+ (this.x2-this.x1) * random.nextDouble();
        double targetY = this.y1 + (this.y2-this.y1) * random.nextDouble();
        double deltaX = Math.signum(targetX-RobotApplyed.getRobotPosition().getX()) * this.speed;
        double deltaY = Math.signum(targetY-RobotApplyed.getRobotPosition().getY()) * this.speed;
        RobotApplyed.getRobotPosition().setX(deltaX+RobotApplyed.getRobotPosition().getX());
        RobotApplyed.getRobotPosition().setY(deltaY+RobotApplyed.getRobotPosition().getY());
    }

}
