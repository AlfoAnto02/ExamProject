package progetto;

public class MoveCommand implements ICommand{

    private final double x;

    private final double y;
    private final double speed;

    public MoveCommand (double[] args){
        this.x=args[0];
        this.y=args[1];
        this.speed=args[2];

    }
    @Override
    public void Apply(Robot R) {
        double deltaX = this.x*this.speed;
        double deltaY = this.y*this.speed;
        R.getRobotPosition().setX(deltaX+R.getRobotPosition().getX());
        R.getRobotPosition().setY(deltaY+R.getRobotPosition().getY());
        }
    }

