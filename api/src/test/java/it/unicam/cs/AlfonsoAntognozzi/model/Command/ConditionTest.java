package it.unicam.cs.AlfonsoAntognozzi.model.Command;
import it.unicam.cs.AlfonsoAntognozzi.util.Condition;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ConditionTest {

    @Test
    void incorrectString(){
        assertThrows(IllegalArgumentException.class,
                () -> {
                    Condition c = new Condition("ùà+è--.");
                });
        assertThrows(NullPointerException.class,
                ()->{
                    Condition c = new Condition("");
                });
    }
    @Test
    void correctString(){
        Condition c = new Condition("HelloWorld_");
        assertEquals(c.getCondition(),"HelloWorld_");
    }

    @Test
    void correctEquals(){
        Condition c1 = new Condition("Ciao123_");
        Condition c2 = new Condition("Ciao123");
        Condition c3 = new Condition("Ciao123_");
        assertFalse(c1.equals(c2));
        assertTrue(c1.equals(c3));
    }

}
