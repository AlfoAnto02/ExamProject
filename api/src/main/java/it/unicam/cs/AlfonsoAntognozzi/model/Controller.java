package it.unicam.cs.AlfonsoAntognozzi.model;

import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.doneCommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.repeatCommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.untilCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Controller implements IController{

    private final List<ICommand> commandList;

    private int programCounter;

    public Controller(){
        this.commandList=new ArrayList<>();
        this.programCounter=0;
    }
    @Override
    public List<ICommand> getCommandList() {
        return this.commandList;
    }

    public void addCommand(ICommand C){
        this.getCommandList().add(C);
    }

    @Override
    public int getProgramCounter() {
        return this.programCounter;
    }

    @Override
    public void setProgramCounter(int n) {
        this.programCounter=n;
    }

    public void increaseProgramCounter(){
        this.programCounter++;
    }

    public void Consume (Robot R){
        this.commandList.get(programCounter).Apply(R);
        this.increaseProgramCounter();
    }


    public void skipUntilInstruction() {
        List<ICommand> sublist = this.commandList.subList(this.programCounter,this.commandList.size());
        Stack<ICommand> stack = new Stack<>();
        for (ICommand C : sublist) {
            if(C instanceof untilCommand || C instanceof repeatCommand) stack.push(C);
            else if(C instanceof doneCommand) stack.pop();
            if(stack.isEmpty()) break;
            this.programCounter++;
        }

    }

    public boolean hasNextIstruction(){
        if(this.programCounter<this.commandList.size()) return true;
        return false;
    }

}
