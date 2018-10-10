package Lists;

public class intList {
    private intNode first;
    private int large;

    public intList(){
        this.first = null;
        this.large = 0;
    }

    /**
     * Agrega el elemento al final de la lista
     * @param value elemento a agregar
     */
    public void addAtEnd(int value){
        if(this.isEmpty()){
            this.first = new intNode(value);
            this.large += 1;
        }
        else{
            intNode temporal = this.first;
            while (temporal.getNext() != null){
                temporal =  temporal.getNext();
            }
            temporal.setNext(new intNode(value));
            this.large += 1;
        }
    }

    /**
     * Agrega elemento al inicio de la lista
     * @param value elemento a agregar
     */
    public void addAtBeginning (int value){
        if(this.isEmpty()){
            this.first = new intNode(value);
            this.large += 1;
        }
        else{
            intNode tmp = new intNode(value);
            tmp.setNext(this.first);
            this.first = tmp;
        }
    }

    /**
     * Verifica si la lista está vacía
     * @return boolean
     */
    public boolean isEmpty(){
        intNode next = this.getFirst();
        return next == null;
    }

    /**
     * Obtiene el primer elemento de la lista
     * @return primer elemento de la lista
     */
    public intNode getFirst() {
        return first;
    }

    /**
     * Imprime el largo de la lista
     */
    public void showLarge() {
        System.out.println(this.getLarge());
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
     * Imprime los elementos de la lista
     */
    public void showList(){
        intNode temporal = this.first;
        while (temporal != null){
            System.out.println(temporal.getValue());
            temporal = temporal.getNext();
        }
    }

    /**
     * Elimina un elemento en la posición que se le indique
     * @param index posición de elemento a eliminar
     */
    public void delete(int index){
        if (index < this.getLarge()){
            int i = 0;
            intNode temporal = this.first;
            if (index == 0){
                this.first = this.getByIndex(1);
            }
            else {
                while (temporal.getNext() != null) {

                    if (i == index) {
                        temporal.setNext(temporal.getNext().getNext());
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
    public intNode getByIndex(int i){
        int j = 0;
        intNode temp = this.getFirst();
        while (j != i ){
            temp = temp.getNext();
            j++;
        }
        return temp;
    }


    public void setFirst(intNode first) {
        this.first = first;
    }


    public void swap (int i, int j, intList list){
        if (i != j) {
            if(i == 0 && j == list.getLarge() -1){
                intNode n1 = getByIndex(i);
                intNode n2 = getByIndex(j);
                if (n1.getNext() != n2){
                    intNode n1Next = n1.getNext();
                    intNode n2Prev = list.getByIndex(j-1);
                    n2Prev.setNext(n1);
                    n2.setNext(n1Next);
                    setFirst(n2);
                    return;

                }
                list.setFirst(n2);
                n2.setNext(n1);
                n1.setNext(null);
                return;
            }

            if (i == 0){
                intNode n1 = getByIndex(i);
                intNode n2 = getByIndex(j);
                intNode n1Next = n1.getNext();
                if (n1Next != n2) {
                    intNode n2Prev = getByIndex(j-1);
                    n2Prev.setNext(n1);
                    n1.setNext(n2.getNext());
                    n2.setNext(n1Next);
                    list.setFirst(n2);
                    return;
                }
                n1.setNext(n2.getNext());
                n2.setNext(n1);
                list.setFirst(n2);
                return;
            }
            if (j == list.getLarge() -1){
                intNode n1 = getByIndex(i);
                intNode n2 = getByIndex(j);
                intNode previous = list.getByIndex(i - 1);
                if (n1.getNext() != n2){
                    intNode n1Next = n1.getNext();
                    intNode n2Prev = getByIndex(j-1);
                    previous.setNext(n2);
                    n2.setNext(n1Next);
                    n2Prev.setNext(n1);
                    n1.setNext(null);
                    return;
                }
                previous.setNext(n2);
                n2.setNext(n1);
                n1.setNext(null);
                return;
            } else {
                intNode n1Prev = getByIndex(i - 1);
                intNode n1 = getByIndex(i);
                intNode n2 = getByIndex(j);
                intNode n2Next = getByIndex(j).getNext();
                if (n1.getNext() !=  n2){
                    intNode n1Next = n1.getNext();
                    intNode n2Prev = getByIndex(j-1);
                    n1Prev.setNext(n2);
                    n2.setNext(n1Next);
                    n2Prev.setNext(n1);
                    n1.setNext(n2Next);
                    return;
                }


                n1Prev.setNext(n2);
                n2.setNext(n1);
                n1.setNext(n2Next);
                return;
            }
        }
    }

    public void addUnique(int value){
        if (!isRepeated(value, this)) {
            if(this.isEmpty()){
                this.first = new intNode(value);
                this.large += 1;
            }
            else{
                intNode temporal = this.first;
                while (temporal.getNext() != null){
                    temporal =  temporal.getNext();
                }
                temporal.setNext(new intNode(value));
                this.large += 1;
            }
        }else{
            System.out.print("is repeated");
        }

    }

    private boolean isRepeated(int value, intList list){
        int i = 0;
        int j = list.getLarge();
        boolean result = false;
        while (i < j){
            if(value == list.getByIndex(i).getValue()){
                result = true;
                break;
            }
            i++;
        }
        return result;
    }
}
