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
    public void moveCommand(double[] args) {
        for(IRobot R : this.gameEnvironment.getRobotList()){
            R.getListOfCommands().add(new MoveCommand(args));
        }
    }

    @Override
    public void moveRandomCommand(double[] args) {
       for(IRobot R : this.gameEnvironment.getRobotList()){
           R.getListOfCommands().add(new MoveRandomCommand(args));
       }
    }

    @Override
    public void signalCommand(String label, Robot r) {
        for(IRobot R : this.gameEnvironment.getRobotList()){
            if (R.equals(r)) R.getListOfCommands().add(new SignalCommand(new Condition(label)));
        }
    }


    @Override
    public void unsignalCommand(String label, Robot r) {
        for(IRobot R : this.gameEnvironment.getRobotList()){
            if (R.equals(r)) R.getListOfCommands().add(new UnSignalCommand());
        }
    }

    @Override
    public void followCommand(String label, double[] args) {

    }


    @Override
    public void stopCommand() {
        for(IRobot R : this.gameEnvironment.getRobotList()){
            R.getListOfCommands().add(new StopCommand());
        }
    }

    @Override
    public void continueCommand(int s) {
        for(IRobot R : this.gameEnvironment.getRobotList()){
            R.getListOfCommands().add(new ContinueCommand(s));
        }
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
