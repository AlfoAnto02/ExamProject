package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.model.IRobot;
import it.unicam.cs.AlfonsoAntognozzi.model.Rectangle;
import it.unicam.cs.AlfonsoAntognozzi.model.Robot;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import it.unicam.cs.AlfonsoAntognozzi.util.ICondition;
import it.unicam.cs.AlfonsoAntognozzi.util.IPosition;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class RectangleTest {

    @Test
    void testCreateRectangle(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Rectangle<Position, Condition, IRobot<Position, Condition>> r =
                            new Rectangle<>(new Position(1,3),new Condition("Hello"),-4,-4);
                });
    }
    @Test
    void testGetPosition(){
        Rectangle<Position, Condition, IRobot<Position, Condition>> r =
                new Rectangle<>(new Position(1,3),new Condition("Hello"),4,4);
        assertEquals(r.getShapePosition().getX(),1);
        assertEquals(r.getShapePosition().getY(),3);
    }
    @Test
    void testGetWidthAndHeight(){
        Rectangle<Position, Condition, IRobot<Position, Condition>> r =
                new Rectangle<>(new Position(1,3),new Condition("Hello"),4,4);
        assertEquals(r.getHeight(),4);
        assertEquals(r.getWidth(),4);
    }

    @Test
    void testEquals(){
        Rectangle<Position, Condition, IRobot<Position, Condition>> r1 =
                new Rectangle<>(new Position(1,3),new Condition("Hello"),4,4);
        Rectangle<Position, Condition, IRobot<Position, Condition>> r2 =
                new Rectangle<>(new Position(1,3),new Condition("Hi"),4,4);
        assertNotEquals(r1, r2);
        Rectangle<Position, Condition, IRobot<Position, Condition>> r3 =
                new Rectangle<>(new Position(1,3),new Condition("Hello"),1,4);
        assertNotEquals(r1, r3);
        Rectangle<Position, Condition, IRobot<Position, Condition>> r4 =
                new Rectangle<>(new Position(1,3),new Condition("Hello"),4,4);
        assertEquals(r1, r4);
    }
    @Test
    void testHashCode() {
        Rectangle<Position, Condition, IRobot<Position, Condition>> r1 =
                new Rectangle<>(new Position(1, 3), new Condition("Hello"), 4, 4);
        Rectangle<Position, Condition, IRobot<Position, Condition>> r2 =
                new Rectangle<>(new Position(1, 3), new Condition("Hello"), 4, 4);
        assertEquals(r1.hashCode(), r2.hashCode());
    }
    @Test
    void testCheckCollision() {
        Rectangle<IPosition, ICondition, Robot> rectangle =
                new Rectangle<>(new Position(0, 0), new Condition("Hello"), 4, 4);
        Robot robotInside = new Robot(new Position(2, 2));
        Robot robotOutside = new Robot(new Position(5, 5));
        assertTrue(rectangle.checkCollision(robotInside));
        assertFalse(rectangle.checkCollision(robotOutside));
    }
}
