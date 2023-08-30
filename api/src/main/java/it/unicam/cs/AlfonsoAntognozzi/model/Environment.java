package it.unicam.cs.AlfonsoAntognozzi.model;

import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the IEnvironment interface.
 *
 * @param <R> type of robot
 * @param <S> type of shape
 */
public class Environment<R extends IRobot<IPosition, ICondition>, S extends IShape<IPosition,ICondition,IRobot<IPosition,ICondition>>> implements IEnvironment<R,S> {

    private final List<S> shapeList;

    private final List<R> robotList;

    /**
     * This is the constructor of the class
     *
     * @param robotList robotList passed to the constructor and then added to the Environment.
     */
    public Environment (List<R> robotList){
        if(robotList.isEmpty()) throw new IllegalArgumentException("There need to be at least 1 robot in the Environment");
        this.shapeList = new ArrayList<>();
        this.robotList=robotList;
    }

    /**
     * Method used to add a shape to the shapeList of the Environment
     *
     * @param shape Shape that has to be added to the shapeList of the Environment
     */
    public void addShapeToList(S shape) {
        if(shape==null) throw new NullPointerException("You can't add a null shape");
        this.shapeList.add(shape);
    }

    /**
     * This method checks if at least one robot in the environment has another instruction to be consumed.
     *
     * @return True if the environment has another instruction to be consumed, False otherwise
     */
    public boolean hasNextInstruction(){
        return robotList.stream()
                .anyMatch(robot -> robot.getRobotController().hasNextInstruction());
    }

    /**
     * This method consumes each robot's next instruction(if the robot has another to consume)
     */
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

