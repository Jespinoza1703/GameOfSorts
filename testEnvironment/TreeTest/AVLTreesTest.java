package TreeTest;

import game.logic.trees.AVLTree;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreesTest {
    @Test
    void AVLAdd() {
        AVLTree tree = new AVLTree();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        assertEquals(4, tree.getRoot().getElement());

    }
}
