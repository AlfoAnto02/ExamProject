package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.model.IShape;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

import java.util.List;

public class UntilCommand <R extends IRobot<IPosition, ICondition>, S extends IShape<IPosition,ICondition,R>> implements ICommand <R>{
    private int loopLocker;
    private final List<S> checkedShapeList;

    public UntilCommand(List<S> r){
        this.checkedShapeList = r;
        this.loopLocker=0;
    }
    @Override
    public void Apply(R RobotApplied) {
        if(this.loopLocker==0){
            RobotApplied.getLoopTracker().add(RobotApplied.getRobotController().getProgramCounter());
            this.loopLocker=-1;
        }
        boolean checked = checkedShapeList.stream()
                .anyMatch(shape -> shape.checkCollision(RobotApplied));
        if(!checked) {
            RobotApplied.getRobotController().skipUntilInstruction();
            RobotApplied.getLoopTracker().set(RobotApplied.getLoopTracker().size()-1,-1);
            this.loopLocker=0;
        }
    }
}
