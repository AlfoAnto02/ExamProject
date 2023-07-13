package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
public class Circle<P extends IPosition, C extends ICondition, R extends IRobot<P,C>> extends AbstractShape <P,C,R>{
    private final double radius;
    public Circle(P position, C condition, double radius) {
        super(position, condition);
        if(radius < 0) throw new IllegalArgumentException("radius must be > 0");
        this.radius = radius;
    }

    public boolean checkCollision(R robot) {
        double distanceBetweenObject = Math.sqrt(Math.pow(robot.getRobotPosition().getX() - this.getShapePosition().getX(),2) +
                Math.pow(robot.getRobotPosition().getY() - this.getShapePosition().getY(),2));
        return distanceBetweenObject <= this.getRadius();
    }

    public double getRadius(){
        return this.radius;
    }
}

