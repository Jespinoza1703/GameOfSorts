package utilTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import util.Clock;

public class clockTest {
    @Test
    void setThickTest(){
       Clock clock =  Clock.getInstance();
       clock.ticks(50);
    }

    @Test
    void getMills(){
        Clock clock =  Clock.getInstance();
        Assertions.assertNotNull(clock.getTime());
    }
}