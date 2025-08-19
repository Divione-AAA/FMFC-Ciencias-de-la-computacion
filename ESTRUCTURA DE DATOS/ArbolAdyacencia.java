import java.util.*;
class Node<E>{
    E value;
    List<Node<E>> child;

    Node(E v){
        this.value = v;
        this.child = new ArrayList<>();
    }
}
public class ArbolAdyacencia<E>{
    ArrayList<E>[] adj;
    E root;

    public ArbolAdyacencia(){
        root = null;
        for(int i = 0;i < 10000;i++){
            adj[i] = new ArrayList<>();
        }
    }

    public void add(int padre, E valor){
        if(root == null) root = valor;
        adj[padre].add(valor);
    }

    public void delete(int padre, E valor){
        if(root == valor) root = null;
        else{adj[padre].remove(valor); 
        deleteOnCascade(padre);}
    }

    private void deleteOnCascade(int padre){
        for(E i: adj[padre]){
            deleteOnCascade(i);
        }
    }

}
