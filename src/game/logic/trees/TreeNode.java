package game.logic.trees;

import game.logic.entities.dragon;

public class TreeNode {

    int element, height;
    TreeNode left;
    TreeNode right;
    dragon  dragon;

    public TreeNode(int element) {
        this(element, null, null, 1);
    }

    public TreeNode(int element, TreeNode left, TreeNode right, int height) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = height;
    }
    public TreeNode(int element, dragon dragon){
        this.element = element;
        this.dragon = dragon;
        this.left = null;
        this.right = null;
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

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public game.logic.entities.dragon getDragon() {
        return dragon;
    }

    public void setDragon(game.logic.entities.dragon dragon) {
        this.dragon = dragon;
    }
}
