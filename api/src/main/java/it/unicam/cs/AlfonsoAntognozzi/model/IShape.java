package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

public interface IShape <C extends Condition, P extends Position> {
    P getShapePosition();
    C  getShapecondition();
    void setShapePosition(P position);
    boolean checkCollision(IRobot R);

}
