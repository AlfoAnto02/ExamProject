package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/***
 * This class implements the ICommand interface and its task is to repeat a given set of commands
 * n times. The repeated commands will be those present in the command list between this and the
 * next Done Command.
 * @param <R> type of robot
 */
public class RepeatCommand <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    private int counter;
    private final int loopLock;
    public RepeatCommand(int n){
        if(n<1) throw new IllegalArgumentException("The counter must be >=1");
        this.counter = n;
        this.loopLock = n;
    }
    /*
    This method is based on the mechanism set in motion by the "consume" method: the first time it will consume a RepeatCommand
    it will use the loopLock variable to store the program_counter step where the loop starts in the loopTracker of the
    Robot. Until the counter goes to 0, it will continue looping the commands. When the counter goes to 0 the last index
    in the loopTracker will be set to 0 so that in the next Done command it will be removed.
    Algorithm specifics: in case of in nested loop the counter variable will be set to the loopLock values.
     */
    @Override
    public void apply(R robotApplied) {
        if(robotApplied==null) throw new NullPointerException("The robot passed is null");
        if(counter == this.loopLock) {
            robotApplied.getLoopTracker().add(robotApplied.getRobotController().getProgramCounter());
        }
        this.counter--;
        if (counter < 1){
            robotApplied.getLoopTracker().set(robotApplied.getLoopTracker().size()-1,-1);
            this.counter = this.loopLock;
        }
    }
}
