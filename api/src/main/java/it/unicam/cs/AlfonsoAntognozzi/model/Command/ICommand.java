package it.unicam.cs.AlfonsoAntognozzi.model.Command;

/***
 * This interface is used to compute and apply a command of a given robot.
 *
 * @param <R> type of Robot
 */
public interface ICommand <R> {

    void apply(R robotApplied);


}
