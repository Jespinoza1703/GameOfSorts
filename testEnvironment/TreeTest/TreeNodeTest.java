package TreeTest;

import game.entities.Dragon;
import game.logic.trees.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TreeNodeTest {

    @Test
    void testConstructor(){
        TreeNode treeNode = new TreeNode(5, 0);
        assertNotNull(treeNode);
    }
    @Test
    void setAndGetDragonTest(){
        TreeNode treeNode =  new TreeNode(1, 0);
        Dragon dragon = new Dragon();
        treeNode.setDragon(dragon);
        Dragon result = treeNode.getDragon();
        assertSame(dragon, result);
    }

    @Test
    void SetAndGetRightLeftTest(){
        TreeNode treeNode = new TreeNode(50);
        TreeNode right = new TreeNode(2);
        TreeNode left = new TreeNode(7);
        treeNode.setRight(right);
        treeNode.setLeft(left);
        assertSame(right, treeNode.getRight());
        assertSame(left, treeNode.getLeft());
    }
}
