package ListsTest;

import game.logic.lists.intList;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class intListTest {

    @Test
    void constructorTest(){
        intList intList = new intList();
        Assertions.assertNotNull(intList);
    }

    @Test
    void AddABeginningTest(){
        intList intList = new intList();
        intList.addAtBeginning(8);
        intList.addAtBeginning(10);
        intList.addAtBeginning(1);
        Assertions.assertEquals(1, intList.getByIndex(0).getValue());
        Assertions.assertEquals(10, intList.getByIndex(1).getValue());
        Assertions.assertEquals(8, intList.getByIndex(2).getValue());
    }

    @Test
    void AddEndTest(){
        intList intList = new intList();
        intList.addAtEnd(8);
        intList.addAtEnd(10);
        intList.addAtEnd(1);
        Assertions.assertEquals(8, intList.getByIndex(0).getValue());
        Assertions.assertEquals(10, intList.getByIndex(1).getValue());
        Assertions.assertEquals(1, intList.getByIndex(2).getValue());
    }

    @Test
    void isEmptyTest(){
        intList intList = new intList();
        Assertions.assertTrue(intList.isEmpty());
        intList.addAtEnd(8);
        Assertions.assertFalse(intList.isEmpty());
    }

    @Test
    void getLargeWhenIsEmty(){
        intList intList = new intList();
        Assertions.assertEquals(0, intList.getLarge());
    }

    @Test
    void getLargeTest(){
        intList intList = new intList();
        intList.addAtEnd(8);
        intList.addAtEnd(10);
        intList.addAtEnd(1);
        Assertions.assertEquals(3, intList.getLarge());
    }

    @Test
    void ClearOutTest(){
        intList intList = new intList();
        int i = 0;
        while (i < 100){
            intList.addAtEnd(i);
            i++;
        }
        intList.clearOut();
        Assertions.assertNull(intList.getFirst());
        Assertions.assertEquals(0, intList.getLarge());
    }
    @Test
    void printListTest(){
        intList intList = new intList();
        int i = 0;
        while (i < 100){
            intList.addAtEnd(i);
            i++;
        }
        intList.showList();
    }

    @Test
    void deleteFirst(){
        intList intList = new intList();
        int i = 0;
        while (i < 10){
            intList.addAtEnd(i);
            i++;
        }
        intList.delete(0);
        Assertions.assertEquals(1, intList.getFirst().getValue());
    }

    @Test
    void DeleteOutOfIndexTest(){
        intList intList = new intList();
        int i = 0;
        while (i < 10){
            intList.addAtEnd(i);
            i++;
        }
        intList.delete(20);
    }

    @Test
    void deleteMultipleNodes(){
        intList intList = new intList();
        int i = 0;
        while (i < 10){
            intList.addAtEnd(i);
            i++;
        }
        intList.delete(9);
        intList.delete(4);
        Assertions.assertEquals(9, intList.getByIndex(8).getValue());
    }

    @Test
    void swapTest(){
        intList intList = new intList();
        intList.addAtEnd(8);
        intList.addAtEnd(10);
        intList.swap(0,1);
        Assertions.assertEquals(10, intList.getByIndex(0).getValue());
        Assertions.assertEquals(8, intList.getByIndex(1).getValue());
    }

    @Test
    void AddUniqueTest(){
        intList intList = new intList();
        intList.addUnique(1);
        intList.addUnique(1);
        intList.addUnique(2);
        intList.addUnique(9);
        intList.addUnique(2);
        Assertions.assertEquals(3, intList.getLarge());
    }
}
