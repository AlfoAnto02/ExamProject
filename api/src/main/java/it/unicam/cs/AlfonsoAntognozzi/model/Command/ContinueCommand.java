package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

public class ContinueCommand <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    private final int s;
    public ContinueCommand(int s){
        this.s=s;
    }
    @Override
    public void Apply(R RobotApplied) {
        for(int i=0; i < this.s; i++){
            RobotApplied.getRobotController().setProgramCounter(RobotApplied.getRobotController().getProgramCounter()-1);
            RobotApplied.getRobotController().Consume(RobotApplied);
        }
    }
}