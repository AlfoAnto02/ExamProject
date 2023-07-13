package it.unicam.cs.AlfonsoAntognozzi.model;


public interface IShape <P,C,R> {
    P getShapePosition();
    C getShapeCondition();
    boolean checkCollision(R robot);

}
