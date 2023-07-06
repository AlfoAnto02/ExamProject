package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import java.util.*;



public class Robot<P extends IPosition,C extends ICondition> implements IRobot <P,C>{

    private P robotPosition;

    private C robotCondition;
    
    private final Controller robotController;

    private LinkedList<Integer> loopTracker = new LinkedList<>();

    public Robot (P robotPosition){
        this.robotPosition = robotPosition;
        this.robotCondition =null;
        this.robotController= new Controller();
    }

    public boolean checkDistanceBetweenRobot(List<? extends IRobot> robots, double dist){
        return robots.stream()
                .filter(robot -> !robot.equals(this))
                .anyMatch(robot -> {
                    double distance = Math.sqrt(Math.pow((this.getRobotPosition().getX() - robot.getRobotPosition().getX()), 2)
                            + Math.pow((this.getRobotPosition().getY() - robot.getRobotPosition().getY()), 2));
                    return distance <= dist;
                });
    }



    public void Consume(){
        this.getRobotController().Consume(this);
    }

    public Controller getRobotController() {
        return robotController;
    }

    public LinkedList<Integer> getLoopTracker() {
        return loopTracker;
    }

    public P getRobotPosition(){
        return this.robotPosition;
    }

    public C getRobotCondition(){
         return this.robotCondition;
    }

    public void setRobotCondition(C condition){
        this.robotCondition=condition;
    }

    public void setRobotPosition(double x, double y) {
        this.robotPosition.setY(y);
        this.robotPosition.setX(x);
    }
}