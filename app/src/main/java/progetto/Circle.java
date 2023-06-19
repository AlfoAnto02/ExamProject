package progetto;

public class Circle extends AbstracShape{

    private final double radius;
    public Circle(Position p, Condition S, double r) {
        super(p, S);
        if(r < 0) throw new IllegalArgumentException("radius must be > 0");
        this.radius = r;
    }

    public double getRadius(){
        return this.radius;
    }

    @Override
    public boolean checkCollision(Robot R) {
        double distanceBetweenObject = Math.sqrt(Math.pow(R.getRobotPosition().getX()-this.getShapePosition().getX(),2) +
                Math.pow(R.getRobotPosition().getY()-this.getShapePosition().getY(),2));
        if (distanceBetweenObject <= this.radius) return true;
        else return false;
    }
}

