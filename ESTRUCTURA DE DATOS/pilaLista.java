public class pilaLista<E>{
    private static class Nodo<E>{
        E value;
        Nodo<E> next,prev;

        Nodo(E v){
            this.value = v;
            this.next = null;
            this.prev = null;
        }
    }

    Nodo<E> nodo;
    int size;

    public pilaLista(){
        nodo = null;
        size = 0;
    }

    public void push(E e){
        Nodo<E> t = new Nodo<>(e);
        if(nodo == null){
            nodo = t;
        }else{
            nodo.next = t;
            t.prev = nodo;
            nodo = t;
        }
        size++;
        System.out.println("puto");
    }

    public boolean isEmpty(){
        return nodo == null;
    }

    public E pop() throws Exception{
        if(isEmpty()) throw new Exception("Esta vacia");
        E t = nodo.value;
        nodo = nodo.prev;
        return t;
    }
    public static void main(String[] args) {
        pilaLista<Integer> t = new pilaLista<>();
        t.push(14);
        t.push(14);
        t.push(14);
    }
}
