package it.unicam.cs.AlfonsoAntognozzi.model;

import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;

import java.util.ArrayList;
import java.util.List;

public class Controller implements IController{

        private int ProgramCounter;

        private List<ICommand> commandList = new ArrayList<ICommand>();

        public Controller(){
            this.ProgramCounter=0;
        }

    @Override
    public int getProgrammCounter() {
        return ProgramCounter;
    }

    public void incrementProgramCounter(){
            this.ProgramCounter++;
    }

    public void Consume(Robot R){
            this.getListOfCommands().get(getProgrammCounter()).Apply(R);
            this.incrementProgramCounter();
    }

    public boolean hasNextIstruction(){
            if(this.getProgrammCounter()<this.getListOfCommands().size()) return true;
            return false;
    }
    @Override
    public List<ICommand> getListOfCommands() {
        return this.commandList;
    }

    public void setProgramCounter(int programCounter) {
        ProgramCounter = programCounter;
    }

    public void addCommandToController(ICommand I) { this.commandList.add(I);}

    public void addListOfCommandsToController(List<ICommand> ListI){
        for(ICommand I : ListI){
            this.commandList.add(I);
        }
    }
}
