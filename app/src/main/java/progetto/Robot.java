package progetto;

import java.util.*;

public class Robot implements IRobot{
    private Position RobotPosition;
    private Condition RobotCondition;
    private List<ICommand> ListOfCommands;
    private int programmCounter =0;
    private LinkedList<Integer> loopTracker = new LinkedList<Integer>();

    public Robot (Position robotPosition){
        this.RobotPosition=robotPosition;
        this.RobotCondition=null;
    }


    public LinkedList<Integer> getLoopTracker() {
        return loopTracker;
    }

    public void Consume (){
        this.getListOfCommands().get(programmCounter).Apply(this);
        this.programmCounter++;
    }


    public int getProgrammCounter() {
        return programmCounter;
    }

    public void setProgramCounter(int n){
        this.programmCounter=n;
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

    public void setRobotCondition(Condition condition){
        this.RobotCondition=condition;
    }


    public void setRobotPosition(double x, double y) {
        this.RobotPosition.setY(y);
        this.RobotPosition.setX(x);
    }


}
