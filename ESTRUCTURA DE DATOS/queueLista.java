class Node<E>{
    E value;
    Node<E> next;

    Node(E v){
        this.value = v;
        this.next = null;
    }
}

public class queueLista<E>{
    private Node<E> front, back; 
    private int size;
    
    public queueLista(){
        front = back = null;
        size = 0;
    }

    public void pushback(E e){
        Node<E> newNode = new Node<>(e);

        if(back != null){
            back.next = newNode;
        }

        back = newNode;

        if(front == null){
            front = newNode;
        }

        size++;
    }

    public boolean isempty(){
        return front == null;
    }

    public E popfront()throws Exception{
        
        if(isempty()){
            throw new Exception("Esta vacia");
        }

        E t = front.value;
        
        front = front.next;
        
        if(front == null){
            back = null;
        }
        
        size--;
        
        return t;
    }
    public static void main(String[] args) {
        queueLista<Integer> q = new queueLista<>();
        q.pushback(54);
        q.pushback(58);
        q.pushback(57);
        q.pushback(21);
    }
}
