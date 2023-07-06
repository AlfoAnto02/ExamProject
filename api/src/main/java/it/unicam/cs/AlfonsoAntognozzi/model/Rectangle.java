package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

public class Rectangle <P extends IPosition, C extends ICondition, R extends IRobot> extends AbstractShape <P,C,R> {
    private final double height;
    private final double width;
    public Rectangle(P position, C condition, double height, double width) {
        super(position, condition);
        if (height < 0 || width < 0) throw new IllegalArgumentException("Height and Width of the Rectangle must be >0");
        this.height=height;
        this.width=width;
    }
    public boolean checkCollision(R Robot){
        return (Robot.getRobotPosition().getX() >= this.getShapePosition().getX() - this.getWidth()/ 2) && (Robot.getRobotPosition().getX() <= this.getShapePosition().getX() + this.getWidth() / 2) &&
                (Robot.getRobotPosition().getY() >= this.getShapePosition().getY() - this.getHeight() / 2) && (Robot.getRobotPosition().getY() <= this.getShapePosition().getY() + this.getHeight() / 2);
    }
    public double getHeight(){
        return this.height;
    }

    public double getWidth(){
        return this.width;
    }
}
