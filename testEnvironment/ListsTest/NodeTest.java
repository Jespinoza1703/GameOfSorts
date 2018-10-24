package ListsTest;

import game.logic.lists.Node;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NodeTest {

    @Test
    void testStandardConstructorWithString(){
        Node node = new Node("Hello World");
        String result = (String) node.getValue();
        assertEquals("Hello World", result);
    }

    @Test
    void testStandardConstructorWithInteger() {
        Node node = new Node(420);
        int result = (int) node.getValue();
        assertEquals(420, result);
    }
}
