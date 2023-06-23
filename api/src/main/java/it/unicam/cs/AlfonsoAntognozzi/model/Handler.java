package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;
import java.util.stream.Collectors;

import it.unicam.cs.AlfonsoAntognozzi.model.Command.*;

public class Handler implements IHandler{
    private final Environment gameEnvironment;

    public Handler(Environment gameEnvironment){

        this.gameEnvironment = gameEnvironment;

    }

    public Environment getGameEnvironment() {
        return gameEnvironment;
    }

    @Override
    public void parsingStarted() {

    }

    @Override
    public void parsingDone() {
        for(IRobot R : this.gameEnvironment.robotList()){
            while(R.hasNextIstruction()){
                R.Consume();
            }
        }
    }

    @Override
    public void moveCommand(double[] args) {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getListOfCommands().add(new MoveCommand(args));
        }
    }

    @Override
    public void moveRandomCommand(double[] args) {
       for(IRobot R : this.gameEnvironment.robotList()){
           R.getListOfCommands().add(new MoveRandomCommand(args));
       }
    }

    @Override
    public void signalCommand(String label) {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getListOfCommands().add(new SignalCommand(new Condition(label)));
        }
    }


    @Override
    public void unsignalCommand(String label) {
        for(IRobot R : this.gameEnvironment.robotList()){
             R.getListOfCommands().add(new UnSignalCommand());
        }
    }

    @Override
    public void followCommand(String label, double[] args) {

    }




    @Override
    public void stopCommand() {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getListOfCommands().add(new StopCommand());
        }
    }

    @Override
    public void continueCommand(int s) {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getListOfCommands().add(new ContinueCommand(s));
        }
    }

    @Override
    public void repeatCommandStart(int n) {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getListOfCommands().add(new repeatCommand(n));
        }
    }

    @Override
    public void untilCommandStart(String label) {
        List<IShape> checkedShapeList = this.gameEnvironment.shapeList()
                .stream()
                .filter(s -> s.getShapecondition().equals(new Condition(label)))
                .collect(Collectors.toList());
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getListOfCommands().add(new untilCommand(checkedShapeList));
        }
    }



    @Override
    public void doForeverStart() {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getListOfCommands().add(new doForeverCommand());
        }
    }

    @Override
    public void doneCommand() {
        for(IRobot R : this.gameEnvironment.robotList()){
            R.getListOfCommands().add(new doneCommand());
        }
    }
}
