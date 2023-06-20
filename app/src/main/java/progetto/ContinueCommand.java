package progetto;

public class ContinueCommand implements ICommand{
    private int s;

    public ContinueCommand(int s){
        this.s=s;
    }
    @Override
    public void Apply(Robot RobotApplyed) {
        for(int i=0; i < this.s; i++){
            RobotApplyed.getListOfCommands().add(RobotApplyed.getListOfCommands().get(RobotApplyed.getProgrammCounter()-1));
            RobotApplyed.Consume();
        }
    }
}
