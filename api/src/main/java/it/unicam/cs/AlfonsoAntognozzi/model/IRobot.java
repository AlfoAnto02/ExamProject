package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import java.util.LinkedList;
import java.util.List;

public interface IRobot<P extends IPosition,C extends ICondition> {
     P getRobotPosition();

     C getRobotCondition();

     void setRobotCondition(C condition);

     LinkedList<Integer> getLoopTracker();

     boolean checkDistanceBetweenRobot(List<? extends IRobot<P,C>> RobotList, double dist);

     Controller<ICommand<IRobot<IPosition, ICondition>>, IRobot<IPosition, ICondition>> getRobotController();

     void Consume();

     void setRobotPosition(double v, double v1);
}