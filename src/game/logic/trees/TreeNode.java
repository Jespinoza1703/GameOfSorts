package game.logic.trees;

import game.entities.Dragon;

public class TreeNode {

    int element, height;
    TreeNode left;
    TreeNode right;
    Dragon  dragon;

    public TreeNode(int element) {
        this(element, null, null, 1);
    }

    public TreeNode(int element, TreeNode left, TreeNode right, int height) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = height;
    }
    public TreeNode(int element, Dragon dragon){
        this.element = element;
        this.dragon = dragon;
        this.dragon.setAge(element);
        this.left = null;
        this.right = null;
        dragon.setRank("Infantry");
    }



    public int getElement() {
        return element;
    }

    public void setElement(int element) {
        this.element = element;
    }

    public TreeNode getLeft() {
        return left;
    }


    public TreeNode getRight() {
        return right;
    }



    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
}
