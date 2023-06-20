package progetto;

import java.util.List;

public class Environment implements IEnvironment {


    private final List<IShape> shapeList;


    private final List<IRobot> robotList;

    public Environment (List<IShape> shapeList, List<IRobot> robotList){
        this.shapeList=shapeList;
        this.robotList=robotList;
    }
    public List<IRobot> getRobotList() {
        return robotList;
    }

    public List<IShape> getShapeList() {
        return shapeList;
    }

    public boolean isSignaled(Condition c){
        for(IRobot R : robotList){
            if(R.getRobotCondition().equals(c)) return true;
        }
        return false;
    }

}
