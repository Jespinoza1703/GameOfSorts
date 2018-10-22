package game.logic.trees;

import game.entities.Dragon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TreeNode {

    int element, height;
    TreeNode left;
    TreeNode right;
    Dragon  dragon;
    Logger logger = LoggerFactory.getLogger("TreeNode");


    public TreeNode(int element) {
        this(element, null, null, 1);
        logger.debug("Create New TreeNode");
    }

    public TreeNode(int element, TreeNode left, TreeNode right, int height) {
        this.element = element;
        this.left = left;
        this.right = right;
        this.height = height;
        logger.debug("Create New TreeNode");
    }
    public TreeNode(int element, Dragon dragon){
        this.element = element;
        this.dragon = dragon;
        this.dragon.setAge(element);
        this.left = null;
        this.right = null;
        dragon.setRank("Infantry");
        logger.debug("Create New TreeNode");
    }



    public int getElement() {
        logger.debug("Element of " + this + " get it");
        return element;
    }

    public void setElement(int element) {
        logger.debug("Element of " + this + " set it");
        this.element = element;
    }

    public TreeNode getLeft() {
        logger.debug("Left element of " + this + " get it");
        return left;
    }


    public TreeNode getRight() {
        logger.debug("Right element of " + this + " get it");
        return right;
    }



    public Dragon getDragon() {
        logger.debug("Get it Dragon of " + this);
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        logger.debug("Set dragon of " + this + " as " + dragon);
        this.dragon = dragon;
    }
}
