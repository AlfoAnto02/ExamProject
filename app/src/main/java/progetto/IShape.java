package progetto;

public interface IShape {
    Position getShapePosition();
    Condition  getShapecondition();

    void setShapePosition(Position p);

    boolean checkCollision(Robot R);

}
