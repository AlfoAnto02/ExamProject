package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IShape;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;
import java.util.List;

public class untilCommand implements ICommand{

    private int loopLocker;
    private final List<IShape> checkedShapeList;

    public untilCommand(List<IShape> r){
        this.checkedShapeList = r;
        this.loopLocker=0;
    }
    @Override
    public void Apply(Robot RobotApplyed) {
        boolean checked=false;
        if(this.loopLocker==0){
            RobotApplyed.getLoopTracker().add(RobotApplyed.getRobotController().getProgramCounter());
            this.loopLocker=-1;
        }
        for(IShape s : this.checkedShapeList){
            if(s.checkCollision(RobotApplyed)) checked=true;
        }
        if(!checked) {
            RobotApplyed.getRobotController().skipUntilInstruction();
            RobotApplyed.getLoopTracker().set(RobotApplyed.getLoopTracker().size()-1,-1);
            this.loopLocker=0;
        }
    }
}
