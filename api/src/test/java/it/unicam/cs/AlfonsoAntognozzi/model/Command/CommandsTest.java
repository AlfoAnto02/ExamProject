package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.*;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



public class CommandsTest {


    @Test
    void testMoveCommand(){
        Robot r = new Robot(new Position(2,3));
        double[] args1 = {1.1,0.5,4};
        assertThrows(IllegalArgumentException.class,
                () -> r.getRobotController().addCommand(new MoveCommand<>(args1)));
        double[] args2 = {0.5,0.5,4};
        r.getRobotController().addCommand(new MoveCommand<>(args2));
        r.consume();
        assertEquals(r.getRobotPosition().getX(),4);
    }

    @Test
    void testMoveRandomCommand(){
        Robot r = new Robot(new Position(2,3));
        double[] args = {1,100,1,200,5};
        r.getRobotController().addCommand(new MoveRandomCommand<>(args));
        r.consume();
        assertNotEquals(r.getRobotPosition().getX(),2);
        assertNotEquals(r.getRobotPosition().getX(),3);
    }

    @Test
    void testStopCommand(){
        Robot r = new Robot(new Position(2,3));
        double[] args = {1,100,1,200,5};
        r.getRobotController().addCommand(new MoveRandomCommand<>(args));
        r.getRobotController().addCommand(new MoveRandomCommand<>(args));
        r.getRobotController().addCommand(new StopCommand<>());
        r.getRobotController().addCommand(new MoveRandomCommand<>(args));
        r.consume();
        r.consume();
        r.consume();
        assertTrue(r.getRobotController().getCommandList().isEmpty());
    }

    @Test
    void testSignalAndUnSignalCommand(){
        Robot r = new Robot(new Position(2,3));
        Condition c = new Condition("Hello");
        r.getRobotController().addCommand(new SignalCommand<>(c));
        r.consume();
        assertEquals(r.getRobotCondition().getCondition(),c.getCondition());
        r.getRobotController().addCommand(new UnSignalCommand<>(new Condition("Hello")));
        r.consume();
        assertEquals(r.getRobotCondition().getCondition(),"_");
    }

    @Test
    void testRepeatCommand(){
        List<Robot> robotList = new ArrayList<>();
        Robot r = new Robot(new Position(2,3));
        robotList.add(r);
        Environment<Robot, IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> env = new Environment<>(robotList);
        double[] args = {0.5,0.5,4};
        r.getRobotController().addCommand(new RepeatCommand<>(3));
        r.getRobotController().addCommand(new MoveCommand<>(args));
        r.getRobotController().addCommand(new DoneCommand<>());
        while(env.hasNextInstruction()) env.executeNextInstruction();
        assertEquals(r.getRobotPosition().getX(), 8);
    }

    @Test
    void testUntilCommand(){
        Robot r = new Robot(new Position(-10,-10));
        Rectangle<IPosition, ICondition, IRobot<IPosition, ICondition>> rectangle =
                new Rectangle<>(new Position(2,2),new Condition("Hello"),4,4);
        List<Robot> robotList = new ArrayList<>();
        robotList.add(r);
        Environment<Robot, IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> env = new Environment<>(robotList);
        env.addShapeToList(rectangle);
        double[] args = {0.5,0.5,4};
        r.getRobotController().addCommand(new UntilCommand<>(env.getShapeList(),"Hello"));
        r.getRobotController().addCommand(new MoveCommand<>(args));
        r.getRobotController().addCommand(new DoneCommand<>());
        while(env.hasNextInstruction()) env.executeNextInstruction();
        assertEquals(r.getRobotPosition().getX(),0);
        assertEquals(r.getRobotPosition().getY(),0);
    }
    @Test
    void testDoForeverCommand(){
        Robot r = new Robot(new Position(-10,-10));
        List<Robot> robotList = new ArrayList<>();
        robotList.add(r);
        Environment<Robot, IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> env = new Environment<>(robotList);
        double[] args = {0.5,0.5,4};
        r.getRobotController().addCommand(new DoForeverCommand<>());
        r.getRobotController().addCommand(new MoveCommand<>(args));
        r.getRobotController().addCommand(new DoneCommand<>());
        double t = -10;
        for(int i = 1; i < 100; i++){
            for(int j = 0; j <3;j++){
                env.executeNextInstruction();
            }
            assertEquals(r.getRobotPosition().getX(),t+i*2);
            assertEquals(r.getRobotPosition().getY(),t+i*2);
        }
    }

    @Test
    void testFollowLabelCommand(){
        Robot r1 = new Robot(new Position(2,2));
        r1.setRobotCondition(new Condition("CIAO"));
        Robot r2 = new Robot(new Position(4,4));
        r2.setRobotCondition(new Condition("CIAO"));
        Robot r3 = new Robot(new Position(3,3));
        Robot r4 = new Robot(new Position(-5,-5));
        List<IRobot<IPosition,ICondition>> robotList = new ArrayList<>();
        robotList.add(r1); robotList.add(r2); robotList.add(r3); robotList.add(r4);
        Environment<IRobot<IPosition,ICondition>, IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> env = new Environment<>(robotList);
        double[] args = {5,5};
        for(IRobot<IPosition,ICondition> R : robotList) R.getRobotController().addCommand(new FollowLabelCommand<>("CIAO",args,robotList));
        while (env.hasNextInstruction()) env.executeNextInstruction();
        assertEquals(r1.getRobotPosition().getX(),17);
    }
    @Test
    void testContinueCommand(){
        Robot r = new Robot(new Position(2,3));
        double[] args = {0.5,0.5,4};
        r.getRobotController().addCommand(new MoveCommand<>(args));
        assertThrows(IllegalArgumentException.class,
                () -> r.getRobotController().addCommand(new ContinueCommand<>(-1)));
        r.getRobotController().addCommand(new ContinueCommand<>(3));
        r.consume();
        r.consume();
        assertEquals(r.getRobotPosition().getX(),10);
    }
    @Test
    void testInnestedLoop(){
        Robot r1 = new Robot(new Position(2,2));
        List<IRobot<IPosition,ICondition>> robotList = new ArrayList<>();
        robotList.add(r1);
        Environment<IRobot<IPosition,ICondition>, IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> env = new Environment<>(robotList);
        double[] args = {0.5,0.5,4};
        r1.getRobotController().addCommand(new RepeatCommand<>(3));
        r1.getRobotController().addCommand(new RepeatCommand<>(2));
        r1.getRobotController().addCommand(new MoveCommand<>(args));
        r1.getRobotController().addCommand(new DoneCommand<>());
        r1.getRobotController().addCommand(new DoneCommand<>());
        while (env.hasNextInstruction()) env.executeNextInstruction();
        assertEquals(r1.getRobotPosition().getX(),14); // 3*2
        r1.getRobotController().addCommand(new RepeatCommand<>(3));
        r1.getRobotController().addCommand(new MoveCommand<>(args));
        r1.getRobotController().addCommand(new RepeatCommand<>(2));
        r1.getRobotController().addCommand(new MoveCommand<>(args));
        r1.getRobotController().addCommand(new DoneCommand<>());
        r1.getRobotController().addCommand(new DoneCommand<>());
        while (env.hasNextInstruction()) env.executeNextInstruction();
        assertEquals(r1.getRobotPosition().getX(),32); //14 + 3*2 + 3*2*2
        Rectangle<IPosition, ICondition, IRobot<IPosition, ICondition>> rectangle =
                new Rectangle<>(new Position(32,32),new Condition("Hello"),4,4);
        env.addShapeToList(rectangle);
        double [] args2 ={0.5,0.5,-6};
        r1.getRobotController().addCommand(new RepeatCommand<>(3));
        r1.getRobotController().addCommand(new UntilCommand<>(env.getShapeList(),"Hello"));
        r1.getRobotController().addCommand(new MoveCommand<>(args2));
        r1.getRobotController().addCommand(new DoneCommand<>());
        r1.getRobotController().addCommand(new MoveCommand<>(args));
        r1.getRobotController().addCommand(new DoneCommand<>());
        while (env.hasNextInstruction()) env.executeNextInstruction();
        assertEquals(r1.getRobotPosition().getX(),35);
        r1.getRobotController().addCommand(new RepeatCommand<>(2));
        r1.getRobotController().addCommand(new RepeatCommand<>(2));
        r1.getRobotController().addCommand(new RepeatCommand<>(2));
        r1.getRobotController().addCommand(new MoveCommand<>(args));
        r1.getRobotController().addCommand(new DoneCommand<>());
        r1.getRobotController().addCommand(new DoneCommand<>());
        r1.getRobotController().addCommand(new DoneCommand<>());
        while (env.hasNextInstruction()) env.executeNextInstruction();
        assertEquals(r1.getRobotPosition().getX(),51);
    }


}
