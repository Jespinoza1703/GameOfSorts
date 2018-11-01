package TreeTest;

import game.entities.Dragon;
import game.logic.trees.BinaryTree;
import game.logic.trees.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryTreeTest {

    @Test
    void testAddDragon(){
        Dragon d1 = new Dragon();
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.addDragon(d1);

        Assertions.assertEquals(d1.getName(), binaryTree.getRoot().getDragon().getName());


    }

    @Test
    void testContainTrue(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(2);
        binaryTree.add(1);
        binaryTree.add(7);
        binaryTree.add(3);
        binaryTree.add(5);
        binaryTree.add(38);
        assertTrue(binaryTree.contains(2));
        assertTrue(binaryTree.contains(1));
        assertTrue(binaryTree.contains(7));
        assertTrue(binaryTree.contains(3));
        assertTrue(binaryTree.contains(5));
        assertTrue(binaryTree.contains(38));
    }
    @Test
    void testContainFalse(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(2);
        Assertions.assertFalse(binaryTree.contains(5));
    }
    @Test
    void findMinMaxTest(){
        BinaryTree binaryTree = new BinaryTree();
        binaryTree.add(2);
        binaryTree.add(1);
        binaryTree.add(7);
        binaryTree.add(3);
        binaryTree.add(5);
        binaryTree.add(38);
        binaryTree.add(38);
        assertEquals(1, binaryTree.findMin().getElement());
        assertEquals(38, binaryTree.findMax().getElement());
    }
    @Test
    void findMinMaxWithNullTest(){
        BinaryTree binaryTree = new BinaryTree();
        assertNull(binaryTree.findMax());
        assertNull(binaryTree.findMin());
    }
    @Test
    void setRootTest(){
        BinaryTree binaryTree = new BinaryTree();
        TreeNode treeNode = new TreeNode(10);
        binaryTree.setRoot(treeNode);
        Assertions.assertSame(treeNode, binaryTree.getRoot());
    }




}
