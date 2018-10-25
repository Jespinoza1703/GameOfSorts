package game.logic.lists;

import game.entities.Dragon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Node <T> {

    private Dragon dragon;
    private T value = null;
    private Node next = null;
    private static Logger logger = LoggerFactory.getLogger("IntList");


    /***
     * Constructor with int as parameter
     * @param value Value of the Node that will be add
     */
    public Node(T value){
        logger.debug("Created New Node");
        this.value = value;
    }

    /**
     * Constructor with a dragon as parameter
     * @param dragon Dragon that will be added
     */
    public Node(Dragon dragon){
        logger.debug("Created new Node with a Dragon");
        this.setDragon(dragon);
    }

    /** Setters and Getters **/

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Dragon getDragon() {
        return dragon;
    }

    public void setDragon(Dragon dragon) {
        this.dragon = dragon;
    }
}
