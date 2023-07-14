package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;

/***
 * This interface is used to represent the common behaviors of a robot controller.
 * @param <C> Command that has to be added in the command list
 * @param <R> Type of robot
 */
public interface IController <C,R> {

    List<C> getCommandList();

    int getProgramCounter();

    void setProgramCounter(int newProgramCounter);

    /*
    This method is used to skip the instruction of a loop, in case the loop does not need to be repeated.
     */
    void skipUntilInstruction();
    /*
    This method is used to add a specific command in the commandList
     */
    void addCommand(C Command);
    /*
    This method is used to check if the commandList has another commands to be consumed.
     */
    boolean hasNextInstruction();
    /*
    This method is used to consume the next command of a specific Robot.
     */
    void consume(R robot);
}
