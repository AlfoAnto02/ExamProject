package it.unicam.cs.AlfonsoAntognozzi.model;

import it.unicam.cs.AlfonsoAntognozzi.model.Command.ICommand;

import java.util.ArrayList;
import java.util.List;

public interface IController {


    public int getProgrammCounter();

    public List<ICommand> getListOfCommands();

    public void Consume(Robot R);

    public boolean hasNextIstruction();

    public void setProgramCounter(int programCounter);
}
