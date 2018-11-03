package client;

import game.entities.Dragon;
import game.logic.sorts.SortMethods;
import game.logic.trees.AVLTree;
import game.logic.trees.BinaryTree;

import java.util.ArrayList;
import java.util.List;

public class Wave {

    public long id;
    public int size;
    public String formation = "unsorted";
    public List<Dragon> dragonsList = new ArrayList<>();
    public BinaryTree dragonsBinaryTree = new BinaryTree();
    public AVLTree dragonsAVLTree = new AVLTree();

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

    public Wave(long id, int size, String formation, List<Dragon> dragonsList, BinaryTree dragonsBinaryTree, AVLTree dragonsAVLTree) {
        this.id = id;
        this.size = size;
        this.formation = formation;
        this.dragonsList = dragonsList;
        this.dragonsBinaryTree = dragonsBinaryTree;
        this.dragonsAVLTree = dragonsAVLTree;
    }

    public void dragonDies(Dragon dragon) {
        dragonsList.remove(dragon);
        size = dragonsList.size();
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

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
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
