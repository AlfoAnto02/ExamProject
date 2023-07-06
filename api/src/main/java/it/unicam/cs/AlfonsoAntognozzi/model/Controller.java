package it.unicam.cs.AlfonsoAntognozzi.model;

import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.DoneCommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.RepeatCommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.UntilCommand;

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

    public void addCommand(ICommand C){
        this.getCommandList().add(C);
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
        this.programCounter--;
        for (ICommand C : sublist) {
            if(C instanceof UntilCommand || C instanceof RepeatCommand) stack.push(C);
            else if(C instanceof DoneCommand) stack.pop();
            if(stack.isEmpty()) break;
            this.programCounter++;
        }

    }

    public boolean hasNextIstruction(){
        if(this.programCounter<this.commandList.size()) return true;
        return false;
    }

    public List<ICommand> getCommandList() {
        return this.commandList;
    }

    @Override
    public int getProgramCounter() {
        return this.programCounter;
    }

    @Override
    public void setProgramCounter(int n) {
        this.programCounter=n;
    }

}
