package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class ContinueCommand implements ICommand{
    private final int s;

    public ContinueCommand(int s){
        this.s=s;
    }
    @Override
    public void Apply(Robot RobotApplyed) {
        for(int i=0; i < this.s; i++){
            RobotApplyed.getRobotController().setProgramCounter(RobotApplyed.getRobotController().getProgramCounter()-1);
            RobotApplyed.getRobotController().Consume(RobotApplyed);
        }
    }
}