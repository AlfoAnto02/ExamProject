package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

import java.util.Objects;

/**
 * This class extends the Abstract class AbstractShape and its task is to represent the figure of the circle
 *
 * @param <P> Position of the circle
 * @param <C> Condition of the circle
 * @param <R> type of robot
 */
public class Circle<P extends IPosition, C extends ICondition, R extends IRobot<P,C>> extends AbstractShape <P,C,R>{
    private final double radius;

    /**
     * This is the constructor of the class
     *
     * @param position position set for the circle
     * @param condition condition set for the circle
     * @param radius radius set for the circle
     */
    public Circle(P position, C condition, double radius) {
        super(position, condition);
        if(radius < 0) throw new IllegalArgumentException("Radius must be > 0");
        this.radius = radius;
    }

    /**
     * This method is used to check if the given robot collides with circle.
     *
     * @param robot robot Passed
     * @return True if the robot collides, false otherwise.
     */
    public boolean checkCollision(R robot) {
        if(robot==null) throw new NullPointerException("Robot is null");
        double distanceBetweenObject = Math.sqrt(Math.pow(robot.getRobotPosition().getX() - this.getShapePosition().getX(),2) +
                Math.pow(robot.getRobotPosition().getY() - this.getShapePosition().getY(),2));
        return distanceBetweenObject <= this.getRadius();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Circle<?, ?, ?> circle = (Circle<?, ?, ?>) o;
        return Double.compare(circle.radius, radius) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), radius);
    }

    public double getRadius(){
        return this.radius;
    }
}

