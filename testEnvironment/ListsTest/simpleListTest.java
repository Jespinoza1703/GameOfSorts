package ListsTest;

import game.logic.lists.Node;
import game.logic.lists.SimpleList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class simpleListTest {

    @Test
    void testSimpleListEmpty(){
        SimpleList list = new SimpleList();
        Node result = list.getFirst();
        assertNull(result);
    }
    @Test
    void testSimpleListEmptyLarge(){
        SimpleList list = new SimpleList();
        int result = list.getLarge();
        assertEquals(0, result);
    }

    @Test
    void testSimpleListNotEmpty(){
        SimpleList list = new SimpleList();
        list.addAtEnd(1);
        Node result = list.getFirst();
        assertNotNull(result);
    }

    @Test
    void testSimpleListNotEmptyLarge(){
        SimpleList list = new SimpleList();
        list.addAtEnd(1);
        int result = list.getLarge();
        assertEquals(1, result);
    }

    @Test
    void testAddAtBeginning(){
        SimpleList list = new SimpleList();
        list.addAtBeginning(2);
        list.addAtBeginning(1);
        int result = (int) list.getFirst().getValue();
        assertEquals(1, result);
    }

    @Test
    void testIsEmptyMethodWithEmptyList(){
        SimpleList list = new SimpleList();
        boolean result = list.isEmpty();
        assertTrue(result);
    }

    @Test
    void testIsEmptyMethodWithNotEmptyList(){
        SimpleList list = new SimpleList();
        list.addAtBeginning(1);
        boolean result = list.isEmpty();
        assertFalse(result);
    }

    @Test
    void testSwap(){
        SimpleList list = new SimpleList();
        list.addAtBeginning(1);
        list.addAtBeginning(2);
        list.swap(0,1);
        int result = (int)list.getFirst().getValue();
        assertEquals(1, result);
    }

    @Test
    void testInverseSwap(){
        SimpleList list = new SimpleList();
        list.addAtBeginning(2);
        list.addAtBeginning(1);
        list.swap(0,1);
        int result = (int)list.getFirst().getValue();
        assertEquals(2, result);
    }

    @Test
    void testDeleteFirst(){
        SimpleList list = new SimpleList();
        list.addAtBeginning(2);
        list.addAtBeginning(1);
        list.delete(0);
        int result = (int) list.getFirst().getValue();
        assertEquals(2, result);
    }

    @Test
    void testDeleteLastOne(){
        SimpleList list = new SimpleList();
        list.addAtEnd(3);
        list.addAtEnd(2);
        list.addAtEnd(1);
        list.delete(2);
        Node result = list.getByIndex(2);
        assertNull(result);
    }

    @Test
   void testDeleteOther(){
       SimpleList list = new SimpleList();
       list.addAtEnd(3);
       list.addAtEnd(2);
       list.addAtEnd(1);
       list.delete(1);
       int result = (int)list.getByIndex(1).getValue();
       assertEquals(1, result);
   }




}
