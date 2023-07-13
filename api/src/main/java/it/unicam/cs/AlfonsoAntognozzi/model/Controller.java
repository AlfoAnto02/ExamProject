package it.unicam.cs.AlfonsoAntognozzi.model;

import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.DoneCommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.RepeatCommand;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.UntilCommand;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Controller <C extends ICommand<R>, R extends IRobot<IPosition, ICondition>> implements IController <C,R>{
    private final List<C> commandList;
    private int programCounter;
    public Controller(){
        this.commandList=new ArrayList<>();
        this.programCounter=0;
    }
    public void addCommand(C Command){
        this.getCommandList().add(Command);
    }
    public void increaseProgramCounter(){
        this.programCounter++;
    }

    public void consume(R robot){
        this.commandList.get(programCounter).apply(robot);
        this.increaseProgramCounter();
    }


    public void skipUntilInstruction() {
        List<C> sublist = this.commandList.subList(this.programCounter,this.commandList.size());
        Stack<C> stack = new Stack<>();
        this.programCounter--;
        for (C command : sublist) {
            if(command instanceof UntilCommand || command instanceof RepeatCommand) stack.push(command);
            else if(command instanceof DoneCommand) stack.pop();
            if(stack.isEmpty()) break;
            this.programCounter++;
        }

    }

    public boolean hasNextInstruction(){
        return this.programCounter < this.commandList.size();
    }

    public List<C> getCommandList() {
        return this.commandList;
    }

    @Override
    public int getProgramCounter() {
        return this.programCounter;
    }

    @Override
    public void setProgramCounter(int newProgramCounter) {
        this.programCounter=newProgramCounter;
    }

}
