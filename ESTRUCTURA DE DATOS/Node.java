public class Node<E> {
    E value;
    Node<E> next;

    Node(E v){
        this.value = v;
        this.next = null;
    }
}
