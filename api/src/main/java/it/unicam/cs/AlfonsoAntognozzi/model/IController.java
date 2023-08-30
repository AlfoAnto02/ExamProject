package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;

/**
 * This interface is used to represent the common behaviors of a robot controller.
 *
 * @param <C> Command that has to be added in the command list
 * @param <R> Type of robot
 */
public interface IController <C,R> {
    /**
     * Method used to get the commandList of the Controller
     *
     * @return the commandList of the controller.
     */
    List<C> getCommandList();

    /**
     * Method used to get the programCounter of the Controller. The programCounter's task is to save at which step of the commandList
     * the controller is applying the command to the robot.
     *
     * @return the value of the programCounter
     */
    int getProgramCounter();

    /**
     * Method used to set a new programCounter.
     *
     * @param newProgramCounter new ProgramCounter value to set
     */
    void setProgramCounter(int newProgramCounter);

    /**
     * This method is used to skip the instruction of a loop, in case the loop does not need to be repeated.
     */
    void skipUntilInstruction();

    /**
     * This method is used to add a specific command in the commandList
     *
     * @param Command new Command to add to the commandList
     */
    void addCommand(C Command);

    /**
     * This method is used to check if the commandList has another commands to be consumed.
     *
     * @return True if the controller has a next instruction to be consumed, false otherwise
     */
    boolean hasNextInstruction();

    /**
     * This method is used to consume the next command of a specific Robot.
     *
     * @param robot robot that has to apply the command
     */
    void consume(R robot);
}
