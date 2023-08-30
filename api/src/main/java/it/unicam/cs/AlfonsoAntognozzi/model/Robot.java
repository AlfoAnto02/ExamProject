package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import java.util.*;

/**
 * This class implements the IRobot interface.
 */
public class Robot implements IRobot <IPosition,ICondition>{
    private final IPosition robotPosition;
    private ICondition robotCondition;
    private final IController <ICommand<IRobot<IPosition,ICondition>>, IRobot<IPosition,ICondition>> robotController;
    private final LinkedList<Integer> loopTracker = new LinkedList<>();

    /**
     * This is the constructor of the class
     *
     * @param robotPosition Initial position of the robot.
     */
    public Robot (IPosition robotPosition){
        if(robotPosition==null) throw new NullPointerException("You can't add a robot with a null position");
        this.robotPosition = robotPosition;
        this.robotCondition =null;
        this.robotController= new Controller<>();
    }

    /**
     * This method is used to check if at least one robot in the RobotList passes at least "dist" meters away (Dist is the
     * variable that represent the minimum distance)
     *
     * @param robots robotList that has to check if at least one robot in the List is distant "dist" meters away from the class Robot
     * @param dist minimum distance
     * @return True if at least one robot "dist" meters away, False otherwise.
     */
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

    /**
     * This method will consume the next command of the robot (Using the robot controller of the class).
     */
    public void consume(){
        this.getRobotController().consume(this);
    }

    /**
     * Method used to get the Controller of the Robot
     *
     * @return the controller of the robot
     */
    public IController<ICommand<IRobot<IPosition,ICondition>>, IRobot<IPosition,ICondition>> getRobotController() {
        return robotController;
    }

    /**
     * Method used to get the loopTracker of the robot. The loopTracker's task is to save the index of where each loopCommand start.
     *
     * @return the loopTracker
     */
    public LinkedList<Integer> getLoopTracker() {
        return loopTracker;
    }

    /**
     * Method used to get the position of the Robot
     *
     * @return the position of the robot
     */

    public IPosition getRobotPosition(){
        return this.robotPosition;
    }

    /**
     * Method used to get the condition of the Robot
     *
     * @return the condition of the robot
     */
    public ICondition getRobotCondition(){
         return this.robotCondition;
    }

    /**
     * Method used to set a new condition for the robot
     *
     * @param condition new condition to set
     */
    public void setRobotCondition(ICondition condition){
        if(condition==null) throw new NullPointerException("The condition must be not null");
        this.robotCondition=new Condition(condition.getCondition());
    }

    /**
     * Method used to set a new Position for the Robot
     *
     * @param x x coordinate
     * @param y y coordinate
     */
    public void setRobotPosition(double x, double y) {
        this.robotPosition.setY(y);
        this.robotPosition.setX(x);
    }
}