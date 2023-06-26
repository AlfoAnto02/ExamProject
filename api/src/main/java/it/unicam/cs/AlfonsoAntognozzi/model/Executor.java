package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParser;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParserException;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

import java.io.File;
import java.util.ArrayList;
import java.util.List;



public class Executor {


    public static void main (String[]args) throws Exception {

        Robot r1 = new Robot(new Position(1.0 , 1.0)); Robot r2 = new Robot(new Position(6.0 , 6.0)); Robot r3 = new Robot(new Position(7.0 , 7.0)); Robot r4 = new Robot(new Position(8.0 , 8.0));

        Rectangle c1 = new Rectangle<>(new Position(1.0,1.0),new Condition("Pippo"), 4.0,5.0);  Rectangle c2 = new Rectangle<>(new Position(3.0,6.0),new Condition("Michele"), 2.0,3.0);Rectangle c3 = new Rectangle<>(new Position(7.0,10.0),new Condition("Marco"), 1.0,5.0);

        List<IRobot> listaDeiRobot = new ArrayList<IRobot>();
        listaDeiRobot.add(r1); listaDeiRobot.add(r2);

        List<IShape> listaDiShape = new ArrayList<IShape>();
        listaDiShape.add(c1);

        Handler handlerProva = new Handler(new Environment(listaDiShape, listaDeiRobot));
        FollowMeParser parser = new FollowMeParser(handlerProva, null);
        File sourcefile = new File("C:\\ExamProject\\api\\src\\main\\resources\\fileDiProva");
        parser.parseRobotProgram(sourcefile);


        int k=0;
        for(IRobot R : handlerProva.getGameEnvironment().robotList()){
            k+=1;
            System.out.println("Robot numero: "+k+ "\n X: "+R.getRobotPosition().getX()+" \n Y: "+R.getRobotPosition().getY());
        }




    }
}