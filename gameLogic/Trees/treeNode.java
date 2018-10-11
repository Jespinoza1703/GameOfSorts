package Trees;

public class treeNode {
    int element;
    treeNode left;
    treeNode right;
    public treeNode(int element) {     this(element, null, null);   }

    public treeNode(int element, treeNode left, treeNode right) {
        this.element = element;     this.left = left;     this.right = right;   }
}
