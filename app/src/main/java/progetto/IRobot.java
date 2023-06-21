package progetto;

import java.util.List;

public interface IRobot  {
     Position getRobotPosition();

     Condition getRobotCondition();

     void setRobotCondition(Condition condition);

     List<ICommand> getListOfCommands();

     void Consume ();

     void setProgramCounter(int n);
}
