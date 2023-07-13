package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;

public interface IEnvironment <R,S>{
     List<R> getRobotList();

     List<S> getShapeList();

     void addShapeToList(S Shape);


}
