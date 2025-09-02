import java.util.LinkedList;
import java.util.Queue;

class Node<E>{

    E value;
    Node<E> left,rigth;

    public Node() {
        left = rigth = null;
    }
}

public class ArbolBinario<E>{
    Node<E> root;
    
    public ArbolBinario(){
        root = null;
    }

    public E raiz() throws Exception{
        if(root == null){
            throw new Exception("Arbol vacio");
        }else{
            return root.value;
        }
    }

    public E padre(E value)throws Exception{
        if(root == null) throw new Exception("Arbol vacio");
        Node<E> raiz = root;
        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(raiz);
        while(!cola.isEmpty()){
            raiz = cola.peek();
            if(raiz.left != null) cola.add(raiz.left);
            if(raiz.rigth != null) cola.add(raiz.rigth);
            if(raiz.left.equals(value)) return raiz.value;
            if(raiz.rigth.equals(value)) return raiz.value;
        }
        throw new Exception("Nodo no encontrado");
    }

    public void insertarHijo(E value, E padre){
        if(root == null && padre == null){ 
            root.value = value;
            return;
        }
        Node<E> raiz = root;
        Node<E> t = new Node<>();
        t.value = value;
        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(raiz);
        while(!cola.isEmpty()){
            raiz = cola.peek();
            if(raiz.left != null) cola.add(raiz.left);
            if(raiz.rigth != null) cola.add(raiz.rigth);
            if(raiz.left.equals(padre)){
                raiz.rigth = t;
                return;
            }
            if(raiz.rigth.equals(padre)){
                raiz.rigth = t;
                return;
            }
        }
    }
}
