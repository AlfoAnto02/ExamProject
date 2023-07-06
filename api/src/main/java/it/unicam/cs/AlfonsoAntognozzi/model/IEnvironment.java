package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;

public interface IEnvironment <R extends IRobot, S extends IShape>{
     List<R> robotList();

     List<S> shapeList();

     void addShapeToList(S Shape);


}
