class Nodo<E>{
    E valor;
    int altura;
    Nodo<E> izquierdo,derecho;
    
    public Nodo(E e){
        this.valor = e;
        altura = 1;
        izquierdo = derecho = null;
    }

    public int factorDeBalanceo(){
        int nodoDerech = (derecho == null ? 0 : derecho.altura);
        int nodoIzquierdo = (izquierdo == null ? 0 : izquierdo.altura);
        this.altura = Math.max(derecho.altura, izquierdo.altura) + 1;
        return nodoIzquierdo - nodoDerech;
    }
}

public class ABAB<E extends Comparable<E>>{
    Nodo<E> raiz;
    public ABAB(){
        raiz = null;
    }
    public void insertar(E e)throws Exception{
        if(raiz == null){
            raiz = new Nodo<>(e);
            return;
        }
        insertar(raiz,e);
    }

    private Nodo<E> insertar(Nodo<E> nodo, E e)throws Exception{
        if(nodo == null) return new Nodo<>(e);

        //aqui insertamos

        if(e.compareTo(nodo.valor)<0){
            nodo.izquierdo = insertar(nodo.izquierdo, e);
        }else if(e.compareTo(nodo.valor)>0){
            nodo.derecho = insertar(nodo.derecho, e);
        }else{
            throw new Exception("Nodo repetido");
        }

        //balanceamos

        int balance = nodo.factorDeBalanceo();

        //LL
        if(balance > 1 && e.compareTo(nodo.izquierdo.valor) < 0){
            rotacionDerecha(nodo);
        }
        //RR
        if(balance < -1 && e.compareTo(nodo.derecho.valor) > 0){
            rotacionDerecha(nodo);
        }
        //LR
        if(balance > 1 && e.compareTo(nodo.izquierdo.valor) > 0){
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        //Rl
        if(balance < -1 && e.compareTo(nodo.derecho.valor) < 0){
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }
        //retornar nodo ya balanceado
        return nodo;

    }

    public void eliminar(E e)throws Exception{
        if(raiz == null) return;
        eliminar(raiz,e);
    }

    private Nodo<E> eliminar(Nodo<E> nodo, E e)throws Exception{
        if(nodo == null) return null;
        //eliminamos el subarbol

        if (e.compareTo(nodo.valor) < 0) {
            nodo.izquierdo = eliminar(nodo.izquierdo, e);
        } else if (e.compareTo(nodo.valor) > 0) {
            nodo.derecho = eliminar(nodo.derecho, e);
        } else {
            
            if (nodo.izquierdo == null || nodo.derecho == null) {
                nodo = (nodo.izquierdo != null) ? nodo.izquierdo : nodo.derecho;
            } else {
                Nodo<E> sucesor = minimo(nodo.derecho);
                nodo.valor = sucesor.valor;
                nodo.derecho = eliminar(nodo.derecho, sucesor.valor);
            }
        }

        nodo.factorDeBalanceo();

        //balanceamos

        int balance = nodo.factorDeBalanceo();

        //LL
        if(balance > 1 && e.compareTo(nodo.izquierdo.valor) < 0){
            rotacionDerecha(nodo);
        }
        //RR
        if(balance < -1 && e.compareTo(nodo.derecho.valor) > 0){
            rotacionDerecha(nodo);
        }
        //LR
        if(balance > 1 && e.compareTo(nodo.izquierdo.valor) > 0){
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        //Rl
        if(balance < -1 && e.compareTo(nodo.derecho.valor) < 0){
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }
        //retornar nodo ya balanceado
        return nodo;
    }


    private Nodo<E> rotacionDerecha(Nodo<E> y){
        Nodo<E> x = y.izquierdo;
        Nodo<E> t2 = x.derecho;
        //rotar
        x.derecho = y;
        y.izquierdo = t2;
        //actualizar factor
        y.factorDeBalanceo();
        x.factorDeBalanceo();
        return x;
    }

    private Nodo<E> rotacionIzquierda(Nodo<E> x){
        Nodo<E> y = x.derecho;
        Nodo<E> t2 = y.izquierdo;
        //rotar
        y.izquierdo = x;
        x.derecho = t2;
        //actualizar factor
        y.factorDeBalanceo();
        x.factorDeBalanceo();
        return y;
    }

    private Nodo<E> minimo(Nodo<E> nodo) {
    while (nodo.izquierdo != null) nodo = nodo.izquierdo;
    return nodo;
    }
}