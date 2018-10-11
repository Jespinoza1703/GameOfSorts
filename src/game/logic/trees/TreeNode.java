package game.logic.trees;

public class TreeNode {

    int element;
    TreeNode left;
    TreeNode right;

    public TreeNode(int element) {
        this(element, null, null);
    }

    public TreeNode(int element, TreeNode left, TreeNode right) {
        this.element = element;
        this.left = left;
        this.right = right;
    }
}
