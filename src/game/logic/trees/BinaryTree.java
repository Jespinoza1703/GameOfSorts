package game.logic.trees;


import game.entities.Dragon;

public class BinaryTree {

    public TreeNode root;

    public BinaryTree(){

    }

    public BinaryTree(TreeNode root) {
        this.root = root;
    }

    public boolean contains (double element){
        return this.contains(element, this.root);
    }

    private boolean contains (double element, TreeNode node){
        if (node == null){
            return false;
        } else {
            if (element < node.element){
                return contains(element, node.left);
            } else if (element > node.element){
                return contains(element, node.right);
            }
            else{
                return true;
            }
        }
    }

    public void setDragon (Dragon dragon){
        this.setDragon(dragon, this.root);
    }

    private void setDragon (Dragon dragon, TreeNode node){
        if (dragon.getAge() < node.element){
            setDragon(dragon, node.left);
        } else if (dragon.getAge() > node.element){
            setDragon(dragon, node.right);
        } else if (dragon.getAge() == node.element){
            node.setDragon(dragon);
        }
    }

    public TreeNode findMin(){
        if (root == null){
            return null;
        }
        else{
            return findMin(this.root);
        }
    }

    private TreeNode findMin (TreeNode node){
        if(node.left == null){
            return node;
        }else{
            return findMin(node.left);
        }
    }

    public TreeNode findMax(){
        if (root == null){
            return null;
        }
        else{
            return findMax(this.root);
        }
    }

    private TreeNode findMax(TreeNode node){
        if(node.right == null){
            return node;
        }else{
            return findMax(node.right);
        }
    }

    private TreeNode addRecursive(TreeNode current, double value, int level) {
        if (current == null) {
            return new TreeNode((int) value, level);
        }
        if (value < current.element) {
            current.left = addRecursive(current.left, value, level + 1);
        } else if (value > current.element) {
            current.right = addRecursive(current.right, value, level + 1);
        } else {
            // value already exists
            return current;
        }
        return current;
    }

    private TreeNode addRecursiveDragon(TreeNode current, double value, Dragon dragon, int level) {
        if (current == null) {
            return new TreeNode((int) value, dragon, level);
        }
        if (value < current.element) {
            current.left = addRecursiveDragon(current.left, value, dragon, level+1);
        } else if (value > current.element) {
            current.right = addRecursiveDragon(current.right, value, dragon, level+1);
        } else {
            // value already exists
            return current;
        }
        return current;
    }

    public void add (double value){
        root = addRecursive(root, value, 0);
    }

    private void addDragon (double value, Dragon dragon){
        root = addRecursiveDragon(root, value, dragon, 0);
    }

    public void addDragon (Dragon dragon){
        addDragon(dragon.getAge(), dragon);
    }

    public TreeNode getRoot() {
        return root;
    }

    public void setRoot(TreeNode root) {
        this.root = root;
    }
}