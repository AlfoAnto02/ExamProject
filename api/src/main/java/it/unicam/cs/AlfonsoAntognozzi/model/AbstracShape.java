package it.unicam.cs.AlfonsoAntognozzi.model;

import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

public abstract  class AbstracShape <P extends Position, C extends Condition> implements IShape {
    private P ShapePosition;
    private final C Shapecondition;

    public AbstracShape (P position, C Condition){
        this.ShapePosition = position;

        this.Shapecondition = Condition;
    }

    public P  getShapePosition (){
        return this.ShapePosition;
    }
    public C getShapecondition(){
        return this.Shapecondition;
    }

    public void setShapePosition(Position p){
        this.getShapePosition().setX(p.getX());
        this.getShapePosition().setY(p.getY());
    }


}
