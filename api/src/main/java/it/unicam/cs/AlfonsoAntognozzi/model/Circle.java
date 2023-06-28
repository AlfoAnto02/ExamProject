package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

public class Circle<P extends Position, C extends Condition> extends AbstracShape{
    private final double radius;
    public Circle(P position, C condition, double r) {
        super(position, condition);
        if(r < 0) throw new IllegalArgumentException("radius must be > 0");
        this.radius = r;
    }

    public boolean checkCollision(Robot R) {
        double distanceBetweenObject = Math.sqrt(Math.pow(R.getRobotPosition().getX()-this.getShapePosition().getX(),2) +
                Math.pow(R.getRobotPosition().getY()-this.getShapePosition().getY(),2));
        return distanceBetweenObject <= this.getRadius();
    }

    public double getRadius(){
        return this.radius;
    }
}

