package TreeTest;

import game.entities.Dragon;
import game.logic.trees.BinaryTree;
import game.logic.trees.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


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

    @Test
    void findMaxWithEntryTree(){
        BinaryTree tree = new BinaryTree();
        TreeNode result = tree.findMax();
        assertNull(result);
    }

    @Test
    void findMinWithEntryTree(){
        BinaryTree tree = new BinaryTree();
        TreeNode result = tree.findMin();
        assertNull(result);
    }

    @Test
    void testDragonRankInRoot(){
        Dragon dragon = new Dragon();
        Dragon dragon1 = new Dragon();
        Dragon dragon2 = new Dragon();
        BinaryTree tree = new BinaryTree();
        tree.addDragon(5, dragon);
        tree.addDragon(7, dragon1);
        tree.addDragon(3, dragon2);
        String result = tree.getRoot().getDragon().getRank();
        assertEquals("Commander", result);
    }
    @Test
    void testDragonRankInInfantry(){
        Dragon dragon = new Dragon();
        Dragon dragon1 = new Dragon();
        Dragon dragon2 = new Dragon();
        BinaryTree tree = new BinaryTree();
        tree.addDragon(5, dragon);
        tree.addDragon(7, dragon1);
        tree.addDragon(3, dragon2);
        String result = tree.findMin().getDragon().getRank();
        assertEquals("Infantry", result);
    }
    @Test
    void testDragonRankInCaptains(){
        Dragon dragon = new Dragon();
        Dragon dragon1 = new Dragon();
        Dragon dragon2 = new Dragon();
        BinaryTree tree = new BinaryTree();
        tree.addDragon(5, dragon);
        tree.addDragon(7, dragon1);
        tree.addDragon(9, dragon2);
        String result = tree.getRoot().getRight().getDragon().getRank();
        assertEquals("Captain", result);
    }






}
