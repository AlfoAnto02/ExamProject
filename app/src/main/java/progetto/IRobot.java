package progetto;

import java.util.List;

public interface IRobot {
     Position getRobotPosition();

     Condition getRobotCondition();

     double getRobotSpeed();

     void setRobotCondition(Condition c);

     List<ICommand> getListOfCommands();

     void Consume ();

}
