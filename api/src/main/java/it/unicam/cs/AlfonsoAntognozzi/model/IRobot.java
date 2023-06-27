package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import java.util.LinkedList;
import java.util.List;

public interface IRobot {
     Position getRobotPosition();

     Condition getRobotCondition();

     void setRobotCondition(Condition condition);

     LinkedList<Integer> getLoopTracker();

     boolean checkDistanceBetweenRobot(List<IRobot> R, double dist);

     Controller getRobotController();

     void Consume();

}