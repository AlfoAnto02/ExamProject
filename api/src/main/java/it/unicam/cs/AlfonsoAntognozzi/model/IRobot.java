package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import java.util.LinkedList;
import java.util.List;

/**
 * This interface is used to represent the common behaviors of a robot.
 * Each robot has a loopTracker that represents a List of Integer where there will be stored the precise step through the
 * program counter where the loop starts. Another Logic behind this tracker is that every time the loop will end, the last
 * index will be set at -1 and the next time it will be consumed a done command, this index will be removed from the loopTracker.
 * To divide the tasks of the classes, each robot has a robotController for handle the commands control.
 *
 * @param <P> Position of the robot
 * @param <C> Condition of the robot
 */
public interface IRobot<P,C> {
     /**
      * Method used to get the position of the Robot
      *
      * @return the position of the robot
      */
     P getRobotPosition();

     /**
      * Method used to get the condition of the Robot
      *
      * @return the condition of the robot
      */
     C getRobotCondition();

     /**
      * Method used to set a new condition for the robot
      *
      * @param condition new condition to set
      */
     void setRobotCondition(C condition);

     /**
      * Method used to get the loopTracker of the robot. The loopTracker's task is to save the index of where each loopCommand start.
      *
      * @return the loopTracker
      */
     LinkedList<Integer> getLoopTracker();

     /**
      * This method is used to check if at least one robot in the RobotList passes at least "dist" meters away (Dist is the
      * variable that represent the minimum distance)
      *
      * @param RobotList robotList that has to check if at least one robot in the List is distant "dist" meters away from the class Robot
      * @param dist minimum distance
      * @return True if at least one robot "dist" meters away, False otherwise.
      */
     boolean checkDistanceBetweenRobot(List<? extends IRobot<P,C>> RobotList, double dist);

     /**
      * Method used to get the Controller of the Robot
      *
      * @return the controller of the robot
      */
     IController<ICommand<IRobot<IPosition, ICondition>>, IRobot<IPosition, ICondition>> getRobotController();

     /**
      * This method will consume the next command of the robot (Using the robot controller of the class).
      */
     void consume();

     /**
      * Method used to set a new Position for the Robot
      *
      * @param x x coordinate
      * @param y y coordinate
      */
     void setRobotPosition(double x, double y);
}