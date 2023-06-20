package progetto;

public class Robot implements IRobot{
    private Position RobotPosition;

    private Condition RobotCondition;

    private double RobotSpeed;

    public Robot (Position P, double RobotSpeed){
        this.RobotPosition=P;
        this.RobotCondition=null;
        this.RobotSpeed=RobotSpeed;
    }

    public Position getRobotPosition(){
        return this.RobotPosition;
    }

    public Condition getRobotCondition(){
        return this.RobotCondition;
    }

    public void setRobotCondition(Condition c){
        this.RobotCondition=c;
    }

    public double getRobotSpeed(){
        return this.RobotSpeed;
    }

    public void setRobotPosition(double x, double y) {
        this.RobotPosition.setY(y);
        this.RobotPosition.setX(x);
    }


}
