package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

import java.util.Random;

public class MoveRandomCommand  <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
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
    public void Apply(R RobotApplied) {
        Random random = new Random();
        double targetX = this.x1+ (this.x2-this.x1) * random.nextDouble();
        double targetY = this.y1 + (this.y2-this.y1) * random.nextDouble();
        double deltaX = Math.signum(targetX- RobotApplied.getRobotPosition().getX()) * this.speed;
        double deltaY = Math.signum(targetY- RobotApplied.getRobotPosition().getY()) * this.speed;
        RobotApplied.setRobotPosition(deltaX+ RobotApplied.getRobotPosition().getX(),deltaY+ RobotApplied.getRobotPosition().getY());
    }

}
