package it.unicam.cs.AlfonsoAntognozzi.model;
import it.unicam.cs.AlfonsoAntognozzi.io.ShapeCreator;
import it.unicam.cs.AlfonsoAntognozzi.model.Command.MoveCommand;
import it.unicam.cs.AlfonsoAntognozzi.util.FollowMeParser;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Executor {


    public static void main (String[]args) throws Exception {

        Robot r1 = new Robot(new Position(1.0 , 1.0)); Robot r2 = new Robot(new Position(6.0 , 6.0)); Robot r3 = new Robot(new Position(7.0 , 7.0)); Robot r4 = new Robot(new Position(2.0 , 2.0));
        Rectangle c1 = new Rectangle<>(new Position(1.0,1.0),new Condition("Pippo"), 4.0,5.0);  Rectangle c2 = new Rectangle<>(new Position(3.0,6.0),new Condition("Michele"), 2.0,3.0);Rectangle c3 = new Rectangle<>(new Position(7.0,10.0),new Condition("Marco"), 1.0,5.0);

        List<IRobot> listaDeiRobot = new ArrayList<IRobot>();
        listaDeiRobot.add(r1); listaDeiRobot.add(r4); listaDeiRobot.add(r3); listaDeiRobot.add(r2);
        File shapeFile = new File("C:\\ExamProject\\api\\src\\main\\resources\\ShapeFile");
        Environment Envprova = new Environment(listaDeiRobot);
        ShapeCreator S = new ShapeCreator(Envprova);
        S.parseShape(shapeFile);
        Handler handlerProva = new Handler(Envprova);
        FollowMeParser parser = new FollowMeParser(handlerProva,null);
        File file = new File("C:\\ExamProject\\api\\src\\main\\resources\\CommandFile");
        parser.parseRobotProgram(file);

        int k=0;
        for(IRobot R : handlerProva.getGameEnvironment().robotList()){
            k+=1;
            System.out.println("Robot numero: "+k+ "\n X: "+R.getRobotPosition().getX()+" \n Y: "+R.getRobotPosition().getY());
        }
    }
}
