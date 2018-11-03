package client;

import game.draw.Drawer;
import game.entities.Dragon;
import game.logic.lists.SimpleList;
import game.logic.trees.BinaryTree;
import game.logic.trees.TreeNode;

import java.util.List;

public class WaveGenerator {
    SimpleList<SimpleList> treeList = new SimpleList<>();

    public static void listWave(Wave wave){
        List<Dragon> dragons = wave.getDragonsList();
        int waveSize = wave.getSize();
        int columns = 8;
        int rows = waveSize / columns;
        double width = Drawer.width / 2;
        double height = Drawer.height;
        double xOffset = width / columns;
        double yOffset = height / (rows + 1);
        double xPoss = Drawer.width;

        int n = 0;
        for (int i = 0; i < columns; i++){
            for (int j = 0; j < rows; j++){
                double x = (i + 1) * xOffset;
                double y = (j + 1) * yOffset;
                x += xPoss;
                generateDragon(x, y, dragons.get(n));
                n++;
            }
        }
    }

    private static void generateDragon(double x, double y, Dragon dragon){
        int parentAge = dragon.getParentAge();
        int age = dragon.getAge();
        String rank = dragon.getRank();
        String name = dragon.getName();
        int lives = dragon.getLives();
        int fire_rate = dragon.getFire_rate();
        new Dragon(x, y, parentAge, age, rank, name, lives, fire_rate);
    }
/*
    public static void treeWave(Wave wave) {
        List<Dragon> dragons = wave.getDragonsList();
        double width = Drawer.width - 10;
        double height = (Drawer.height/2)-150;
        double xOffset = width;
        double yOffset = height;
        double xPoss = Drawer.width;
        treeWaveAux(wave, 0, width, height, 0, 0,50, height);
    }

    private static void treeWaveAux(Wave wave, int collocated, double x, double y, int exp, int total, int moveY, double beginning){
        List<Dragon> dragons = wave.getDragonsList();
        int moveX = -40;
        int n = 0;
        if (total > wave.getSize()){
            return;
        }else{
            double j = beginning - moveY;
            while (collocated < (int)Math.pow(2, exp)){
                generateDragon(x, y, dragons.get(n));
                y = y + 2* moveY;
                collocated ++;
            }
            treeWaveAux(wave, 0, x + moveX, j, exp + 1, total + collocated, moveY, j);
        }
    }
*/


    public static void treeWave(Wave wave) {
        List<Dragon> dragons = wave.getDragonsList();
        double width = Drawer.width - 10;
        double height = (Drawer.height/2)-150;
    }

    public void readTree(BinaryTree bt){
        TreeNode root = bt.getRoot();
        readTreeAux(root);
    }

    private void readTreeAux(TreeNode current){
        insertOnTreeList(current);
        if(current.left != null){
            readTreeAux(current.left);
        }
        if (current.right != null){
            readTreeAux(current.right);
        }
    }

    private void insertOnTreeList(TreeNode node){
        int i = node.getLevel();
        if (treeList.getByIndex(i) == null){
            treeList.addAtEnd(new SimpleList());
            treeList.getByIndex(i).getValue().addAtEnd(node);
        }
    }
}
