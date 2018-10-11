package game.logic.trees;

public class AVLTree {
    TreeNode root;

    public AVLTree(){
    }


    // A utility function to get the height of the tree
    int height(TreeNode N) {
        if (N == null)
            return 0;

        return N.height;
    }

    // A utility function to get maximum of two integers
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    // A utility function to right rotate subtree rooted with y
    // See the diagram given above.
    private TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        // Perform rotation
        x.right = y;
        y.left = T2;

        // Update heights
        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        // Return new root
        return x;
    }

    // A utility function to left rotate subtree rooted with x
    // See the diagram given above.
    private TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        // Perform rotation
        y.left = x;
        x.right = T2;

        //  Update heights
        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        // Return new root
        return y;
    }

    // Get Balance factor of node N
    private int getBalance(TreeNode N) {
        if (N == null)
            return 0;

        return height(N.left) - height(N.right);
    }

    private TreeNode insert(TreeNode node, int key) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            return (new TreeNode(key));

        if (key < node.element)
            node.left = insert(node.left, key);
        else if (key > node.element)
            node.right = insert(node.right, key);
        else // Duplicate keys not allowed
            return node;

        /* 2. Update height of this ancestor node */
        node.height = 1 + max(height(node.left),
                height(node.right));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = getBalance(node);

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && key < node.left.element)
            return rightRotate(node);

        // Right Right Case
        if (balance < -1 && key > node.right.element)
            return leftRotate(node);

        // Left Right Case
        if (balance > 1 && key > node.left.element) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && key < node.right.element) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        /* return the (unchanged) node pointer */
        return node;
    }

    // A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.element + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void insert(int element){
        root = insert(root, element);
    }


    public TreeNode getRoot() {
        return root;
    }

}
