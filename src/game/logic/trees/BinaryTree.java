package game.logic.trees;

public class binaryTree {
    private TreeNode root;

    public binaryTree(){
        this.root = null;   }

    public boolean isEmpty(){
        return this.root == null;   }

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
        if (node == null){
            return null;
        }else if(node.left == null){
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
        if (node == null){
            return null;
        }else if(node.right == null){
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
    public void add (int value){
        root = addRecursive(root, value);
    }




}

