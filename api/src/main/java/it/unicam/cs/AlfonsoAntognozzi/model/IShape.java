package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;


public interface IShape <P extends IPosition,C extends ICondition, R extends IRobot> {
    P getShapePosition();
    C getShapeCondition();
    boolean checkCollision(R Robot);

}
