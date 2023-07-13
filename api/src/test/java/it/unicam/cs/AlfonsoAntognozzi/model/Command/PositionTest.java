package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.util.Position;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PositionTest {

    @Test
    void xAndyShouldBeFour(){
        Position P = new Position(4,4);
        assertEquals(4,P.getX());
        assertEquals(4,P.getY());
    }
    @Test
    void setShouldReturn3(){
        Position P = new Position(4,4);
        P.setX(3);
        P.setY(3);
        assertEquals(3,P.getX());
        assertEquals(3,P.getY());
    }
    @Test
    void p1ShouldEqualsp2(){
        Position p1 = new Position(3,3);
        Position p2 = new Position(2,2);
        assertFalse(p1.equals(p2));
        p2.setY(3);
        p2.setX(3);
        assertTrue(p1.equals(p2));
    }

}
