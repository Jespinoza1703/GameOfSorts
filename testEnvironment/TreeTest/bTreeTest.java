package TreeTest;

import game.entities.Dragon;
import game.logic.trees.BTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class bTreeTest {
    @Test
    void addTest(){
        BTree bTree = new BTree();
        int i = 0;
        while (i < 10){
            Dragon dragon =  new Dragon();
            bTree.add(i, dragon);
            i++;
        }
        System.out.println(bTree.toString());
        Assertions.assertNotNull(bTree);
    }
}
