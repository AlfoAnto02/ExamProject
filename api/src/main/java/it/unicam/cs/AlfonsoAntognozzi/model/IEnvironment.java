package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;

/**
 * This interface is used to represent the common behaviors of the simulation environment.
 *
 * @param <R> type of Robot
 * @param <S> type of Shape
 */
public interface IEnvironment <R,S>{
     /**
      * Method used to get the robotList of the Environment
      *
      * @return the robotList of the Environment
      */
     List<R> getRobotList();

     /**
      * Method used to get the shapeList of the Environment
      *
      * @return the shapeList of the Environment
      */
     List<S> getShapeList();

     /**
      * Method used to add a shape to the shapeList of the Environment
      *
      * @param Shape Shape that has to be added to the shapeList of the Environment
      */
     void addShapeToList(S Shape);

     /**
      * This method checks if at least one robot in the environment has another instruction to be consumed.
      *
      * @return True if the environment has another instruction to be consumed, False otherwise
      */
     boolean hasNextInstruction();

     /**
      * This method consumes each robot's next instruction(if the robot has another to consume)
      */
     void executeNextInstruction();


}
