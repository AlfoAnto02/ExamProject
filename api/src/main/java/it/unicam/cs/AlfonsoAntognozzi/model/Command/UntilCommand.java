package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.model.IShape;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import java.util.List;

/***
 * This class implements the ICommand interface and its task is to repeat a given set of commands
 * until a certain condition is not perceived in the environment (which means, until the robot is in a figure that is marked
 * by a particular condition).
 *
 * @param <R> type of Robot
 * @param <S> type of Shape
 */
public class UntilCommand <R extends IRobot<IPosition, ICondition>, S extends IShape<IPosition,ICondition,R>> implements ICommand <R>{
    private int loopLocker;
    private final List<S> shapeList;
    private final Condition checkedCondition;

    /***
     *
     * @param checkedShapeList shapeList of the Environment.
     * @param label the particular condition of the method.
     */
    public UntilCommand(List<S> checkedShapeList,String label){
        this.shapeList = checkedShapeList;
        checkedCondition = new Condition(label);
        this.loopLocker = 0;
    }
    /*
    The shapes that are marked by the condition given in the Constructor of the class are going to be stored in List<S>
    checkedShapeList. Everytime this method is called, it will check if the robot is in the figure that is marked by the
    condition. In this case the loop will continue, otherwise it will skip the commands that are in loop and set the
    last index of the robot LoopTracker to -1.
    Algorithm Spec: in case of In nested loop, the loopLocker is set to 0.
     */
    @Override
    public void apply(R robotApplied) {
        List<S> checkedShapeList = shapeList
                .stream()
                .filter(s -> s.getShapeCondition().equals(checkedCondition))
                .toList();
        if(this.loopLocker == 0){
            robotApplied.getLoopTracker().add(robotApplied.getRobotController().getProgramCounter());
            this.loopLocker= -1;
        }
        boolean checked = checkedShapeList.stream()
                .anyMatch(shape -> shape.checkCollision(robotApplied));
        if(checked) {
            robotApplied.getRobotController().skipUntilInstruction();
            robotApplied.getLoopTracker().set(robotApplied.getLoopTracker().size()-1,-1);
            this.loopLocker = 0;
        }
    }
}
