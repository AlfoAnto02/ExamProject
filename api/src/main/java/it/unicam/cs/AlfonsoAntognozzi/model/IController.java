package it.unicam.cs.AlfonsoAntognozzi.model;


import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;

import java.util.List;

public interface IController <R extends ICommand> {

    List<R> getCommandList();

    int getProgramCounter();

    void setProgramCounter(int n);

    void skipUntilInstruction();

    void addCommand(R C);

    




}
