package it.unicam.cs.AlfonsoAntognozzi.model;
import java.util.Objects;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.*;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserHandler;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

/**
 * This class implements the FollowMeParserHandler Interface and his job is to load each Robot_Controller (present in the
 * robot list of the game environment) with the parsed instructions.
 *
 * @param <R> type of robot
 * @param <S> type of shape
 */
public class Handler <R extends IRobot<IPosition,ICondition>, S extends IShape<IPosition,ICondition,IRobot<IPosition,ICondition>>> implements FollowMeParserHandler<R> {
    private final Environment <R,S> gameEnvironment;

    /**
     * This is the constructor of the class.
     *
     * @param gameEnvironment Environment of the simulation
     */
    public Handler(Environment<R,S> gameEnvironment){
        if(gameEnvironment==null) throw new NullPointerException("You can't handle a null game environment");
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Method used to clear the commandList if a new commandList will be passed and handled.
     */
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

    /**
     * Method used to add a moveCommand to each controller of each robot in the environment
     *
     * @param args arguments of the command
     */
    @Override
    public void moveCommand(double[] args) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new MoveCommand<>(args)));
    }

    /**
     * Method used to add a moveRandomCommand to each controller of each robot in the environment
     *
     * @param args arguments of the command
     */
    @Override
    public void moveRandomCommand(double[] args) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new MoveRandomCommand<>(args)));
    }

    /**
     * Method used to add a signalCommand to each controller of each robot in the environment
     *
     * @param label label that has to be signaled
     */
    @Override
    public void signalCommand(String label) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new SignalCommand<>(new Condition(label))));
    }

    /**
     * Method used to add an unsignalCommand to each controller of each robot in the environment
     *
     * @param label label that has to be signaled
     */
    @Override
    public void unsignalCommand(String label) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new UnSignalCommand<>(new Condition(label))));
    }

    /**
     * Method used to add a followCommand to each controller of each robot in the environment
     *
     * @param label label to follow
     * @param args  command arguments
     */
    @Override
    public void followCommand(String label, double[] args) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new FollowLabelCommand(label, args, this.gameEnvironment.getRobotList())));
    }

    /**
     * Method used to add a stopCommand to each controller of each robot in the environment
     */
    @Override
    public void stopCommand() {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new StopCommand<>()));
    }

    /**
     * Method used to add a continueCommand to each controller of each robot in the environment
     *
     * @param s seconds that the last command has to be repeated
     */
    @Override
    public void continueCommand(int s) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new ContinueCommand<>(s)));
    }

    /**
     * Method used to add a repeatCommandStart to each controller of each robot in the environment
     *
     * @param n times that the set of instruction need to be repeated
     */
    @Override
    public void repeatCommandStart(int n) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new RepeatCommand<>(n)));
    }

    /**
     * Method used to add a untilCommandStart to each controller of each robot in the environment
     *
     * @param label label that the robots need to recognize in order to repeat the next set of instruction.
     */
    @Override
    public void untilCommandStart(String label) {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new UntilCommand<>(gameEnvironment.getShapeList(),label)));
    }

    /**
     * Method used to add a doForeverStart to each controller of each robot in the environment
     */
    @Override
    public void doForeverStart() {
        this.gameEnvironment.getRobotList().forEach(robot -> robot.getRobotController().addCommand(new DoForeverCommand<>()));
    }

    /**
     * Method used to add a doneCommand to each controller of each robot in the environment
     */
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