package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/***
 * This class implements the ICommand interface and its task is to mark the end of
 * a loopCommand. Everytime the controller will apply this command, it will check if
 * the loop should restart or if it should end.
 *
 * @param <R>
 */
public class DoneCommand<R extends IRobot<IPosition, ICondition>> implements ICommand<R> {

    /*
   The logic behind this implementation is that each Robot has a loopTracker (an Integer List)
   that will store the program_counter step where the loop starts (Repeat,Until,DoForever...).
   The loop command will decide if the loop should restart or not.
   The done command task is to check if the loop is over (the last index of the loopTracker is -1) or not.
   In case it's over, it will remove the last index, otherwise it will set the program counter of the robot
   to the last index of the loopTracker (-1 because in the consumer the program counter will increase after the "apply method").
     */
    public void apply(R robotApplied) {
        if(robotApplied==null) throw new NullPointerException("The robot passed is null");
        if (!robotApplied.getLoopTracker().isEmpty() &&  robotApplied.getLoopTracker().getLast() == -1) {
            robotApplied.getLoopTracker().removeLast();
        } else if (!robotApplied.getLoopTracker().isEmpty()){
            robotApplied.getRobotController().setProgramCounter(robotApplied.getLoopTracker().getLast() - 1);
        }
    }
}
