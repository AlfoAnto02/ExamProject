package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;


public class RepeatCommand <R extends IRobot> implements ICommand <R>{
    private int n;
    private final int loopLock;
    public RepeatCommand(int n){
        this.n=n;
        this.loopLock=n;
    }
    @Override
    public void Apply(R RobotApplied) {
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
