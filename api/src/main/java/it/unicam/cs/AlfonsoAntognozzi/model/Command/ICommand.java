package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;

public interface ICommand <R extends IRobot> {

    void Apply (R RobotApplied);


}
