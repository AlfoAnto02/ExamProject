package it.unicam.cs.AlfonsoAntognozzi.model;


import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;

import java.util.List;

public interface IController <C extends ICommand, R extends IRobot<IPosition, ICondition>> {

    List<C> getCommandList();

    int getProgramCounter();

    void setProgramCounter(int n);

    void skipUntilInstruction();

    void addCommand(C Command);

    boolean hasNextIstruction();

    void Consume (R Robot);
}
