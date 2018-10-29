package client;


import game.entities.Dragon;
import game.logic.sorts.SortMethods;
import game.logic.trees.AVLTree;
import game.logic.trees.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class Wave {

    private long id;
    private int size;
    private List<Dragon> dragonsList = new ArrayList<>();
    private BinaryTree dragonsBinaryTree = new BinaryTree();
    private AVLTree dragonsAVLTree = new AVLTree();
    private String currentWave = "list";

    public Wave() {

    }

    public Wave(long id, int size) {
        this.id = id;
        this.size = size;
        generateDragonList();
    }

    public Wave(long id, int size, List<Dragon> dragonsList) {
        this.id = id;
        this.size = size;
        this.dragonsList = dragonsList;
    }

    public void dragonDies(Dragon dragon) {
        if (currentWave.equals("list")) {
            dragonsList.remove(dragon);
            size--;
        } else if (currentWave.equals("avl")) {
            dragonsAVLTree.deleteNode(dragon.getAge());
            size--;
        } else if (currentWave.equals("binary")) {
            //dragonsBinaryTree.delete(dragon.getAge());
            size--;
        }
    }

    private void generateDragonList() {
        Dragon parent = new Dragon(-1, size, "Commander");
        dragonsList.add(parent);
        for (int i = 1; i < size; i++) {
            parent = new Dragon(parent.getAge(), size - i, defineRank());
            dragonsList.add(parent);
        }
        SortMethods.unSort(dragonsList);
    }

    private String defineRank() {
        int i = (int) (Math.random() * 10);
        if (i % 2 == 0) {
            return "Captain";
        }
        return "Infantry";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Dragon> getDragonsList() {
        return dragonsList;
    }

    public void setDragonsList(List<Dragon> dragonsList) {
        this.dragonsList = dragonsList;
    }

    public BinaryTree getDragonsBinaryTree() {
        return dragonsBinaryTree;
    }

    public void setDragonsBinaryTree(BinaryTree dragonsBinaryTree) {
        this.dragonsBinaryTree = dragonsBinaryTree;
    }

    public AVLTree getDragonsAVLTree() {
        return dragonsAVLTree;
    }

    public void setDragonsAVLTree(AVLTree dragonsAVLTree) {
        this.dragonsAVLTree = dragonsAVLTree;
    }
}
