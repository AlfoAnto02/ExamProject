package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.doneCommand;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
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
        this.ListOfCommands= new ArrayList<ICommand>();
    }

    public LinkedList<Integer> getLoopTracker() {
        return loopTracker;
    }

    public void Consume (){
        this.getListOfCommands().get(programmCounter).Apply(this);
        this.programmCounter++;
    }

    public boolean hasNextIstruction(){
        if(this.getProgrammCounter()< this.getListOfCommands().size()) return true;
        return false;
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

    public IRobot checkCollision(List<IShape> shapeList){
        for(IShape S : shapeList){
            if(S.checkCollision(this)) return this;
        }
        return null;
    }

    public void setRobotPosition(double x, double y) {
        this.RobotPosition.setY(y);
        this.RobotPosition.setX(x);
    }


    public void skipUntilIstruction() {
        List<ICommand> sublist = this.ListOfCommands.subList(this.programmCounter,this.ListOfCommands.size());
        for (ICommand C : sublist){
            if(!(C instanceof doneCommand)) this.programmCounter++;
            else break;
        }
    }
}
