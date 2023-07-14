package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

import java.util.Objects;
/***
 * This class extends the Abstract class AbstractShape and its task is to represent the figure of the Rectangle
 * @param <P> Position of the circle
 * @param <C> Condition of the circle
 * @param <R> type of robot
 */
public class Rectangle <P extends IPosition, C extends ICondition, R extends IRobot<P,C>> extends AbstractShape <P,C,R> {
    private final double height;
    private final double width;
    public Rectangle(P position, C condition, double height, double width) {
        super(position, condition);
        if (height < 0 || width < 0) throw new IllegalArgumentException("Height and Width of the Rectangle must be >0");
        this.height=height;
        this.width=width;
    }
    public boolean checkCollision(R robot){
        if(robot==null) throw new NullPointerException("The robot passed is null");
        return (robot.getRobotPosition().getX() >= this.getShapePosition().getX() - this.getWidth()/ 2) && (robot.getRobotPosition().getX() <= this.getShapePosition().getX() + this.getWidth() / 2) &&
                (robot.getRobotPosition().getY() >= this.getShapePosition().getY() - this.getHeight() / 2) && (robot.getRobotPosition().getY() <= this.getShapePosition().getY() + this.getHeight() / 2);
    }
    public double getHeight(){
        return this.height;
    }

    public double getWidth(){
        return this.width;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangle<?, ?, ?> rectangle = (Rectangle<?, ?, ?>) o;
        return Double.compare(rectangle.getHeight(), this.height) == 0 && Double.compare(rectangle.getWidth(), this.width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height, width);
    }
}
