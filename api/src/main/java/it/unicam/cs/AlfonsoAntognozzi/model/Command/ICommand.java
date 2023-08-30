package it.unicam.cs.AlfonsoAntognozzi.model.Command;

/**
 * This interface is used to compute and apply a command of a given robot.
 *
 * @param <R> type of Robot
 */
public interface ICommand <R> {

    /**
     * Method used to apply a command to a robot
     *
     * @param robotApplied robot that has to apply the command
     */
    void apply(R robotApplied);


}
