package it.unicam.cs.AlfonsoAntognozzi.model;

import java.util.List;

public interface IController <C,R> {

    List<C> getCommandList();

    int getProgramCounter();

    void setProgramCounter(int newProgramCounter);

    void skipUntilInstruction();

    void addCommand(C Command);

    boolean hasNextInstruction();

    void consume(R robot);
}
