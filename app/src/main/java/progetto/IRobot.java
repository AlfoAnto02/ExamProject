package progetto;

public interface IRobot {
     Position getRobotPosition();

     Condition getRobotCondition();

     double getRobotSpeed();

     void setRobotCondition(Condition c);


}
