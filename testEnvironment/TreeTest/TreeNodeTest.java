package TreeTest;

import game.entities.Dragon;
import game.logic.trees.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class TreeNodeTest {

    @Test
    void testConstructor(){
        TreeNode treeNode = new TreeNode(5);
        assertNotNull(treeNode);
    }
    @Test
    void setAndGetDragonTest(){
        TreeNode treeNode =  new TreeNode(1);
        Dragon dragon = new Dragon();
        treeNode.setDragon(dragon);
        Dragon result = treeNode.getDragon();
        assertSame(dragon, result);
    }
}
