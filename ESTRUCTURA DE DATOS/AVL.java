class Nodo<E>{
    E valor;
    int altura;
    Nodo<E> izquierdo, derecho;

    public Nodo(E e) {
        this.valor = e;
        altura = 1;
        izquierdo = derecho = null;
    }

    public int factorDeBalanceo() {
        int nodoDerecho = (derecho == null ? 0 : derecho.altura);
        int nodoIzquierdo = (izquierdo == null ? 0 : izquierdo.altura);
        this.altura = Math.max(nodoIzquierdo, nodoDerecho) + 1;
        return nodoIzquierdo - nodoDerecho;
    }
}

public class AVL<E extends Comparable<E>> {
    private Nodo<E> raiz;

    public AVL() {
        raiz = null;
    }

    // Inserción pública
    public void insertar(E e) throws Exception{
        raiz = insertar(raiz, e);
    }

    // Inserción recursiva
    private Nodo<E> insertar(Nodo<E> nodo, E e) throws Exception {
        if (nodo == null) return new Nodo<>(e);
        if (e.compareTo(nodo.valor) < 0) {
            nodo.izquierdo = insertar(nodo.izquierdo, e);
        } else if (e.compareTo(nodo.valor) > 0) {
            nodo.derecho = insertar(nodo.derecho, e);
        } else {
            throw new Exception("Nodo repetido");
        }
        return balancearNodo(nodo);
    }

    // Eliminación pública
    public void eliminar(E e) throws Exception {
        raiz = eliminar(raiz, e);
    }

    // Eliminación recursiva
    private Nodo<E> eliminar(Nodo<E> nodo, E e) throws Exception {
        if (nodo == null) return null;
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
        return balancearNodo(nodo);
    }

    // Balanceo centralizado
    private Nodo<E> balancearNodo(Nodo<E> nodo) {
        if (nodo == null) return null;

        int balance = nodo.factorDeBalanceo();
        // Caso LL
        if (balance > 1 && nodo.izquierdo.factorDeBalanceo() >= 0) {
            return rotacionDerecha(nodo);
        }
        // Caso RR
        if (balance < -1 && nodo.derecho.factorDeBalanceo() <= 0) {
            return rotacionIzquierda(nodo);
        }
        // Caso LR
        if (balance > 1 && nodo.izquierdo.factorDeBalanceo() < 0) {
            nodo.izquierdo = rotacionIzquierda(nodo.izquierdo);
            return rotacionDerecha(nodo);
        }
        // Caso RL
        if (balance < -1 && nodo.derecho.factorDeBalanceo() > 0) {
            nodo.derecho = rotacionDerecha(nodo.derecho);
            return rotacionIzquierda(nodo);
        }
        return nodo;
    }

    // Rotación derecha
    private Nodo<E> rotacionDerecha(Nodo<E> y) {
        Nodo<E> x = y.izquierdo;
        Nodo<E> t2 = x.derecho;
        x.derecho = y;
        y.izquierdo = t2;
        y.factorDeBalanceo();
        x.factorDeBalanceo();
        return x;
    }

    // Rotación izquierda
    private Nodo<E> rotacionIzquierda(Nodo<E> x) {
        Nodo<E> y = x.derecho;
        Nodo<E> t2 = y.izquierdo;
        y.izquierdo = x;
        x.derecho = t2;
        y.factorDeBalanceo();
        x.factorDeBalanceo();
        return y;
    }

    // Encuentra el mínimo
    private Nodo<E> minimo(Nodo<E> nodo) {
        while (nodo.izquierdo != null) nodo = nodo.izquierdo;
        return nodo;
    }

    // Recorrido en orden
    public void inOrden() {
        inOrden(raiz);
        System.out.println();
    }

    private void inOrden(Nodo<E> nodo) {
        if (nodo != null) {
            inOrden(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inOrden(nodo.derecho);
        }
    }

    // MAIN DE PRUEBA
    public static void main(String[] args) {
        try {
            AVL<Integer> arbol = new AVL<>();

            int[] valores = {10, 20, 30, 40, 50, 25};
            System.out.println("Insertando: ");
            for (int v : valores) {
                arbol.insertar(v);
                arbol.inOrden();
            }

            System.out.println("\nEliminando 40:");
            arbol.eliminar(40);
            arbol.inOrden();

            System.out.println("\nEliminando 25:");
            arbol.eliminar(25);
            arbol.inOrden();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}