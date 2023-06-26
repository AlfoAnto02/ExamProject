package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import java.util.LinkedList;
import java.util.List;

public interface IRobot {
     Position getRobotPosition();

     Condition getRobotCondition();

     void setRobotCondition(Condition condition);

     List<ICommand> getListOfCommands();

     void Consume ();

     void setProgramCounter(int n);

     public IRobot checkCollision(List<IShape> shapeList);

     public LinkedList<Integer> getLoopTracker();


     int getProgrammCounter();

     boolean checkDistanceBetweenRobot(List<IRobot> R, double dist);

     public boolean hasNextIstruction();
}