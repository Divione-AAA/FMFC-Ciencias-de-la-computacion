class Nodo<E> {
    E valor;
    Nodo<E> izquierdo, derecho;
    public Nodo(E e) {
        this.valor = e;
        izquierdo = derecho = null;
    }
    public boolean esHoja() {
        return izquierdo == null && derecho == null;
    }
}

class Comparador<E extends Comparable<E>> {
    public boolean mayor(E a, E b) {
        return a.compareTo(b) > 0;
    }
    public boolean menor(E a, E b) {
        return a.compareTo(b) < 0;
    }
    public boolean igual(E a, E b) {
        return a.compareTo(b) == 0;
    }
}

public class ABB<E extends Comparable<E>> {
    Nodo<E> raiz;
    Comparador<E> c = new Comparador<>();

    public ABB() {
        raiz = null;
    }

    public void insertar(E e) throws Exception {
        raiz = insertarRec(raiz, e);
    }

    private Nodo<E> insertarRec(Nodo<E> nodo, E e) throws Exception {
        if (nodo == null) return new Nodo<>(e);

        if (c.igual(nodo.valor, e)) throw new Exception("Elemento ya existente");

        if (c.mayor(nodo.valor, e)) {
            nodo.izquierdo = insertarRec(nodo.izquierdo, e);
        } else {
            nodo.derecho = insertarRec(nodo.derecho, e);
        }
        return nodo;
    }

    public boolean buscar(E e) throws Exception {
        if (raiz == null) throw new Exception("Árbol vacío");
        Nodo<E> t = raiz;
        while (t != null) {
            if (c.igual(t.valor, e)) return true;
            if (c.mayor(t.valor, e)) t = t.izquierdo;
            else t = t.derecho;
        }
        return false;
    }

    public void eliminar(E e) throws Exception {
        raiz = eliminarRec(raiz, e);
    }

    private Nodo<E> eliminarRec(Nodo<E> nodo, E e) throws Exception {
        if (nodo == null) throw new Exception("Elemento no encontrado");

        if (c.mayor(nodo.valor, e)) {
            nodo.izquierdo = eliminarRec(nodo.izquierdo, e);
        } else if (c.menor(nodo.valor, e)) {
            nodo.derecho = eliminarRec(nodo.derecho, e);
        } else {
            // Caso 1: nodo hoja
            if (nodo.izquierdo == null && nodo.derecho == null) {
                return null;
            }
            // Caso 2: un hijo
            else if (nodo.izquierdo == null) {
                return nodo.derecho;
            } else if (nodo.derecho == null) {
                return nodo.izquierdo;
            }
            // Caso 3: dos hijos
            else {
                Nodo<E> sucesor = minimo(nodo.derecho);
                nodo.valor = sucesor.valor;
                nodo.derecho = eliminarRec(nodo.derecho, sucesor.valor);
            }
        }
        return nodo;
    }

    private Nodo<E> minimo(Nodo<E> nodo) {
        while (nodo.izquierdo != null) {
            nodo = nodo.izquierdo;
        }
        return nodo;
    }

    // Método auxiliar para ver el árbol en inorden
    public void inOrden() {
        inOrdenRec(raiz);
        System.out.println();
    }

    private void inOrdenRec(Nodo<E> nodo) {
        if (nodo != null) {
            inOrdenRec(nodo.izquierdo);
            System.out.print(nodo.valor + " ");
            inOrdenRec(nodo.derecho);
        }
    }
}
