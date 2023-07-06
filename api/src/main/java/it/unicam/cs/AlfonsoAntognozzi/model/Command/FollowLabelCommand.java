package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class FollowLabelCommand <R extends IRobot> implements ICommand <R>{
    private final double[] args;
    private final List<R> robotList;
    private final String label;
    private final double speed;

    public FollowLabelCommand(String label, double[] args, List<R> robotList){
        this.args = args;
        this.robotList=robotList;
        this.label=label;
        this.speed=args[1];
    }

    @Override
    public void Apply(R RobotApplied) {
        List<R> tempRobList = robotList.stream()
                .filter(robot -> robot.getRobotCondition() != null && robot.getRobotCondition().equals(new Condition(label)))
                .collect(Collectors.toList());
        if (tempRobList.size()<=1) {
            this.callFollowRandomCommand(RobotApplied);
            return;
        }
        double avgX = tempRobList.stream().mapToDouble(robot->robot.getRobotPosition().getX()).sum();
        double avgY = tempRobList.stream().mapToDouble(robot->robot.getRobotPosition().getY()).sum();
        List<R> toRemove = tempRobList.stream()
                .filter(robot -> !robot.checkDistanceBetweenRobot(tempRobList, args[0]))
                .collect(Collectors.toList());
        tempRobList.removeAll(toRemove);
        if(tempRobList.size()>1) this.callFollowCommand(new Position(avgX/tempRobList.size(), avgY/tempRobList.size()), RobotApplied);
        else this.callFollowRandomCommand(RobotApplied);
        tempRobList.clear(); toRemove.clear();
    }

    private void callFollowCommand(Position position, R RobotApplied) {
        double x = position.getX();
        double y = position.getY();
        double deltaX = x*this.speed;
        double deltaY = y*this.speed;
        RobotApplied.setRobotPosition(deltaX+ RobotApplied.getRobotPosition().getX(), deltaY+ RobotApplied.getRobotPosition().getY());
        }

        private void callFollowRandomCommand(R RobotApplied){
            Random random = new Random();
            double x1 = args[0];
            double x2 = -args[0];
            double y1 = args[0];
            double y2 = -args[0];
            double targetX = x1+ (x2-x1) * random.nextDouble();
            double targetY = y1 + (y2-y1) * random.nextDouble();
            double deltaX = Math.signum(targetX- RobotApplied.getRobotPosition().getX()) * this.speed;
            double deltaY = Math.signum(targetY- RobotApplied.getRobotPosition().getY()) * this.speed;
            RobotApplied.setRobotPosition(deltaX+ RobotApplied.getRobotPosition().getX(),deltaY+ RobotApplied.getRobotPosition().getY());
        }
}


