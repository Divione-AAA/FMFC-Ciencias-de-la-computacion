import java.util.*;

class Node<E> {
    E value;
    List<Node<E>> child;

    Node(E v){
        this.value = v;
        this.child = new ArrayList<>();
    }
}

public class ArbolHijos<E>{

    Node<E> root;

    public ArbolHijos(E e) {
        root = new Node<E>(e);
    }

    public void insertar(E padre, E valor){
        Node<E> f = find(root,padre);
        if(padre != null){
            f.child.add(new Node<E>(valor));
        }
    }
    
    private Node<E> find(Node<E> padre, E valor){
        if(padre == null) return null;
        if(padre.value == valor) return padre;
        for(Node<E> i: padre.child){
            Node<E> found = find(i,valor);
            if(found != null) return found;
        }
        return null;
    }

    public void delete(E valor){
        if(root == null) return;
        if(root.value == valor){
            root = null;
            return;
        }
        deleteOnCascade(root,valor);
    }

    private void deleteOnCascade(Node<E> root, E valor){
        Iterator<Node<E>> iterador = root.child.iterator();
        while(iterador.hasNext()){
            Node<E> child = iterador.next();
            if(child.value == valor){
                iterador.remove();
                return;
            }else{
                deleteOnCascade(child, valor);
            }
        }
    }

    public void preorden(Node<E> node){
        if(node == null) return;
        System.out.println(node.value + " ");
        for(Node<E> i: node.child){
            preorden(i);
        }
    }

    public void postorden(Node<E> node){
        Queue<Node<E>> cola = new LinkedList<>();
        cola.add(node);
        while(!cola.isEmpty()){
            Node<E> t = cola.poll();
            System.out.println(t.value + " ");
            cola.addAll(t.child); //adiciona todos los elementos de la lista
        }
    }
}