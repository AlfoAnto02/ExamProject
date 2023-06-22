package progetto;

public class repeatCommand implements ICommand{
    private int n;

    private int loopLock;


    public repeatCommand(int n){
        this.n=n;
        this.loopLock=n;
    }
    @Override
    public void Apply(Robot RobotApplyed) {
        if(n==this.loopLock) RobotApplyed.getLoopTracker().add(RobotApplyed.getProgrammCounter());
        this.n--;
        if (n<1){
            RobotApplyed.getLoopTracker().set(RobotApplyed.getLoopTracker().size(),-1);
            this.n = this.loopLock;
        }
    }
}
