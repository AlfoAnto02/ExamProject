package progetto;

public class Robot implements IRobot{
    private Position RobotPosition;

    private Condition RobotCondition;

    private double RobotSpeed;

    public Robot (Position P, Condition C, double RobotSpeed){
        this.RobotPosition=P;
        this.RobotCondition=C;
        this.RobotSpeed=RobotSpeed;
    }

    public Position getRobotPosition(){
        return this.RobotPosition;
    }

    public Condition getRobotCondition(){
        return this.RobotCondition;
    }

    public double getRobotSpeed(){
        return this.RobotSpeed;
    }

    public void setRobotPosition(double x, double y) {
        this.RobotPosition.setY(y);
        this.RobotPosition.setX(x);
    }


}
