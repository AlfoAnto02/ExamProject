package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstracShape<?, ?> that = (AbstracShape<?, ?>) o;
        return Objects.equals(Shapecondition, that.Shapecondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Shapecondition);
    }
}
