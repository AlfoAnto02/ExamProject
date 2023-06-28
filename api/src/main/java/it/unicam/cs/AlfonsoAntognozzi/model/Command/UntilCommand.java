package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IShape;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;
import java.util.List;

public class UntilCommand implements ICommand{
    private int loopLocker;
    private final List<IShape> checkedShapeList;

    public UntilCommand(List<IShape> r){
        this.checkedShapeList = r;
        this.loopLocker=0;
    }
    @Override
    public void Apply(Robot RobotApplied) {
        boolean checked=false;
        if(this.loopLocker==0){
            RobotApplied.getLoopTracker().add(RobotApplied.getRobotController().getProgramCounter());
            this.loopLocker=-1;
        }
        for(IShape s : this.checkedShapeList){
            if(s.checkCollision(RobotApplied)) checked=true;
        }
        if(!checked) {
            RobotApplied.getRobotController().skipUntilInstruction();
            RobotApplied.getLoopTracker().set(RobotApplied.getLoopTracker().size()-1,-1);
            this.loopLocker=0;
        }
    }
}
