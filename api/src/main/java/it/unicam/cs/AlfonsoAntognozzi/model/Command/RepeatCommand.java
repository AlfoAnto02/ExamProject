package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class RepeatCommand implements ICommand{
    private int n;
    private final int loopLock;
    public RepeatCommand(int n){
        this.n=n;
        this.loopLock=n;
    }
    @Override
    public void Apply(Robot RobotApplied) {
        if(n==this.loopLock) {
            RobotApplied.getLoopTracker().add(RobotApplied.getRobotController().getProgramCounter());
        }
        this.n--;
        if (n<1){
            RobotApplied.getLoopTracker().set(RobotApplied.getLoopTracker().size()-1,-1);
            this.n = this.loopLock;
        }
    }
}
