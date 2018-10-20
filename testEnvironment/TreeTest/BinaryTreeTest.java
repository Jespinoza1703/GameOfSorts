package TreeTest;

import game.entities.Dragon;
import game.logic.trees.BinaryTree;
import game.logic.trees.TreeNode;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import game.logic.trees.BinaryTree.*;

public class BinaryTreeTest {

    @Test
    void isEmptyWhenIsEmpty(){
        BinaryTree tree = new BinaryTree();
        boolean result = tree.isEmpty();
        assertTrue(result);
    }

    @Test
    void isEmptyWhenIsNotEmpty(){
        BinaryTree tree = new BinaryTree();
        tree.add(8);
        boolean result = tree.isEmpty();
        assertFalse(result);
    }

    @Test
    void TestContainsWhenContainSTheNumber(){
        BinaryTree tree = new BinaryTree();
        tree.add(8);
        boolean result = tree.contains(8);
        assertTrue(result);
    }
    @Test
    void TestContainsWhenNotContainSTheNumber(){
        BinaryTree tree = new BinaryTree();
        tree.add(8);
        boolean result = tree.contains(5);
        assertFalse(result);
    }
    @Test
    void testFindMin(){
        BinaryTree tree = new BinaryTree();
        tree.add(8);
        tree.add(7);
        tree.add(9);
        tree.add(1);
        tree.add(4);
        int result = tree.findMin().getElement();
        assertEquals(1, result);
    }

    @Test
    void testFindMinWhenIsEmpty(){
        BinaryTree tree = new BinaryTree();
        TreeNode result = tree.findMin();
        assertNull(result);
    }
    @Test
    void testFindMinWhenIsTheRoot(){
        BinaryTree tree = new BinaryTree();
        tree.add(1);
        tree.add(2);
        tree.add(3);
        tree.add(4);
        tree.add(5);
        int result = tree.findMin().getElement();
        assertEquals(1, result);
    }

    @Test
    void testFindMax(){
        BinaryTree tree = new BinaryTree();
        tree.add(8);
        tree.add(7);
        tree.add(9);
        tree.add(1);
        tree.add(4);
        int result = tree.findMax().getElement();
        assertEquals(9, result);
    }

    @Test
    void testFindMaxWhenIsEmpty(){
        BinaryTree tree = new BinaryTree();
        TreeNode result = tree.findMax();
        assertNull(result);
    }

    @Test
    void testFindManWhenIsTheRoot(){
        BinaryTree tree = new BinaryTree();
        tree.add(5);
        tree.add(4);
        tree.add(3);
        tree.add(2);
        tree.add(1);
        int result = tree.findMax().getElement();
        assertEquals(5, result);
    }

    @Test
    void ClearOut(){
        BinaryTree tree = new BinaryTree();
        int i = 0;
        while (i<6){
            tree.add(i);
            i++;
        }
        tree.clearOut();
        TreeNode result = tree.getRoot();
        assertNull(result);
    }

    @Test
    void addDragonTest(){
        BinaryTree tree = new BinaryTree();
        int i = 0;
        while (i<6){
            tree.addDragon();
            i++;
        }
        Dragon dragon = tree.getRoot().getDragon();
        boolean result = dragon.getClass() == Dragon.class;
        assertTrue(result);

    }









}
