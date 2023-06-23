package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;

public interface IEnvironment <R extends Robot, S extends AbstracShape>{
     List<R> robotList();

     List<S> shapeList();


}
