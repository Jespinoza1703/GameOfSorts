package game.logic.trees;

import game.entities.Dragon;

public class TreeNode {

    public double element, height;
    public int level;
    public Dragon dragon;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(double element){
        this.setElement(element);
    }

    public TreeNode(double element, int level) {
        this(element, null, null, 1,  level);
    }

    public TreeNode(double element, TreeNode left, TreeNode right, double height, int level) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = height;
        this.setLevel(level);
    }
    public TreeNode(double element, Dragon dragon, int level){
        this.element = element;
        this.dragon = dragon;
        this.setLevel(level);
        this.left = null;
        this.right = null;
    }

    public TreeNode(double element, Dragon dragon, TreeNode left, TreeNode right){
        this.element = element;
        this.dragon = dragon;
        this.left = left;
        this.right = right;
    }


    public int getElement() {
        return (int) element;
    }

    public void setElement(double element) {
        this.element = element;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}