import java.util.*;

public class ArbolLista<E extends Comparable<E>> {
    private static class Nodo<E extends Comparable<E>> {
        E valor;
        Nodo<E> hijo;
        Nodo<E> hermanoDerecho;

        Nodo(E v) {
            valor = v;
            hijo = null;
            hermanoDerecho = null;
        }
    }

    private Nodo<E> raiz;

    public ArbolLista() {
        raiz = null;
    }

    public void insertarHijo(E elemento, E padre) {
        Nodo<E> nuevo = new Nodo<>(elemento);

        if (padre == null) {
            if (raiz == null) {
                raiz = nuevo;
            } else {
                System.out.println("Ya existe una raíz. No se puede insertar otra sin padre.");
            }
            return;
        }

        Queue<Nodo<E>> q = new LinkedList<>();
        q.add(raiz);

        while (!q.isEmpty()) {
            Nodo<E> nodo = q.poll();
            if (nodo.valor.compareTo(padre) == 0) {
                if (nodo.hijo == null) {
                    nodo.hijo = nuevo;
                } else {
                    Nodo<E> actual = nodo.hijo;
                    while (actual.hermanoDerecho != null) {
                        actual = actual.hermanoDerecho;
                    }
                    actual.hermanoDerecho = nuevo;
                }
                return;
            }
            if (nodo.hijo != null) q.add(nodo.hijo);
            Nodo<E> hermano = nodo.hermanoDerecho;
            while (hermano != null) {
                q.add(hermano);
                hermano = hermano.hermanoDerecho;
            }
        }
    }

    public void eliminar(E elemento) throws Exception {
        if (raiz == null) throw new Exception("No existe ese elemento");

        if (raiz.valor.compareTo(elemento) == 0) {
            raiz = null;
            return;
        }

        Queue<Nodo<E>> q = new LinkedList<>();
        q.add(raiz);

        while (!q.isEmpty()) {
            Nodo<E> nodo = q.poll();
            Nodo<E> actual = nodo.hijo;
            Nodo<E> anterior = null;

            while (actual != null) {
                if (actual.valor.compareTo(elemento) == 0) {
                    if (anterior == null) {
                        nodo.hijo = actual.hermanoDerecho;
                    } else {
                        anterior.hermanoDerecho = actual.hermanoDerecho;
                    }
                    return;
                }
                q.add(actual);
                anterior = actual;
                actual = actual.hermanoDerecho;
            }
        }

        throw new Exception("No existe ese elemento");
    }

    public void imprimir() {
        imprimirRecursivo(raiz, 0);
    }

    private void imprimirRecursivo(Nodo<E> nodo, int nivel) {
        if (nodo == null) return;
        System.out.println("  ".repeat(nivel) + "- " + nodo.valor);
        imprimirRecursivo(nodo.hijo, nivel + 1);
        imprimirRecursivo(nodo.hermanoDerecho, nivel);
    }

    public static void main(String[] args) throws Exception {
        ArbolLista<String> arbol = new ArbolLista<>();

        arbol.insertarHijo("A", null);     // raíz
        arbol.insertarHijo("B", "A");      // hijo de A
        arbol.insertarHijo("C", "A");      // otro hijo de A
        arbol.insertarHijo("D", "B");      // hijo de B
        arbol.insertarHijo("E", "B");      // otro hijo de B
        arbol.insertarHijo("F", "C");      // hijo de C

        System.out.println("Arbol original:");
        arbol.imprimir();

        arbol.eliminar("B"); // elimina B y su subárbol
        System.out.println("\nArbol después de eliminar 'B':");
        arbol.imprimir();
    }
}
