package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * This class implements the ICommand interface and its task is to apply the followLabelCommand.
 *
 * @param <R> type of robot
 */
public class FollowLabelCommand <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    private final double[] args;
    private final List<R> robotList;
    private final String label;
    private final double speed;

    /**
     * This is the constructor of the class
     *
     * @param label Label that the followed robots should signal
     * @param args arguments of the followLabelCommand
     * @param robotList current list of robot in the Environment
     */
    public FollowLabelCommand(String label, double[] args, List<R> robotList){
        if(robotList.isEmpty()) throw new IllegalArgumentException("The robotList passed is empty");
        this.args = args;
        this.robotList = robotList;
        this.label = label;
        this.speed = args[1];
    }

    /**
     * tempRobList is the list of robots that are actually signaling a given label.
     * In case the robots that are signaling the label are < 2 then the method will call the private
     * callFollowRandomCommand() method that will generate a random position between (dist,-dist) (dist,-dist)
     * The logic behind this is that (as it is written in the schematics of the project) it is impossible to calculate
     * the avg position of the robots that "dist" (where dist is a variable indicating the distance between a robot and
     * another robot) meters apart if only 1 robot is present.
     * Otherwise, the method will continue calculating the avg x and y and eliminating the robots that aren't "dist" meters apart.
     *
     * @param robotApplied robot that has to apply the command
     */
    @Override
    public void apply(R robotApplied) {
        if(robotApplied==null) throw new NullPointerException("The robot passed is null");
        List<R> tempRobList = robotList.stream()
                .filter(robot -> robot.getRobotCondition() != null && robot.getRobotCondition().equals(new Condition(label)))
                .collect(Collectors.toList());
        if (tempRobList.size() <= 1) {
            this.callFollowRandomCommand(robotApplied);
            return;
        }
        double avgX = tempRobList.stream().mapToDouble(robot->robot.getRobotPosition().getX()).sum();
        double avgY = tempRobList.stream().mapToDouble(robot->robot.getRobotPosition().getY()).sum();
        List<R> toRemove = tempRobList.stream()
                .filter(robot -> !robot.checkDistanceBetweenRobot(tempRobList, args[0]))
                .toList();
        tempRobList.removeAll(toRemove);
        if(tempRobList.size() > 1) this.callFollowCommand(new Position(avgX/tempRobList.size(), avgY/tempRobList.size()), robotApplied);
        else this.callFollowRandomCommand(robotApplied);
    }

    /**
     * Method used to move the robot to the new Position calculated
     *
     * @param position The avg position of robot Signaling the label and "dist" meters apart
     * @param robotApplied robot that has to apply the command
     */

    private void callFollowCommand(Position position, R robotApplied) {
        double deltaX = position.getX() * this.speed;
        double deltaY = position.getY() * this.speed;
        robotApplied.setRobotPosition(deltaX+ robotApplied.getRobotPosition().getX(), deltaY+ robotApplied.getRobotPosition().getY());
        }

    /**
     * Method used to move the robot in a random location if the robot that are signaling the label and are "dist" meters apart are <2
     *
     * @param RobotApplied robot that has to apply the command
     */


    private void callFollowRandomCommand(R RobotApplied){
            Random random = new Random();
            double x1 = args[0];
            double x2 = -args[0];
            double y1 = args[0];
            double y2 = -args[0];
            double targetX = x1+ (x2 - x1) * random.nextDouble();
            double targetY = y1 + (y2 - y1) * random.nextDouble();
            double deltaX = Math.signum(targetX - RobotApplied.getRobotPosition().getX()) * this.speed;
            double deltaY = Math.signum(targetY - RobotApplied.getRobotPosition().getY()) * this.speed;
            RobotApplied.setRobotPosition(deltaX + RobotApplied.getRobotPosition().getX(),deltaY + RobotApplied.getRobotPosition().getY());
        }
}


