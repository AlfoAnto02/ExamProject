package progetto;

public class Rectangle extends AbstracShape {
    private final double height;

    private final double width;
    public Rectangle(Position p, Condition S, double height, double width) {
        super(p, S);
        if (height < 0 || width < 0) throw new IllegalArgumentException("Height and Width of the Rectangle must be >0");
        this.height=height;
        this.width=width;
    }
    public boolean checkCollision(Robot R){
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
