package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;


public class StopCommand <R extends IRobot> implements ICommand <R>{
    @Override
    public void Apply(R RobotApplied) {
        RobotApplied.getRobotController().getCommandList().clear();
    }
}
