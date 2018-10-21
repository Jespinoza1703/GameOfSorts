package TreeTest;

import game.logic.trees.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TreeNodeTest {

    @Test
    void testConstructor(){
        TreeNode treeNode = new TreeNode(5);
        assertNotNull(treeNode);
    }
}
