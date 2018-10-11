package game.logic.trees;

public class BinaryTree {

    private TreeNode root = null;

    public BinaryTree(){

    }

    public boolean isEmpty(){
        return root == null;   }

    public boolean contains (int element){
        return contains(element, root);
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
            return findMin(root);
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
            return findMax(root);
        }
    }
    private TreeNode findMax(TreeNode node){
        if (node == null){
            return null;
        }else if(node == null){
            return node;
        }else{
            return findMin(node.right);
        }
    }

}
