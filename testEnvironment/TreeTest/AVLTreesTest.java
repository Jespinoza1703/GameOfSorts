package TreeTest;

import game.entities.Dragon;
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


    @Test
    void TestDragonToTree(){
        Dragon dragon = new Dragon();
        AVLTree tree = new AVLTree();
        tree.insert(dragon);
        assertEquals(dragon, tree.getRoot().getDragon());
    }
    @Test
    void insertAnyDragonTest(){
        AVLTree tree = new AVLTree();
        tree.insertDragon();
    }

    @Test
    void insertDragonWithAnAge(){
        Dragon dragon = new Dragon();
        AVLTree tree = new AVLTree();
        tree.insertDragon(100, dragon);
        assertSame(dragon, tree.getRoot().getDragon());
    }

    @Test
    void insertDragonWithAnAgeTestAge(){
        Dragon dragon = new Dragon();
        AVLTree tree = new AVLTree();
        dragon.setAge(100);
        tree.insert(dragon);
        int result = tree.getRoot().getDragon().getAge();
        assertEquals(100, result);
    }


    @Test
    void testSetDragon(){
        Dragon dragon = new Dragon();
        AVLTree tree = new AVLTree();
        tree.insert(1000);
        tree.getRoot().setDragon(dragon);
        assertSame(dragon, tree.getRoot().getDragon());
    }

    @Test
    void testMaxValue(){
        AVLTree tree = new AVLTree();
        int i = 1;
        while (i <= 7){
            tree.insert(i);
            i++;
        }
        int result  = tree.maxValueNode(tree.getRoot()).getElement();
        assertEquals(7, result);
    }

    @Test
    void testClearOut(){
        AVLTree tree = new AVLTree();
        int i = 1;
        while (i <= 7){
            tree.insert(i);
            i++;
        }
        tree.clearOut();
        TreeNode result = tree.getRoot();
        assertNull(result);
    }

    @Test
    void insertRandomNumber(){
        AVLTree tree = new AVLTree();
        int i = 1;
        while (i <= 20){
            int j = ((int)((Math.random())*100));
            tree.insert(j);
            i++;
        }
    }

    @Test
    void deleteRandomPosition(){
        AVLTree tree = new AVLTree();
        tree.insert(35);
        int i = 1;
        while (i <= 20){
            int j = ((int)((Math.random())*100));
            tree.insert(j);
            i++;
        }
        tree.deleteNode(tree.getRoot(), 35);
    }
    @Test
    void deleteAnotherRandomPosition(){
        AVLTree tree = new AVLTree();
        tree.insert(2);
        int i = 1;
        while (i <= 20){
            int j = ((int)((Math.random())*100));
            tree.insert(j);
            i++;
        }
        tree.deleteNode(tree.getRoot(), 2);
    }

    @Test
    void deleteTest(){
        AVLTree tree = new AVLTree();
        tree.deleteNode(2);
        assertNull(tree.getRoot());
    }

    @Test
    void setRootTest(){
        AVLTree tree = new AVLTree();
        TreeNode node = new TreeNode(1);
        tree.setRoot(node);
        assertSame(node, tree.getRoot());
    }




}
