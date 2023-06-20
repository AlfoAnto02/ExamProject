package progetto;

import java.util.Random;

public class MoveRandomCommand implements ICommand{


    private double [] args;

    public MoveRandomCommand(double[] args){

        this.args=args;


    }
    @Override
    public void Apply(Robot RobotApplyed) {
        Random random = new Random();
        double targetX = this.args[0]+ (this.args[1]-this.args[0]) * random.nextDouble();
        double targetY = this.args[2] + (this.args[3]-this.args[2]) * random.nextDouble();
        double deltaX = Math.signum(targetX-RobotApplyed.getRobotPosition().getX()) * this.args[4];
        double deltaY = Math.signum(targetY-RobotApplyed.getRobotPosition().getY()) * this.args[4];
        RobotApplyed.getRobotPosition().setX(deltaX+RobotApplyed.getRobotPosition().getX());
        RobotApplyed.getRobotPosition().setY(deltaY+RobotApplyed.getRobotPosition().getY());
    }

}
