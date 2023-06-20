package progetto;

public class MoveCommand implements ICommand{

    private double[] args;

    public MoveCommand (double[] args){
        this.args=args;
    }
    @Override
    public void Apply(Robot R) {
        double deltaX = this.args[0] * args[2];
        double deltaY = this.args[1] * args[2];
        R.getRobotPosition().setX(deltaX+R.getRobotPosition().getX());
        R.getRobotPosition().setY(deltaY+R.getRobotPosition().getY());
        }
    }

