package clientTest;

import client.Wave;
import game.entities.Dragon;
import game.logic.trees.AVLTree;
import game.logic.trees.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class waveTest {
    @Test
    void testConstructor(){
        Wave wave = new Wave();
        assertNotNull(wave);
    }
    @Test
    void testConstructor1(){
        Wave wave = new Wave(1552, 2);
        long id = wave.getId();
        int size = wave.getSize();
        assertEquals(1552, id);
        assertEquals(2, size);
    }
    @Test
    void testGenerateDragonList(){
        int i = 0;
        ArrayList dragonList = new ArrayList<>();

        while (i < 15){
            dragonList.add(new Dragon());
            i++;
        }
        Wave wave = new Wave(1552, 2, dragonList);
        assertNotNull(wave.getDragonsList());
    }

    @Test
    void testDragonDies(){
        int i = 0;
        ArrayList dragonList = new ArrayList<>();

        while (i < 15){
            dragonList.add(new Dragon());
            i++;
        }
        Dragon dragon = (Dragon) dragonList.get(0);
        Wave wave = new Wave(1552, 2, dragonList);
        wave.dragonDies(dragon);
        assertEquals(14, wave.getDragonsList().size());

    }

    @Test
    void testSettersAndSetter(){
        Wave wave = new Wave();
        ArrayList dragonList = new ArrayList<>();
        AVLTree avlTree = new AVLTree();
        BinaryTree binaryTree = new BinaryTree();

        wave.setDragonsList(dragonList);
        wave.setSize(5);
        wave.setId(5);
        wave.setDragonsAVLTree(avlTree);
        wave.setDragonsBinaryTree(binaryTree);

        assertEquals(dragonList, wave.getDragonsList());
        assertEquals(5, wave.getSize());
        assertEquals(5, wave.getId());
        assertEquals(avlTree, wave.getDragonsAVLTree());
        assertEquals(binaryTree, wave.getDragonsBinaryTree());
    }
}
