package progetto;

public class Circle<P extends Position, C extends Condition> extends AbstracShape{

    private final double radius;
    public Circle(P position, C Condition, double r) {
        super(position, Condition);
        if(r < 0) throw new IllegalArgumentException("radius must be > 0");
        this.radius = r;
    }

    public double getRadius(){
        return this.radius;
    }


    public boolean checkCollision(IRobot R) {
        double distanceBetweenObject = Math.sqrt(Math.pow(R.getRobotPosition().getX()-this.getShapePosition().getX(),2) +
                Math.pow(R.getRobotPosition().getY()-this.getShapePosition().getY(),2));
        if (distanceBetweenObject <= this.radius) return true;
        else return false;
    }
}

