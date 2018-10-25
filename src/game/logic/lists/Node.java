package game.logic.lists;

import game.entities.Dragon;

public class Node <T> {

    private Dragon dragon;
    private T value = null;
    private Node next = null;

    /***
     * Constructor with int as parameter
     * @param value
     */
    public Node(T value){
        this.value = value;
    }

    /**
     * Constructor with a dragon as parameter
     * @param dragon
     */
    public Node(Dragon dragon){
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
