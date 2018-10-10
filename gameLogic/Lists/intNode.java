package Lists;

public class intNode {
    private int value;

    /**
     * Punteros
     */
    // Pointers
    intNode next = null;


    /**
     * Sets a node to contain data
     *
     * @param value a type variable
     */
    public intNode(int value) {
        this.value = value;
    }

    // @Overload
    public intNode() {
    }


    /**
     * Setters and Getters
     **/
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