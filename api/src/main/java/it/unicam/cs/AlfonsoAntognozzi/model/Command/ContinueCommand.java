package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class ContinueCommand implements ICommand{
    private final int s;
    public ContinueCommand(int s){
        this.s=s;
    }
    @Override
    public void Apply(Robot RobotApplied) {
        for(int i=0; i < this.s; i++){
            RobotApplied.getRobotController().setProgramCounter(RobotApplied.getRobotController().getProgramCounter()-1);
            RobotApplied.getRobotController().Consume(RobotApplied);
        }
    }
}