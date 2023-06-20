package progetto;

import java.util.Random;

public class Handler implements IHandler{
    private final Environment gameEnvironment;

    public Handler(Environment gameEnvironment){

        this.gameEnvironment = gameEnvironment;

    }

    @Override
    public void parsingStarted() {

    }

    @Override
    public void parsingDone() {

    }

    @Override
    public void moveCommand(double x, double y, double s) {
        double deltaX = x * s;
        double deltaY = y * s;
        for(IRobot robot : this.gameEnvironment.getRobotList()){
            robot.getRobotPosition().setX(deltaX+robot.getRobotPosition().getX());
            robot.getRobotPosition().setY(deltaY+robot.getRobotPosition().getY());
        }
    }

    @Override
    public void moveRandomCommand(double x1, double x2, double y1, double y2, double s) {
        Random random = new Random();
        double targetX = x1+ (x2-x1) * random.nextDouble();
        double targetY = y1 + (y2-y1) * random.nextDouble();
        for(IRobot robot : this.gameEnvironment.getRobotList()){
            double deltaX = Math.signum(targetX-robot.getRobotPosition().getX()) * s;
            double deltaY = Math.signum(targetY-robot.getRobotPosition().getY()) * s;
            robot.getRobotPosition().setX(deltaX+robot.getRobotPosition().getX());
            robot.getRobotPosition().setY(deltaY+robot.getRobotPosition().getY());
        }
    }

    @Override
    public void signalCommand(String label, Robot r) {
        if(this.gameEnvironment.getRobotList().contains(r)){
            r.setRobotCondition(new Condition(label));
        }
    }

    @Override
    public void unsignalCommand(String label,  Robot r) {
        if(r.getRobotCondition().equals(new Condition(label))) r.setRobotCondition(null);
    }

    @Override
    public void followCommand(String label, double dist, double s) {

    }

    @Override
    public void stopCommand() {

    }

    @Override
    public void continueCommand(int s) {

    }

    @Override
    public void repeatCommandStart(int n) {

    }

    @Override
    public void untilCommandStart(String label) {

    }

    @Override
    public void doForeverStart() {

    }

    @Override
    public void doneCommand() {

    }
}
