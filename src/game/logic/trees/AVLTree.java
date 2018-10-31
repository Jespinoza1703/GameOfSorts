package game.logic.trees;


import game.entities.Dragon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AVLTree {

    TreeNode root;
    private static Logger logger = LoggerFactory.getLogger("AVLTree");

    public AVLTree(){
        logger.debug("Created New AVL Tree");
    }

    /**
     * A utility function to get the height of the tree
     * @param N Node to analise
     * @return height of N node
     */
    int height(TreeNode N) {
        if (N == null)
            return 0;

        return (int) N.height;
    }

    /**
     *  A utility function to get maximum of two integers
     * @param a Value 1
     * @param b Value 2
     * @return Max value
     */
    int max(int a, int b) {
        return (a > b) ? a : b;
    }

    /**
     * A utility function to right rotate subtree rooted with y
     * @param y
     * @return TreeNode Rotated
     */
    private TreeNode rightRotate(TreeNode y) {
        logger.debug("Rotated " + y + " to right");
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


    /**
     * A utility function to left rotate subtree rooted with x
     * @param x
     * @return TreeNode rotated
     */
    private TreeNode leftRotate(TreeNode x) {
        logger.debug("Rotated " + x + " to right");
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

    /**
     * Insert Tree Node with the Dragon inside
     * @param node Used recursively
     * @param key Value to add
     * @param dragon Dragon
     * @return TreeNode to add
     */
    private TreeNode insert(TreeNode node, int key, Dragon dragon) {

        /* 1.  Perform the normal BST insertion */
        if (node == null)
            if (dragon != null) {
                return (new TreeNode(key, dragon));
            }else{
                return (new TreeNode(key));
            }


        if (key < node.element)
            node.left = insert(node.left, key, dragon);
        else if (key > node.element)
            node.right = insert(node.right, key, dragon);
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

    /* A utility function to print preorder traversal
    // of the tree.
    // The function also prints height of every node
    private void preOrder(TreeNode node) {
        if (node != null) {
            System.out.print(node.element + " ");
            preOrder(node.left);
            preOrder(node.right);
        }
    }
*/

    /**
     * Insert only one Element
     * @param element Value of the element to be added
     */
    public void insert(int element){
        root = insert(root, element, null);
    }

    /**
     * Insert Only one dragon
     * @param dragon Value of the dragon to be added
     */
    public void insert (Dragon dragon){
        root = insert(root, dragon.getAge(), dragon);
    }

    /**
     * gets the Root
     * @return AVL Tree Root
     */
    public TreeNode getRoot() {
        return root;
    }

    /**
     * Private method that insert a Dragon to the AVL Tree
     * @param age Age of the dragon
     * @param dragon Dragon
     */
    private void insertDragonAux(int age, Dragon dragon){
        root = insert(root, age, dragon);
    }

    public void insertDragon(){
        Dragon dragon = new Dragon();
        int age = dragon.getAge();
        insertDragonAux(age, dragon);
    }

    public void insertDragon(int age, Dragon dragon){
        insertDragonAux(age, dragon);
    }

    /**
     * Find the minimum value in the tree
     * @param node Start the search from this Node
     * @return TreeNode with minimum value
     */
    public TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;

        /* loop down to find the leftmost leaf */
        while (current.left != null)
            current = current.left;

        return current;
    }

    /**
     * Find the maximum value in the tree
     * @param node
     * @return TreeNode with maximum value
     */
    public TreeNode maxValueNode(TreeNode node) {
        TreeNode current = node;

        /* loop down to find the rightmost leaf */
        while (current.right != null)
            current = current.right;

        return current;
    }

    /**
     * Deletes the TreeNode with the element selected
     * @param root Tree Root
     * @param key Element that will be eliminated
     * @return TreeNode to be eliminated
     */
    public TreeNode deleteNode(int key){
        return deleteNode(root, key);
    }

    private TreeNode deleteNode(TreeNode root, int key) {
        // STEP 1: PERFORM STANDARD BST DELETE
        if (root == null)
            return root;

        // If the key to be deleted is smaller than
        // the root's key, then it lies in left subtree
        if (key < root.getElement())
            root.left = deleteNode(root.left, key);

            // If the key to be deleted is greater than the
            // root's key, then it lies in right subtree
        else if (key > root.getElement())
            root.right = deleteNode(root.right, key);

            // if key is same as root's key, then this is the node
            // to be deleted
        else
        {

            // node with only one child or no child
            if ((root.left == null) || (root.right == null))
            {
                TreeNode temp = null;
                if (temp == root.left)
                    temp = root.right;
                else
                    temp = root.left;

                // No child case
                if (temp == null)
                {
                    temp = root;
                    root = null;
                }
                else // One child case
                    root = temp; // Copy the contents of
                // the non-empty child
            }
            else
            {

                // node with two children: Get the inorder
                // successor (smallest in the right subtree)
                TreeNode temp = minValueNode(root.right);

                // Copy the inorder successor's data to this node
                root.setElement(temp.getElement());

                // Delete the inorder successor
                root.right = deleteNode(root.right, temp.getElement());
            }
        }

        // If the tree had only one node then return
        if (root == null)
            return root;

        // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE
        root.height = max(height(root.left), height(root.right)) + 1;

        // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether
        // this node became unbalanced)
        int balance = getBalance(root);

        // If this node becomes unbalanced, then there are 4 cases
        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0)
        {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0)
        {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    /**
     * Set the Tree Root
     * @param root New Tree Root
     */
    public void setRoot(TreeNode root) {
        logger.debug("Set " + root + " as " +this + " root");
        this.root = root;
    }
}
