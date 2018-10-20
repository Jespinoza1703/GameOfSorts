package game.logic.lists;

import game.entities.Dragon;

public class SimpleList<T> {

    private Node first;
    private int large;

    public SimpleList(){
        this.first = null;
        this.large = 0;
    }

    /**
     * Agrega el elemento al final de la lista
     * @param value elemento a agregar
     */
    public void addAtEnd(T value){
        if(this.isEmpty()){
            this.first = new Node<>(value);
            this.large += 1;
        }
        else{
            Node temporal = this.first;
            while (temporal.getNext() != null){
                temporal =  temporal.getNext();
            }
            temporal.setNext(new Node<>(value));
            this.large += 1;
        }
    }

    /**
     * Agrega elemento al inicio de la lista
     * @param value elemento a agregar
     */
    public void addAtBeginning (T value){
        if(this.isEmpty()){
            this.first = new Node<>(value);
            this.large += 1;
        }
        else{
            Node tmp = new Node<>(value);
            tmp.setNext(this.first);
            this.first = tmp;
            this.large += 1;
        }
    }

    /**
     * Verifica si la lista está vacía
     * @return boolean
     */
    public boolean isEmpty(){
        Node next = this.getFirst();
        return next == null;
    }

    /**
     * Obtiene el primer elemento de la lista
     * @return primer elemento de la lista
     */
    public Node getFirst() {
        return first;
    }


    /**
     * Obtiene el largo de la lista
     * @return int que significa el largo de la lista
     */
    public int getLarge() {
        return large;
    }

    /**
     * Limpia la lista por completo
     */
    public void clearOut(){
        this.first = null;
    }



    /**
     * Swaps the values of two nodes
     * @param i first node index
     * @param j second node index
     */
    public void swap(int i, int j){
        Node node1 = this.getByIndex(i);
        Node node2 = this.getByIndex(j);

        var tmp = node1.getValue();
        node1.setValue(node2.getValue());
        node2.setValue(tmp);
    }

    /**
     * Elimina un elemento en la posición que se le indique
     * @param index posición de elemento a eliminar
     */
    public void delete(int index){
        if (index < this.getLarge()){
            int i = 0;
            Node temporal = this.first;
            if (index == 0){
                this.first = this.getByIndex(1);
            }
            else {
                while (temporal != null) {

                    if (i == index) {
                        this.getByIndex(i-1).setNext(temporal.getNext());
                        this.large -= 1;
                        break;
                    } else {
                        temporal = temporal.getNext();
                    }
                    i += 1;
                }
            }
        }
        else{
            System.out.println("List out of index");
        }
    }

    /**
     * Obtiene el elemento que se encuentra en un determinado índice
     * @param i posición de elemento a obtener
     * @return un elemento de la lista
     */
    public Node<T> getByIndex(int i){
        int j = 0;
        Node temp = this.getFirst();
        while (j != i){
            temp = temp.getNext();
            j++;
        }
        return temp;
    }

    public void addDragon (Dragon dragon){
        if(this.isEmpty()){
            this.first = new Node(dragon);
            this.large += 1;
        }
        else{
            Node temporal = this.first;
            while (temporal.getNext() != null){
                temporal =  temporal.getNext();
            }
            temporal.setNext(new Node(dragon));
            this.large += 1;
        }
    }

    public void swapDragon (int i, int j){
        Dragon temp = this.getByIndex(i).getDragon();
        this.getByIndex(i).setDragon(this.getByIndex(j).getDragon());
        this.getByIndex(j).setDragon(temp);
    }
}
