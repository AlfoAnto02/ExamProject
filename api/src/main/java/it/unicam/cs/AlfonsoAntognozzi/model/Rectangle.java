package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
public class Rectangle <P extends Position, C extends Condition> extends AbstracShape {
    private final double height;
    private final double width;
    public Rectangle(P position, C condition, double height, double width) {
        super(position, condition);
        if (height < 0 || width < 0) throw new IllegalArgumentException("Height and Width of the Rectangle must be >0");
        this.height=height;
        this.width=width;
    }
    public boolean checkCollision(IRobot R){
        if((R.getRobotPosition().getX()>= this.getShapePosition().getX()-this.width/2) && (R.getRobotPosition().getX()<= this.getShapePosition().getX()+this.width/2) &&
        (R.getRobotPosition().getY()>= this.getShapePosition().getY()-this.height/2)&&(R.getRobotPosition().getY()<= this.getShapePosition().getY()+this.height/2)) return true;
        else return false;
    }
    public double getHeight(){
        return this.height;
    }

    public double getWidth(){
        return this.width;
    }
}