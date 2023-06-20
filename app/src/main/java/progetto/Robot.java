package progetto;

import java.util.List;

public class Robot implements IRobot{
    private Position RobotPosition;

    private Condition RobotCondition;

    private double RobotSpeed;


    private List<ICommand> ListOfCommands;


    private int programmCounter=0;


    public Robot (Position P, double RobotSpeed){
        this.RobotPosition=P;
        this.RobotCondition=null;
        this.RobotSpeed=RobotSpeed;
    }




    public void Consume (){
        this.getListOfCommands().get(programmCounter).Apply(this);
        this.programmCounter++;
    }

    public int getProgrammCounter() {
        return programmCounter;
    }

    public List<ICommand> getListOfCommands() {
        return ListOfCommands;
    }
    public Position getRobotPosition(){
        return this.RobotPosition;
    }

    public Condition getRobotCondition(){
        return this.RobotCondition;
    }

    public void setRobotCondition(Condition c){
        this.RobotCondition=c;
    }

    public double getRobotSpeed(){
        return this.RobotSpeed;
    }

    public void setRobotPosition(double x, double y) {
        this.RobotPosition.setY(y);
        this.RobotPosition.setX(x);
    }


}
