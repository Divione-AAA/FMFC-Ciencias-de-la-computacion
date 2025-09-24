class Nodo<E>{
    E valor;
    Nodo<E> izquierdo,derecho;
    public Nodo(E e){
        this.valor = e;
        izquierdo = derecho = null;
    }
    public boolean esHoja(){
        return izquierdo == null && derecho == null;
    }
}
class Comparador<E extends Comparable<E>>{
    public boolean compare(E a,E b){
        return a.compareTo(b) > 0;
    }
}

public class ABB<E extends Comparable<E>>{
    Nodo<E> raiz;
    Comparador<E> c = new Comparador<>();
    
    public ABB(){
        raiz = null;
    }

    public void insertar(E e) throws Exception{
        if(raiz == null){
            raiz = new Nodo<>(e);
        }else{
            Nodo<E> t = raiz;
                while(t != null){ 
                    if(t.valor == e) throw new Exception("Elemento ya existente");
                    if(c.compare(t.valor,e)) t = t.izquierdo;
                    else t = t.derecho;
                }
            if(c.compare(t.valor,e)) t.izquierdo = new Nodo<>(e);
            else t.derecho = new Nodo<>(e);
        }
    }

    public boolean buscar(E e)throws Exception{
        if(raiz == null) throw new Exception("Arbol vacio");
        Nodo<E> t = raiz;
        while(true){
            if(t.valor == e) return true;
            if(t.esHoja()) return false;
            if(c.compare(t.valor,e)) t = t.derecho;
            else t = t.izquierdo;
        }
    }
} 
