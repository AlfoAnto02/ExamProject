package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.Condition;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FollowLabelCommand implements ICommand{
    private final double[] args;
    private final List<IRobot> robotList;
    private final String label;
    private final double speed;

    public FollowLabelCommand(String label, double[] args, List<IRobot> robotList){
        this.args = args;
        this.robotList=robotList;
        this.label=label;
        this.speed=args[1];
    }

    @Override
    public void Apply(Robot RobotApplied) {
        List<IRobot> tempRobList = new ArrayList<>();
        List<IRobot> toRemove = new ArrayList<>();
        for(IRobot R : this.robotList){ if(R.getRobotCondition()!=null && R.getRobotCondition().equals(new Condition(label))) tempRobList.add(R); }
        if (tempRobList.size()<=1) this.callFollowRandomCommand(RobotApplied);
        double avgX = 0; double avgY = 0; double dist = args[0];
        for (IRobot R : tempRobList) {
            if (!R.checkDistanceBetweenRobot(tempRobList, dist)) toRemove.add(R);
            else {
                avgX += R.getRobotPosition().getX();
                avgY += R.getRobotPosition().getY();
            }
        }
        tempRobList.removeAll(toRemove);
        if(tempRobList.size()>1) this.callFollowCommand(new Position(avgX/tempRobList.size(), avgY/tempRobList.size()), RobotApplied);
        else this.callFollowRandomCommand(RobotApplied);
        tempRobList.clear(); toRemove.clear();
    }

    private void callFollowCommand(Position position, Robot RobotApplied) {
        double x = position.getX();
        double y = position.getY();
        double deltaX = x*this.speed;
        double deltaY = y*this.speed;
        RobotApplied.setRobotPosition(deltaX+ RobotApplied.getRobotPosition().getX(), deltaY+ RobotApplied.getRobotPosition().getY());
        }

        private void callFollowRandomCommand(Robot RobotApplied){
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


