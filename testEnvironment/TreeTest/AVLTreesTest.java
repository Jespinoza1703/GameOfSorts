package TreeTest;

import game.logic.trees.AVLTree;
import game.logic.trees.TreeNode;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTreesTest {
    @Test
    void AVLA_add_Balance_7_Elements() {
        AVLTree tree = new AVLTree();
        int i = 1;
        while (i <= 7){
            tree.insert(i);
            i++;
        }
        assertEquals(4, tree.getRoot().getElement());
    }

    @Test
    void AVL_Add_Balance_12_Elements(){
        AVLTree tree = new AVLTree();
        int i = 1;
        while (i <= 12){
            tree.insert(i);
            i++;
        }
        assertEquals(8, tree.getRoot().getElement());
    }

    @Test
    void AVL_Find_Min_12_Elements(){
        AVLTree tree = new AVLTree();
        int i = 1;
        while (i <= 12){
            tree.insert(i);
            i++;
        }
        TreeNode tmp = tree.getRoot();
        while (tmp.getLeft() != null){
            tmp = tmp.getLeft();
        }
        assertEquals(1, tmp.getElement());
    }
    @Test
    void AVL_Find_Max_12_Elements(){
        AVLTree tree = new AVLTree();
        int i = 1;
        while (i <= 12){
            tree.insert(i);
            i++;
        }
        TreeNode tmp = tree.getRoot();
        while (tmp.getRight() != null){
            tmp = tmp.getRight();
        }
        assertEquals(12, tmp.getElement());
    }

    @Test
    void delete_Root_12_Elements(){
        AVLTree tree = new AVLTree();
        int i = 1;
        while (i <= 7){
            tree.insert(i);
            i++;
        }
        TreeNode tmp = tree.getRoot();
        tree.deleteNode(tmp, 4);
        assertEquals(5, tmp.getElement());
    }





}
