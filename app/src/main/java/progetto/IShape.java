package progetto;

public interface IShape <C extends Condition, P extends Position> {
    P getShapePosition();
    C  getShapecondition();
    void setShapePosition(P position);
    boolean checkCollision(IRobot R);

}
