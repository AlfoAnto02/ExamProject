package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.*;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserHandler;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

public class Handler <R extends IRobot<IPosition,ICondition>, S extends IShape<IPosition,ICondition,R>,C extends ICommand<R>> implements FollowMeParserHandler<R> {
    private final Environment <R,S> gameEnvironment;
    public Handler(Environment<R,S> gameEnvironment){
        this.gameEnvironment = gameEnvironment;
    }

    @Override
    public void parsingStarted() {
        this.gameEnvironment.robotList().forEach(robot -> {
            robot.getRobotController().getCommandList().clear();
            robot.getRobotController().setProgramCounter(0);
        });
    }

    @Override
    public void parsingDone() {

    }

    @Override
    public void moveCommand(double[] args) {
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new MoveCommand<>(args)));
    }

    @Override
    public void moveRandomCommand(double[] args) {
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new MoveRandomCommand<>(args)));
    }

    @Override
    public void signalCommand(String label) {
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new SignalCommand<>(new Condition(label))));
    }


    @Override
    public void unsignalCommand(String label) {
        List<R> tempRobList = this.gameEnvironment.robotList()
                .stream()
                .filter(r -> r.getRobotCondition().equals(new Condition(label)))
                .toList();
        tempRobList.forEach(robot -> robot.getRobotController().addCommand(new UnSignalCommand<>()));
    }

    @Override
    public void followCommand(String label, double[] args) {
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new FollowLabelCommand(label, args, this.gameEnvironment.robotList())));
    }


    @Override
    public void stopCommand() {
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new StopCommand<>()));
    }

    @Override
    public void continueCommand(int s) {
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new ContinueCommand<>(s)));
    }

    @Override
    public void repeatCommandStart(int n) {
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new RepeatCommand<>(n)));
    }

    @Override
    public void untilCommandStart(String label) {
        List<S> checkedShapeList = this.gameEnvironment.shapeList()
                .stream()
                .filter(s -> s.getShapeCondition().equals(new Condition(label)))
                .collect(Collectors.toList());
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new UntilCommand(checkedShapeList)));
    }



    @Override
    public void doForeverStart() {
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new DoForeverCommand<>()));
    }


    @Override
    public void doneCommand() {
        this.gameEnvironment.robotList().forEach(robot -> robot.getRobotController().addCommand(new DoneCommand<>()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Handler<?,?,?> handler = (Handler<?,?,?>) o;
        return Objects.equals(gameEnvironment, handler.gameEnvironment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameEnvironment);
    }
}