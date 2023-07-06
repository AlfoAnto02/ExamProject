package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.ArrayList;
import java.util.List;

public class Environment<R extends IRobot, S extends IShape> implements IEnvironment<R,S> {

    private final List<S> shapeList;

    private final List<R> robotList;

    public Environment (List<R> robotList){
        this.shapeList = new ArrayList<>();
        this.robotList=robotList;
    }

    public void addShapeToList(S Shape) {
        this.shapeList.add(Shape);
    }

    public boolean hasNextInstruction(){
        return robotList.stream()
                .anyMatch(robot -> robot.getRobotController().hasNextIstruction());
    }

    public void executeNextIstruction(){
        robotList.forEach(robot -> {
            if (robot.getRobotController().hasNextIstruction()) {
                robot.Consume();
            }
        });
    }

    public List<R> robotList() {
        return robotList;
    }

    public List<S> shapeList() {
        return shapeList;
    }


}

