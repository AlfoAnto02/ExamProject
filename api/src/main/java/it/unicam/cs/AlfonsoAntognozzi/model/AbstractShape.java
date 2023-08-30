package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;


import java.util.Objects;

/**
 * This abstract class implements the IShape interface and its task is to enclose the generic behaviors of any type of
 * figure
 *
 * @param <P> Position of the shape
 * @param <C> Condition of the shape
 * @param <R> type of robot
 */
public abstract  class AbstractShape<P extends IPosition, C extends ICondition,R extends IRobot<P,C>> implements IShape<P,C,R> {
    private final P shapePosition;
    private final C shapeCondition;

    /**
     * This is the constructor of the abstract class
     *
     * @param position position set for the shape
     * @param condition condition set for the shape
     */
    public AbstractShape(P position, C condition){
        if(position == null || condition == null) throw new NullPointerException("Wrong Position or Condition");
        this.shapePosition = position;
        this.shapeCondition = condition;
    }

    /**
     * Method used to get the position of the shape
     *
     * @return the position of the shape
     */
    public P  getShapePosition (){
        return this.shapePosition;
    }

    /**
     * Method used to get the condition of the shape
     *
     * @return the condition of the shape
     */
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
