package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.ArrayList;
import java.util.List;

public class Environment implements IEnvironment {

    private final List<IShape> shapeList;

    private final List<IRobot> robotList;

    public Environment (List<IRobot> robotList){
        this.shapeList = new ArrayList<>();
        this.robotList=robotList;
    }

    public void addShapeToList(IShape S) {
        this.shapeList.add(S);
    }

    public boolean hasNextInstruction(){
        for(IRobot R : this.robotList){
            if (R.getRobotController().hasNextIstruction()) return true;
        }
        return false;
    }

    public List<IRobot> robotList() {
        return robotList;
    }

    public List<IShape> shapeList() {
        return shapeList;
    }


}

