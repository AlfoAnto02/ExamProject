package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import java.util.*;

/***
 * This class implements the IRobot interface.
 */
public class Robot implements IRobot <IPosition,ICondition>{
    private final IPosition robotPosition;
    private ICondition robotCondition;
    private final IController <ICommand<IRobot<IPosition,ICondition>>, IRobot<IPosition,ICondition>> robotController;
    private final LinkedList<Integer> loopTracker = new LinkedList<>();

    public Robot (IPosition robotPosition){
        if(robotPosition==null) throw new NullPointerException("You can't add a robot with a null position");
        this.robotPosition = robotPosition;
        this.robotCondition =null;
        this.robotController= new Controller<>();
    }

    public boolean checkDistanceBetweenRobot(List<? extends IRobot<IPosition,ICondition>> robots, double dist){
        if(dist < 0) throw new IllegalArgumentException("The distance value must be >= 0");
        return robots.stream()
                .filter(robot -> !robot.equals(this))
                .anyMatch(robot -> {
                    double distance = Math.sqrt(Math.pow((this.getRobotPosition().getX() - robot.getRobotPosition().getX()), 2)
                            + Math.pow((this.getRobotPosition().getY() - robot.getRobotPosition().getY()), 2));
                    return distance <= dist;
                });
    }

    public void consume(){
        this.getRobotController().consume(this);
    }

    public IController<ICommand<IRobot<IPosition,ICondition>>, IRobot<IPosition,ICondition>> getRobotController() {
        return robotController;
    }

    public LinkedList<Integer> getLoopTracker() {
        return loopTracker;
    }

    public IPosition getRobotPosition(){
        return this.robotPosition;
    }

    public ICondition getRobotCondition(){
         return this.robotCondition;
    }

    public void setRobotCondition(ICondition condition){
        if(condition==null) throw new NullPointerException("The condition must be not null");
        this.robotCondition=new Condition(condition.getCondition());
    }

    public void setRobotPosition(double x, double y) {
        this.robotPosition.setY(y);
        this.robotPosition.setX(x);
    }
}