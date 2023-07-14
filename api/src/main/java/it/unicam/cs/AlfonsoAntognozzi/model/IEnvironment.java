package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;

/***
 * This interface is used to represent the common behaviors of the simulation environment.
 * @param <R> type of Robot
 * @param <S> type of Shape
 */
public interface IEnvironment <R,S>{
     List<R> getRobotList();

     List<S> getShapeList();

     void addShapeToList(S Shape);
     /*
     This method checks if at least one robot in the environment has another instruction to be consumed.
      */
     boolean hasNextInstruction();
     /*
     This method consumes each robot's next instruction(if the robot has another to consume)
      */
     void executeNextInstruction();


}
