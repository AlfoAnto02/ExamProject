package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/***
 * This class implements the ICommand interface and its task is to re-apply the last-
 * command applied to the given robot for "n" times.
 *
 * @param <R> type of Robot-
 */
public class ContinueCommand <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    private final int times;

    /***
     *
     * @param times how many times the last command will be applied-
     */
    public ContinueCommand(int times){
        if(times < 1) throw new IllegalArgumentException("Must be >= then 1!");
        this.times = times;
    }

    /*
    The logic behind this method is a linear for_loop that will apply the last command n times
     */
    @Override
    public void apply(R robotApplied) {
        for(int i = 0; i < this.times; i++){
            robotApplied.getRobotController().setProgramCounter(robotApplied.getRobotController().getProgramCounter()-1);
            robotApplied.getRobotController().consume(robotApplied);
        }
    }
}