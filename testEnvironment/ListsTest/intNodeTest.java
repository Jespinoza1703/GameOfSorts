package ListsTest;

import game.logic.lists.intNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class intNodeTest {

    @Test
    void constructorTest(){
        intNode node = new intNode(500);
        Assertions.assertNotNull(node);
    }
    @Test
    void gettersAndSetters(){
        intNode node1 = new intNode(500);
        intNode node2 = new intNode(700);
        node1.setNext(node2);
        Assertions.assertSame(node2, node1.getNext());
        node1.setValue(58);
        Assertions.assertEquals(58, node1.getValue());
    }
}
