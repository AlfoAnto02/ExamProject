package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.Circle;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
public class CirlceTest {

    @Test
    void testCreateCircle(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Circle<Position, Condition, IRobot<Position, Condition>> c =
                            new Circle<>(new Position(1,3),new Condition("Hello"),-4);
                });
    }

    @Test
    void testGetPosition(){
        Circle<Position, Condition, IRobot<Position, Condition>> r =
                new Circle<>(new Position(1,3),new Condition("Hello"),4);
        assertEquals(r.getShapePosition().getX(),1);
        assertEquals(r.getShapePosition().getY(),3);
    }

    @Test
    void testGetRadius(){
        Circle<Position, Condition, IRobot<Position, Condition>> c =
                new Circle<>(new Position(1,3),new Condition("Hello"),4);
        assertEquals(c.getRadius(),4);
    }
    @Test
    void testEquals() {
        Circle<Position, Condition, IRobot<Position, Condition>> c1 =
                new Circle<>(new Position(1, 3), new Condition("Hello"), 4);
        Circle<Position, Condition, IRobot<Position, Condition>> c2 =
                new Circle<>(new Position(1, 3), new Condition("Hello"), 4);
        Circle<Position, Condition, IRobot<Position, Condition>> c3 =
                new Circle<>(new Position(1, 3), new Condition("Hi"), 4);
        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(c3));
    }

    @Test
    void testHashCode() {
        Circle<Position, Condition, IRobot<Position, Condition>> c1 =
                new Circle<>(new Position(1, 3), new Condition("Hello"), 4);
        Circle<Position, Condition, IRobot<Position, Condition>> c2 =
                new Circle<>(new Position(1, 3), new Condition("Hello"), 4);

        assertEquals(c1.hashCode(), c2.hashCode()); // I cerchi con gli stessi attributi devono avere lo stesso hash code
    }

    @Test
    void testCheckCollision() {
        Circle<IPosition, ICondition, Robot> circle =
                new Circle<>(new Position(0, 0), new Condition("Hello"), 5);
        Robot robotInside = new Robot(new Position(2, 2));
        Robot robotOutside = new Robot(new Position(5, 5));
        assertTrue(circle.checkCollision(robotInside));
        assertFalse(circle.checkCollision(robotOutside));
    }
}
