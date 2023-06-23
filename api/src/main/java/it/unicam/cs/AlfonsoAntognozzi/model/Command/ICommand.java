package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public interface ICommand <R extends Robot> {

    void Apply (R RobotApplyed);



}
