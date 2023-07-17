package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.*;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
public class ControllerTest {

    @Test
    void testAddCommand(){
        Controller<ICommand<IRobot<IPosition, ICondition>>,IRobot<IPosition,ICondition>> controller = new Controller<>();
        controller.addCommand(new DoneCommand<>());
        assertEquals(1, controller.getCommandList().size());
    }

    @Test
    void testConsumeCommand(){
        Robot r = new Robot(new Position(1,1));
        r.getRobotController().addCommand(new MoveCommand<>(new double[] {0.5,0.5,4}));
        r.getRobotController().consume(r);
        assertEquals(r.getRobotPosition().getX(),3);
        assertEquals(1,r.getRobotController().getProgramCounter());
    }

    @Test
    void testHasNextInstruction(){
        Controller<ICommand<IRobot<IPosition, ICondition>>,IRobot<IPosition,ICondition>> controller = new Controller<>();
        controller.addCommand(new DoneCommand<>());
        assertTrue(controller.hasNextInstruction());
    }

    @Test
    void testSkipUntilInstruction(){
        Robot r = new Robot(new Position(1,1));
        List<Robot> robotList = new ArrayList<>();
        robotList.add(r);
        Environment<Robot, IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> env = new Environment<>(robotList);
        r.getRobotController().addCommand(new UntilCommand<>(env.getShapeList(),"ciao"));
        r.getRobotController().addCommand(new MoveCommand<>(new double[] {0.5,0.5,4}));
        r.getRobotController().addCommand(new MoveCommand<>(new double[] {0.5,0.5,4}));
        r.getRobotController().addCommand(new MoveCommand<>(new double[] {0.5,0.5,4}));
        r.getRobotController().addCommand(new DoneCommand<>());
        r.getRobotController().skipUntilInstruction();
        assertEquals(r.getRobotController().getProgramCounter(),3);
    }
}
