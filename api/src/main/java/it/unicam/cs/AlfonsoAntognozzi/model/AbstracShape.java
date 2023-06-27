package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

import java.util.Objects;

public abstract  class AbstracShape <P extends Position, C extends Condition> implements IShape {
    private final P shapePosition;
    private final C shapeCondition;

    public AbstracShape (P position, C condition){

        this.shapePosition = position;

        this.shapeCondition = condition;
    }

    public P  getShapePosition (){
        return this.shapePosition;
    }
    public C getShapeCondition(){
        return this.shapeCondition;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstracShape<?, ?> that = (AbstracShape<?, ?>) o;
        return Objects.equals(shapeCondition, that.shapeCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shapeCondition);
    }
}
