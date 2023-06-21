package progetto;

public class repeatCommand implements ICommand{
    private int n;

    public repeatCommand(int n){
        this.n=n;
    }
    @Override
    public void Apply(Robot RobotApplyed) {
        if(n>1) {
            RobotApplyed.getLoopTracker().add(RobotApplyed.getProgrammCounter());
            this.n--;
        } else{
            RobotApplyed.getLoopTracker().set(RobotApplyed.getLoopTracker().size(),-1);
        }

    }
}
