package it.unicam.cs.AlfonsoAntognozzi.model;
import java.util.Objects;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.*;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserHandler;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/***
 * This class implements the FollowMeParserHandler Interface and his job is to load each Robot_Controller (present in the
 * robot list of the game environment) with the parsed instructions.
 * @param <R> type of robot
 * @param <S> type of shape
 */
public class Handler <R extends IRobot<IPosition,ICondition>, S extends IShape<IPosition,ICondition,IRobot<IPosition,ICondition>>> implements FollowMeParserHandler<R> {
    private final Environment <R,S> gameEnvironment;
    public Handler(Environment<R,S> gameEnvironment){
        if(gameEnvironment==null) throw new NullPointerException("You can't handle a null game environment");
        this.gameEnvironment = gameEnvironment;
    }

    @Override
    public void parsingStarted() {
        this.gameEnvironment.getRobotList().forEach(robot -> {
            robot.getRobotController().getCommandList().clear();
            robot.getRobotController().setProgramCounter(0);
        });
    }

    @Override
    public void parsingDone() {
        System.out.println("Commands have been parsed");
    }

    @Override
    public void moveCommand(double[] args) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new MoveCommand<>(args)));
    }

    @Override
    public void moveRandomCommand(double[] args) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new MoveRandomCommand<>(args)));
    }

    @Override
    public void signalCommand(String label) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new SignalCommand<>(new Condition(label))));
    }


    @Override
    public void unsignalCommand(String label) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new UnSignalCommand<>(new Condition(label))));
    }

    @Override
    public void followCommand(String label, double[] args) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new FollowLabelCommand(label, args, this.gameEnvironment.getRobotList())));
    }


    @Override
    public void stopCommand() {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new StopCommand<>()));
    }

    @Override
    public void continueCommand(int s) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new ContinueCommand<>(s)));
    }

    @Override
    public void repeatCommandStart(int n) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new RepeatCommand<>(n)));
    }

    @Override
    public void untilCommandStart(String label) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new UntilCommand<>(gameEnvironment.getShapeList(),label)));
    }



    @Override
    public void doForeverStart() {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new DoForeverCommand<>()));
    }


    @Override
    public void doneCommand() {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new DoneCommand<>()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Handler<?,?> handler = (Handler<?,?>) o;
        return Objects.equals(gameEnvironment, handler.gameEnvironment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameEnvironment);
    }
}