package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;


import java.util.Objects;

public abstract  class AbstractShape<P extends IPosition, C extends ICondition,R extends IRobot> implements IShape<P,C,R> {
    private final P shapePosition;
    private final C shapeCondition;

    public AbstractShape(P position, C condition){
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
        AbstractShape<?, ? , ?> that = (AbstractShape<?, ? , ?>) o;
        return Objects.equals(shapeCondition, that.shapeCondition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shapeCondition);
    }
}
