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

/**
 * This class implements the IController interface and its job is to manage the controller of a specific robot.
 *
 * @param <C> Command that has to be added in the command list
 * @param <R> type of robot
 */
public class Controller <C extends ICommand<R>, R extends IRobot<IPosition, ICondition>> implements IController <C,R>{
    private final List<C> commandList;
    private int programCounter;
    public Controller(){
        this.commandList=new ArrayList<>();
        this.programCounter=0;
    }

    /**
     * This method is used to add a specific command in the commandList
     *
     * @param command new command to add to the commandList
     */
    public void addCommand(C command){
        if(command==null) throw new NullPointerException("You can't add a null command");
        this.getCommandList().add(command);
    }
    public void increaseProgramCounter(){
        this.programCounter++;
    }

    /**
     * This method is used to consume the next command of a specific Robot.
     *
     * @param robot robot that need to apply the command
     */

    public void consume(R robot){
        if(robot == null) throw new NullPointerException("Robot passed is null");
        this.commandList.get(programCounter).apply(robot);
        this.increaseProgramCounter();
    }

    /**
     * This method is used to skip the instruction of a loop, in case the loop does not need to be repeated.
     */
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

    /**
     * This method is used to check if the commandList has another commands to be consumed.
     *
     * @return True if the controller has a next instruction to be consumed, false otherwise.
     */
    public boolean hasNextInstruction(){
        return this.programCounter < this.commandList.size();
    }

    /**
     * Method used to get the commandList of the Controller
     *
     * @return the commandList of the controller.
     */
    public List<C> getCommandList() {
        return this.commandList;
    }

    /**
     * Method used to get the programCounter of the Controller. The programCounter's task is to save at which step of the commandList
     * the controller is applying the command to the robot.
     *
     * @return the value of the programCounter
     */

    @Override
    public int getProgramCounter() {
        return this.programCounter;
    }

    /**
     * Method used to set a new programCounter.
     *
     * @param newProgramCounter new ProgramCounter value to set
     */
    @Override
    public void setProgramCounter(int newProgramCounter) {
        if(newProgramCounter<-1) throw new IllegalArgumentException("You can't set a negative program counter");
        this.programCounter=newProgramCounter;
    }

}
