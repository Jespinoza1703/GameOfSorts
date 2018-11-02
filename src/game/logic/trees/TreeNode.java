package game.logic.trees;

import game.entities.Dragon;

public class TreeNode {

    public double element, height;
    public Dragon dragon;
    public TreeNode left;
    public TreeNode right;


    public TreeNode(double element) {
        this(element, null, null, 1);
    }

    public TreeNode(double element, TreeNode left, TreeNode right, double height) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = height;
    }
    public TreeNode(double element, Dragon dragon){
        this.element = element;
        this.dragon = dragon;
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
}
