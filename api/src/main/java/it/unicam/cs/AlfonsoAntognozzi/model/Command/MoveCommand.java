package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/**
 * This class implements the ICommand interface and its task is to move the given robot
 * to the given direction (x,y) at a given speed.
 *
 * @param <R> type of Robot
 */
public class MoveCommand <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    private final double x;
    private final double y;
    private final double speed;

    /**
     * This is the constructor of the class
     *
     * @param args args[0] (x) and args[1] (y) must be between -1 and 1.
     */
    public MoveCommand (double[] args){
        if(args[0] < -1 || args[0] > 1 || args[1] < -1 || args[1] > 1 ) throw new IllegalArgumentException("Can't accept value of x & y < -1 or > 1");
        this.x = args[0];
        this.y = args[1];
        this.speed = args[2];

    }

    /**
     * Method used to apply the command and move the robot to the given direction.
     *
     * @param robotApplied robot that has to apply the command
     */
    public void apply(R robotApplied) {
        if(robotApplied==null) throw new NullPointerException("The robot passed is null");
        double deltaX = this.x * this.speed;
        double deltaY = this.y * this.speed;
        robotApplied.setRobotPosition(deltaX + robotApplied.getRobotPosition().getX(),deltaY + robotApplied.getRobotPosition().getY());
        }
    }

