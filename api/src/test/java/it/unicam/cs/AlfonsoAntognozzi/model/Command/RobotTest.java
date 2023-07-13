package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RobotTest {
    @Test
    void testCheckDistanceBetweenRobot() {
        Robot robot1 = new Robot(new Position(0, 0));
        Robot robot2 = new Robot(new Position(3, 4));
        Robot robot3 = new Robot(new Position(10, 10));

        assertFalse(robot1.checkDistanceBetweenRobot(List.of(robot2, robot3), 4));
        assertTrue(robot1.checkDistanceBetweenRobot(List.of(robot2, robot3), 6));
    }

    @Test
    void testSetRobotPosition() {
        Robot robot = new Robot(new Position(0, 0));
        robot.setRobotPosition(2.5, 3.5);
        Position newPosition = (Position) robot.getRobotPosition();
        assertEquals(2.5, newPosition.getX());
        assertEquals(3.5, newPosition.getY());
    }
}
