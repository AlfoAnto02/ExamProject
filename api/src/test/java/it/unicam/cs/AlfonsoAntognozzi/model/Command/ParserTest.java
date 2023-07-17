package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.io.ShapeCreator;
import it.unicam.cs.AlfonsoAntognozzi.model.*;
import it.unicam.cs.AlfonsoAntognozzi.util.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class ParserTest {

    @Test
    void testParseCommands() throws Exception {
        Robot r = new Robot(new Position(5,5));
        List<Robot> robotList = new ArrayList<>();
        robotList.add(r);
        String filePath = "..\\api\\src\\test\\resources\\testCommandFile.txt"; // Percorso relativo
        Path absolutePath = Paths.get(System.getProperty("user.dir")).resolve(filePath);
        File commandFile = absolutePath.toFile();
        Environment<Robot, IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> env = new Environment<>(robotList);
        Handler<Robot, IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> handler = new Handler<>(env);
        FollowMeParser parser = new FollowMeParser(handler);
        parser.parseRobotProgram(commandFile);
        assertEquals(r.getRobotController().getCommandList().size(),3);
        assertTrue(r.getRobotController().getCommandList().get(0) instanceof RepeatCommand<IRobot<IPosition, ICondition>>);
        assertTrue(r.getRobotController().getCommandList().get(1) instanceof MoveCommand<IRobot<IPosition, ICondition>>);
        assertTrue(r.getRobotController().getCommandList().get(2) instanceof DoneCommand<IRobot<IPosition, ICondition>>);
    }

    @Test
    void testParseShapes() throws FollowMeParserException, IOException {
        Robot r = new Robot(new Position(5,5));
        List<IRobot<IPosition,ICondition>> robotList = new ArrayList<>();
        robotList.add(r);
        Environment<IRobot<IPosition,ICondition>, IShape<IPosition, ICondition, IRobot<IPosition, ICondition>>> env = new Environment<>(robotList);
        String filePath = "..\\api\\src\\test\\resources\\testShapeFile.txt";
        Path absolutePath = Paths.get(System.getProperty("user.dir")).resolve(filePath);
        File shapeFile = absolutePath.toFile();
        ShapeCreator shapeCreator = new ShapeCreator(env);
        shapeCreator.parseShape(shapeFile);
        assertEquals(env.getShapeList().size(),3);
        assertTrue(env.getShapeList().get(0) instanceof Rectangle<IPosition, ICondition, IRobot<IPosition, ICondition>>);
        assertTrue(env.getShapeList().get(1) instanceof Circle<IPosition, ICondition, IRobot<IPosition, ICondition>>);
        assertTrue(env.getShapeList().get(2) instanceof Rectangle<IPosition, ICondition, IRobot<IPosition, ICondition>>);

    }

}
