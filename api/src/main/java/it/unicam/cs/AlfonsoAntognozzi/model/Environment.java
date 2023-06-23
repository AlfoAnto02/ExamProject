package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;

public class Environment implements IEnvironment {


    private final List<IShape> shapeList;


    private final List<IRobot> robotList;

    public Environment (List<IShape> shapeList, List<IRobot> robotList){
        this.shapeList=shapeList;
        this.robotList=robotList;
    }
    public List<IRobot> robotList() {
        return robotList;
    }

    public List<IShape> shapeList() {
        return shapeList;
    }


}

