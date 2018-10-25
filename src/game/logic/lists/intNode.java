package game.logic.lists;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class intNode {

    private int value;
    intNode next = null;
    private static Logger logger = LoggerFactory.getLogger("IntNode");


    /**
     * Sets a node to contain data
     * @param value a type variable
     */
    public intNode(int value) {
        logger.debug("Created new intNode");
        this.value = value;
    }

    /** Setters and Getters **/

    public intNode getNext() {
        return next;
    }

    public void setNext(intNode next) {
        this.next = next;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
