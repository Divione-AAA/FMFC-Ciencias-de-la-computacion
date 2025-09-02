import java.util.*;
import java.util.List;

class Node<E>{
    E value;
    List<Node<E>> hijos;

    public Node(){
        value = null;
        hijos = new ArrayList<>();
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
            root = cola.poll();
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
            root = cola.poll();
            if(root.value.equals(elemento)){
                return root.hijos.getFirst().value;
            }
            for(Node<E> i : root.hijos){
                if(i.value.equals(elemento)){
                    return i.hijos.getFirst().value;
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
            root = cola.poll();
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
        Node<E> t = new Node<>();
        t.value = elemento;

        while(!cola.isEmpty()){
            root = cola.poll();

            if(root.value.equals(padre)){
                root.hijos.add(t);
                return;
            }

            for(Node<E> i : root.hijos){
                if(i.value.equals(padre)){
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
            root = cola.poll();
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
        if (raiz == null) throw new Exception("Árbol vacío");

        if (raiz.value.equals(elemento)) {
            raiz = null; // Caso especial: borrar raíz
            return;
        }

        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(raiz);

        while (!cola.isEmpty()) {
            Node<E> root = cola.poll();

            // Usar un iterador para recorrer los hijos
            Iterator<Node<E>> it = root.hijos.iterator();
            while (it.hasNext()) {
                Node<E> hijo = it.next();
                if (hijo.value.equals(elemento)) {
                    it.remove(); // ✅ eliminar seguro
                    return;      // ✅ salir, ya se eliminó
                }
                cola.add(hijo);
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
