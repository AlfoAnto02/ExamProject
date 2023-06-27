package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.*;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserHandler;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

public class Handler implements FollowMeParserHandler {
    private final Environment gameEnvironment;


    public Handler(Environment gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    @Override
    public void parsingStarted() {

    }

    @Override
    public void parsingDone() {
        while(this.gameEnvironment.hasNextInstruction()){
            for(IRobot R : this.gameEnvironment.robotList()){
                if(R.getRobotController().hasNextIstruction()) R.Consume();
            }
        }
    }

    @Override
    public void moveCommand(double[] args) {
        for(IRobot R : this.gameEnvironment.robotList()) {
            R.getRobotController().addCommand(new MoveCommand(args));
        }
    }

    @Override
    public void moveRandomCommand(double[] args) {
        for(IRobot R : this.gameEnvironment.robotList()) {
            R.getRobotController().addCommand(new MoveRandomCommand(args));
        }
    }

    @Override
    public void signalCommand(String label) {
        for(IRobot R : this.gameEnvironment.robotList()) {
            R.getRobotController().addCommand(new SignalCommand(new Condition(label)));
        }

    }


    @Override
    public void unsignalCommand(String label) {
        List<IRobot> tempRobList = this.gameEnvironment.robotList()
                .stream()
                .filter(r -> r.getRobotCondition().equals(new Condition(label)))
                .toList();
        for(IRobot R : tempRobList) {
            R.getRobotController().addCommand(new UnSignalCommand());
        }
    }

    @Override
    public void followCommand(String label, double[] args) {
        List<IRobot> tempRobList = this.gameEnvironment.robotList()
                .stream()
                .filter(r->r.getRobotCondition().equals(new Condition(label)))
                .collect(Collectors.toList());
        double avgX=0; double avgY=0; double dist = args[0];
        for(IRobot R : tempRobList){
            if(!R.checkDistanceBetweenRobot(tempRobList, dist)) tempRobList.remove(R);
            else{
                avgX += R.getRobotPosition().getX();
                avgY += R.getRobotPosition().getY();
            }
        }
        if(!tempRobList.isEmpty()) {
            for(IRobot R : this.gameEnvironment.robotList()) {
                R.getRobotController().addCommand(new FollowLabelCommand(new Position(avgX, avgY), args));
            }
        }else{
            double [] val = new double [5];
            val[0] = -dist;val[1] = dist;val[2] = -dist;val[3] = dist;val[4] = args[0];
            for(IRobot R : this.gameEnvironment.robotList()) {
                R.getRobotController().addCommand(new MoveRandomCommand(val));
            }
        }


    }


    @Override
    public void stopCommand() {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getRobotController().addCommand(new StopCommand());
        }
    }

    @Override
    public void continueCommand(int s) {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getRobotController().addCommand(new ContinueCommand(s));
        }
    }

    @Override
    public void repeatCommandStart(int n) {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getRobotController().addCommand(new repeatCommand(n));
        }
    }

    @Override
    public void untilCommandStart(String label) {
        List<IShape> checkedShapeList = this.gameEnvironment.shapeList()
                .stream()
                .filter(s -> s.getShapeCondition().equals(new Condition(label)))
                .collect(Collectors.toList());
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getRobotController().addCommand(new untilCommand(checkedShapeList));
        }
    }



    @Override
    public void doForeverStart() {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getRobotController().addCommand(new doForeverCommand());
        }
    }

    @Override
    public void doneCommand() {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getRobotController().addCommand(new doneCommand());
        }
    }
    public Environment getGameEnvironment() {
        return gameEnvironment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Handler handler = (Handler) o;
        return Objects.equals(gameEnvironment, handler.gameEnvironment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameEnvironment);
    }
}