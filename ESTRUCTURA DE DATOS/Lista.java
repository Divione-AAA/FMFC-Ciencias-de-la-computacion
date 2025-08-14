class Node<E>{
    E value;
    Node<E> next;

    Node(E v){
        this.value = v;
        this.next = null;
    }
}

public class Lista<E>{
    private Node<E> head;
    private int size;

    public Lista(){
        head = null;
        size = 0;
    }

    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head = newNode;
        size++;
    }

    public void addLast(E e){
        Node<E> newNode = new Node<>(e);
        if(head == null) head = newNode;
        else{
            Node<E> t =  head;
            while(t.next != null){
                t = t.next;
            }
            t.next =  newNode;
        }
        size++;
    }

    public void removeFirst(){
        if(head != null){
            head = head.next;
            size--;
        }
    }
}