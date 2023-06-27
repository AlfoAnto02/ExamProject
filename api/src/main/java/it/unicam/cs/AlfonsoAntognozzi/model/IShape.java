package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

public interface IShape <C extends Condition, P extends Position, R extends Robot> {
    P getShapePosition();
    C getShapeCondition();
    boolean checkCollision(R Robot);

}
