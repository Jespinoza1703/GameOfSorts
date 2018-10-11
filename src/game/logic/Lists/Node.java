package game.logic.Lists;

public class Node <T> {

    private T value = null;
    private Node next = null;

    public Node(T value){
        this.value = value;
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
}
