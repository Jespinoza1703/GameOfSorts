package game.logic.trees;

import game.entities.Dragon;

public class BinaryTree {

    private TreeNode root;

    public BinaryTree(){
        this.root = null;
    }

    public boolean isEmpty(){
        return this.root == null;
    }

    public boolean contains (int element){
        return this.contains(element, this.root);
    }

    private boolean contains (int element, TreeNode node){
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

    public TreeNode findMin(){
        if (this.isEmpty()){
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
        if (this.isEmpty()){
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
            return findMin(node.right);
        }
    }


    private TreeNode addRecursive(TreeNode current, int value) {
        if (current == null) {
            return new TreeNode(value);
        }
        if (value < current.element) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.element) {
            current.right = addRecursive(current.right, value);
        } else {
            // value already exists
            return current;
        }
        return current;
    }

    private TreeNode addRecursiveDragon(TreeNode current, int value, Dragon dragon) {
        if (current == null) {
            return new TreeNode(value, dragon);
        }
        if (value < current.element) {
            current.left = addRecursiveDragon(current.left, value, dragon);
        } else if (value > current.element) {
            current.right = addRecursiveDragon(current.right, value, dragon);
        } else {
            // value already exists
            return current;
        }
        return current;
    }



    public void add (int value){
        root = addRecursive(root, value);
    }

    public void addDragon (int value, Dragon dragon){
        root = addRecursiveDragon(root, value, dragon);
    }


    public void addDragon (){
        Dragon dragon = new Dragon();
        int age = dragon.getAge();
        if (!contains(age)) {
            addDragon(age, dragon);
        }else {
            while (contains(age)){
                age = (int)((Math.random())*1000);
                dragon.setAge(age);
            }
            addDragon(age, dragon);
        }
    }

    public TreeNode getRoot() {
        return root;
    }

    public void clearOut(){
        this.root = null;
    }
}

