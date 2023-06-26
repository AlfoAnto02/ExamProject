package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.doneCommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.repeatCommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.untilCommand;
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
    public boolean checkDistanceBetweenRobot(List<IRobot> R, double dist){
        double distance = 0;
        for(IRobot temp : R){
            distance = Math.sqrt(Math.pow( (this.getRobotPosition().getX()-temp.getRobotPosition().getX() ),2)
                    +Math.pow((this.getRobotPosition().getY() - temp.getRobotPosition().getY()),2));
            if(distance<=dist) return true;
        }
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
        Stack<ICommand> stack = new Stack<>();
        for (ICommand C : sublist) {
            if(C instanceof untilCommand || C instanceof repeatCommand) stack.push(C);
            else if(C instanceof doneCommand) stack.pop();
            if(stack.isEmpty()) break;
            this.programmCounter++;
        }

    }
}
