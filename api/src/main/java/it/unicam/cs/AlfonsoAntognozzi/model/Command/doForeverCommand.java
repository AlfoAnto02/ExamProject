package it.unicam.cs.AlfonsoAntognozzi.model.Command;

import it.unicam.cs.AlfonsoAntognozzi.model.Robot;

public class doForeverCommand  implements ICommand {
    @Override
    public void Apply(Robot RobotApplyed) {
        RobotApplyed.getLoopTracker().add(RobotApplyed.getProgrammCounter());
    }
}
