import java.util.*;
class Node<E>{
    E value;
    List<Node<E>> hijos;

    public Node(){
        value = null;
        hijos = null;
    }
}
public class ArbolAdyacencia<E>{
    
    Node<E> raiz = null;

    public ArbolAdyacencia(E valor){
        raiz = new Node<>();
        raiz.value = valor;
    }

    public E raiz()throws Exception{
        if(raiz == null) throw new Exception("El arbol esta vacio");
        else return raiz.value;
    }

    public E padre(E nodo)throws Exception{
        if(raiz == null) throw new Exception("Arbol vacio");
        Node<E> root = raiz;
        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(root);
        while(!cola.isEmpty()){
            root = cola.peek();
            for(Node<E> i : root.hijos){
                if(i.value.equals(nodo)){ 
                    return root.value;
                }else{
                    cola.add(i);
                }
            }
        }
        throw new Exception("Nodo no encontrado");
    }

    public E primerHijo(E elemento)throws Exception{
        if(raiz == null) throw new Exception("Arbol vacio");
        Node<E> root = raiz;
        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(root);
        while(!cola.isEmpty()){
            root = cola.peek();
            for(Node<E> i : root.hijos){
                if(i.value.equals(elemento)){
                    return i.hijos.get(0).value;
                }else{
                    cola.add(i);
                }
            }
        }
        throw new Exception("Nodo no encontrado");
    }

    public E siguienHijo(E elemento)throws Exception{
        if(raiz == null) throw new Exception("Arbol vacio");
        Node<E> root = raiz;
        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(root);
        boolean flag = false;
        while(!cola.isEmpty()){
            root = cola.peek();
            for(Node<E> i : root.hijos){
                if(flag) return i.value;
                if(i.value.equals(elemento)){
                    flag = true;
                }else{
                    cola.add(i);
                }
            }
        }
        throw new Exception("Nodo no encontrado");
    }

    public void insertaHijo(E padre, E elemento)throws Exception{
        if(raiz == null) throw new Exception("Arbol vacio");
        Node<E> root = raiz;
        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(root);
        while(!cola.isEmpty()){
            root = cola.peek();
            for(Node<E> i : root.hijos){
                if(i.value.equals(padre)){
                    Node<E> t = new Node<>();
                    t.value = elemento;
                    i.hijos.add(t);
                    return;
                }else{
                    cola.add(i);
                }
            }
        }
        throw new Exception("Padre no encontrado");
    }
    public void insertaHermano(E hermano, E elemento)throws Exception{
        if(raiz == null) throw new Exception("Arbol vacio");
        Node<E> root = raiz;
        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(root);
        while(!cola.isEmpty()){
            root = cola.peek();
            for(Node<E> i : root.hijos){
                if(i.value.equals(hermano)){
                    Node<E> t = new Node<>();
                    t.value = elemento;
                    root.hijos.add(t);
                    return;
                }else{
                    cola.add(i);
                }
            }
        }
        throw new Exception("Padre no encontrado");
    }

    public void eliminar(E elemento)throws Exception{
        if(raiz == null) throw new Exception("Arbol vacio");
        Node<E> root = raiz;
        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(root);
        boolean flag = false;
        while(!cola.isEmpty()){
            root = cola.peek();
            for(Node<E> i : root.hijos){
                if(i.value.equals(elemento) && !flag){
                    cola.clear();
                    flag = true;
                }              
                cola.add(i);
                if(flag){
                    root.hijos.remove(i);
                }
            }
        }
        throw new Exception("Elemento no encontrado");
    }

    public boolean esVacia(){
        return raiz == null;
    }

    public void vaciar(){
        raiz = null;
    }
}
