package it.unicam.cs.AlfonsoAntognozzi.model;

import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

import java.util.ArrayList;
import java.util.List;

/***
 * This class implements the IEnvironment interface.
 * @param <R> type of robot
 * @param <S> type of shape
 */
public class Environment<R extends IRobot<IPosition, ICondition>, S extends IShape<IPosition,ICondition,IRobot<IPosition,ICondition>>> implements IEnvironment<R,S> {

    private final List<S> shapeList;

    private final List<R> robotList;

    public Environment (List<R> robotList){
        if(robotList.isEmpty()) throw new IllegalArgumentException("There need to be at least 1 robot in the Environment");
        this.shapeList = new ArrayList<>();
        this.robotList=robotList;
    }

    public void addShapeToList(S shape) {
        if(shape==null) throw new NullPointerException("You can't add a null shape");
        this.shapeList.add(shape);
    }

    public boolean hasNextInstruction(){
        return robotList.stream()
                .anyMatch(robot -> robot.getRobotController().hasNextInstruction());
    }

    public void executeNextInstruction(){
        robotList.forEach(robot -> {
            if (robot.getRobotController().hasNextInstruction()) {
                robot.consume();
            }
        });
    }

    public List<R> getRobotList() {
        return robotList;
    }

    public List<S> getShapeList() {
        return shapeList;
    }


}

