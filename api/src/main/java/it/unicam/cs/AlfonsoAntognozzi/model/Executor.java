package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.MoveCommand;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Executor {


    public static void main (String[]args){

        Robot r1 = new Robot(new Position(1.0 , 1.0)); Robot r2 = new Robot(new Position(6.0 , 6.0)); Robot r3 = new Robot(new Position(7.0 , 7.0)); Robot r4 = new Robot(new Position(8.0 , 8.0));

        Rectangle c1 = new Rectangle<>(new Position(1.0,1.0),new Condition("Pippo"), 4.0,5.0);  Rectangle c2 = new Rectangle<>(new Position(3.0,6.0),new Condition("Michele"), 2.0,3.0);Rectangle c3 = new Rectangle<>(new Position(7.0,10.0),new Condition("Marco"), 1.0,5.0);

        List<IRobot> listaDeiRobot = new ArrayList<IRobot>();
        listaDeiRobot.add(r1); listaDeiRobot.add(r2);

        List<IShape> listaDiShape = new ArrayList<IShape>();
        listaDiShape.add(c1);

        Handler handlerProva = new Handler(new Environment(listaDiShape, listaDeiRobot));

        double [ ] val = new double[3];
        val[0] = -0.5;
        val[1] = -0.5;
        val[2] = 4.0;



        int k=0;
        for(IRobot R : handlerProva.getGameEnvironment().robotList()){
            k+=1;
            System.out.println("Robot numero: "+k+ "\n X: "+R.getRobotPosition().getX()+" \n Y: "+R.getRobotPosition().getY());
        }




    }
}
