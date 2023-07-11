package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;


public class StopCommand <R extends IRobot<IPosition, ICondition>> implements ICommand <R>{
    @Override
    public void Apply(R RobotApplied) {
        RobotApplied.getRobotController().getCommandList().clear();
    }
}
