package progetto;

import java.util.ArrayList;
import java.util.List;

public class untilCommand implements ICommand{

    private int loopLocker;
    List<IShape> checkedShapeList = new ArrayList<IShape>();

    public untilCommand(List<IShape> r){

        this.checkedShapeList = r;

        this.loopLocker=0;
    }
    @Override
    public void Apply(Robot RobotApplyed) {
        if(this.loopLocker==0){
            RobotApplyed.getLoopTracker().add(RobotApplyed.getProgrammCounter());
            this.loopLocker=1;
        }
        boolean checked=false;
        for(IShape s : this.checkedShapeList){
            if(s.checkCollision(RobotApplyed) == true) checked=true;
        }
        if(!checked) {
            RobotApplyed.getLoopTracker().set(RobotApplyed.getLoopTracker().size(),-1);
            RobotApplyed.skipUntilIstruction();
            this.loopLocker=0;
        }

    }
}
