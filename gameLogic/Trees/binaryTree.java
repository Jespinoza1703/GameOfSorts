package Trees;

import javax.swing.tree.TreeNode;

public class binaryTree {
    private treeNode root;

    public binaryTree(){
        this.root = null;   }

    public boolean isEmpty(){
        return this.root == null;   }

    public boolean contains (int element){
        return this.contains(element, this.root);
    }
    private boolean contains (int element, treeNode node){
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

    public treeNode findMin(){
        if (this.isEmpty()){
            return null;
        }
        else{
            return findMin(this.root);
        }
    }

    private treeNode findMin (treeNode node){
        if (node == null){
            return null;
        }else if(node.left == null){
            return node;
        }else{
            return findMin(node.left);
        }
    }

    public treeNode findMax(){
        if (this.isEmpty()){
            return null;
        }
        else{
            return findMax(this.root);
        }
    }
    private treeNode findMax(treeNode node){
        if (node == null){
            return null;
        }else if(node.right == null){
            return node;
        }else{
            return findMin(node.right);
        }
    }

}
