package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import java.util.LinkedList;
import java.util.List;

/***
 * This interface is used to represent the common behaviors of a robot.
 * Each robot has a loopTracker that represents a List of Integer where there will be stored the precise step through the
 * program counter where the loop starts. Another Logic behind this tracker is that every time the loop will end, the last
 * index will be set at -1 and the next time it will be consumed a done command, this index will be removed from the loopTracker.
 * To divide the tasks of the classes, each robot has a robotController for handle the commands control.
 * @param <P> Position of the robot
 * @param <C> Condition of the robot
 */
public interface IRobot<P,C> {
     P getRobotPosition();

     C getRobotCondition();

     void setRobotCondition(C condition);

     LinkedList<Integer> getLoopTracker();

     /*
     This method is used to check if at least on robot in the RobotList passes at least "dist" meters away (Dist is the
     variable that represent the minimum distance)
      */
     boolean checkDistanceBetweenRobot(List<? extends IRobot<P,C>> RobotList, double dist);

     IController<ICommand<IRobot<IPosition, ICondition>>, IRobot<IPosition, ICondition>> getRobotController();

     /*
     This method will consume the next command of the robot (Using the robot controller of the class).
      */
     void consume();

     void setRobotPosition(double v, double v1);
}