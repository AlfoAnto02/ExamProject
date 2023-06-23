package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class StopCommand implements ICommand{
    @Override
    public void Apply(Robot RobotApplyed) {
        RobotApplyed.getListOfCommands().clear();
    }
}
