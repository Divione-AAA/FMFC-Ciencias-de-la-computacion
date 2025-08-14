class Node<E> {
    E value;
    Node<E> next,prev;

    Node(E v){
        this.value = v;
        this.next = null;
        this.prev = null;
    }
}

public class pilaLista<E>{
    Node<E> node;
    int size;

    public pilaLista(){
        node = null;
        size = 0;
    }

    public void push(E e){
        Node t = new Node<>(e);
        Node<E> t2;
        if(node == null){
            node = t;
        }else{
            node.next = t;
            t2 = node;
            node = t;
            node.prev = t2;
            
        }
        size++;
    }

    public boolean isEmpty(){
        return node == null;
    }

    public E pop() throws Exception{
        if(isEmpty()) throw new Exception("Esta vacia");
        E t = node.value;
        node = node.prev;
        return t;
    }
}
