package it.unicam.cs.AlfonsoAntognozzi.model;


import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;

import java.util.List;

public interface IController <C extends ICommand, R extends IRobot> {

    List<C> getCommandList();

    int getProgramCounter();

    void setProgramCounter(int n);

    void skipUntilInstruction();

    void addCommand(C Command);

    boolean hasNextIstruction();

    public void Consume (R Robot);

    




}
