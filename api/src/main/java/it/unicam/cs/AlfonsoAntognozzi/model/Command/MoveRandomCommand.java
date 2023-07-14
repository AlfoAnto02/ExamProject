package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

import java.util.Random;

/***
 * This class implements the ICommand interface and its task is to move the given robot
 * to a random Location determined by the arguments of the variable args.
 *
 * @param <R>type of Robot
 */

public class MoveRandomCommand  <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    private final double x1;
    private final double x2;
    private final double y1;
    private final double y2;
    private final double speed;

    public MoveRandomCommand(double[] args){
        if(args[4]<0) throw new IllegalArgumentException("The speed must be >0");
        this.x1 = args[0];
        this.x2 = args[1];
        this.y1 = args[2];
        this.y2 = args[3];
        this.speed = args[4];
    }
    /*
       The logic behind this method is that, based on the given coordinates (x1,y1) (x2,y2), a random direction will
       be calculated between these coordinates.
     */
    @Override
    public void apply(R robotApplied) {
        if(robotApplied==null) throw new NullPointerException("The robot passed is null");
        Random random = new Random();
        double targetX = this.x1 + (this.x2 - this.x1) * random.nextDouble();
        double targetY = this.y1 + (this.y2 - this.y1) * random.nextDouble();
        double deltaX = Math.signum(targetX - robotApplied.getRobotPosition().getX()) * this.speed;
        double deltaY = Math.signum(targetY - robotApplied.getRobotPosition().getY()) * this.speed;
        robotApplied.setRobotPosition(deltaX + robotApplied.getRobotPosition().getX(),deltaY + robotApplied.getRobotPosition().getY());
    }

}
