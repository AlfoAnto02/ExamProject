package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import java.util.*;



public class Robot implements IRobot{

    private Position robotPosition;

    private Condition robotCondition;
    
    private final Controller robotController;

    private LinkedList<Integer> loopTracker = new LinkedList<>();

    public Robot (Position robotPosition){
        this.robotPosition =robotPosition;
        this.robotCondition =null;
        this.robotController= new Controller();
    }

    public LinkedList<Integer> getLoopTracker() {
        return loopTracker;
    }

    public boolean checkDistanceBetweenRobot(List<IRobot> R, double dist){
        double distance;
        for(IRobot temp : R){
            distance = Math.sqrt(Math.pow( (this.getRobotPosition().getX()-temp.getRobotPosition().getX() ),2)
                    +Math.pow((this.getRobotPosition().getY() - temp.getRobotPosition().getY()),2));
            if(distance<=dist) return true;
        }
        return false;
    }

    public Controller getRobotController() {
        return robotController;
    }

    public void Consume(){
        this.getRobotController().Consume(this);
    }

    public Position getRobotPosition(){
        return this.robotPosition;
    }

    public Condition getRobotCondition(){
        return this.robotCondition;
    }

    public void setRobotCondition(Condition condition){
        this.robotCondition=condition;
    }

    public void setRobotPosition(double x, double y) {
        this.robotPosition.setY(y);
        this.robotPosition.setX(x);
    }
}