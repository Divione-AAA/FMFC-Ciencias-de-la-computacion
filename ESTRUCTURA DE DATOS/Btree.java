import java.util.*;

class Nodo<E extends Comparable<E>> {
    int t;
    List<E> llave;
    List<Nodo<E>> hijos;
    boolean hoja;

    public Nodo(int t, boolean hoja) {
        this.t = t;
        this.llave = new ArrayList<>();
        this.hijos = new ArrayList<>();
        this.hoja = hoja;
    }

    public Nodo<E> buscar(E e) {
        int i = 0;
        while (i < llave.size() && e.compareTo(llave.get(i)) > 0) {
            i++;
        }
        if (i < llave.size() && e.compareTo(llave.get(i)) == 0) {
            return this;
        }
        if (hoja) {
            return null;
        }
        return hijos.get(i).buscar(e);
    }
}

public class Btree<E extends Comparable<E>> {
    Nodo<E> raiz;
    int t;

    public Btree(int t) {
        this.t = t;
        this.raiz = null;
    }

    public Nodo<E> buscar(E e) throws Exception {
        if (raiz == null) {
            throw new Exception("No hay nodos");
        }
        return raiz.buscar(e);
    }

    public void insertar(E e) throws Exception {
        if (raiz == null) {
            raiz = new Nodo<E>(t, true);
            raiz.llave.add(e);
        } else {
            if (raiz.llave.size() == 2 * t - 1) {
                Nodo<E> nuevo = new Nodo<E>(t, false);
                nuevo.hijos.add(raiz);
                dividir(nuevo, 0, raiz);
                int i = 0;
                if (nuevo.llave.get(0).compareTo(e) < 0) {
                    i++;
                }
                insertarNoFull(nuevo.hijos.get(i), e);
                raiz = nuevo;
            } else {
                insertarNoFull(raiz, e);
            }
        }
    }

    // insertar en un nodo no completo
    private void insertarNoFull(Nodo<E> nodo, E e) throws Exception {
        int i = nodo.llave.size() - 1;
        if (nodo.hoja) {
            nodo.llave.add(null);
            while (i >= 0 && nodo.llave.get(i).compareTo(e) > 0) {
                nodo.llave.set(i + 1, nodo.llave.get(i));
                i--;
            }
            nodo.llave.set(i + 1, e);
        } else {
            while (i >= 0 && nodo.llave.get(i).compareTo(e) > 0) {
                i--;
            }
            if (nodo.hijos.get(i + 1).llave.size() == 2 * t - 1) {
                dividir(nodo, i + 1, nodo.hijos.get(i + 1));
                if (nodo.llave.get(i + 1).compareTo(e) < 0) {
                    i++;
                }
            }
            insertarNoFull(nodo.hijos.get(i + 1), e);
        }
    }

    private void dividir(Nodo<E> nodo, int i, Nodo<E> hijo) throws Exception {
        Nodo<E> nuevo = new Nodo<E>(hijo.t, hijo.hoja);
        for (int j = 0; j < t - 1; j++) {
            nuevo.llave.add(hijo.llave.remove(t));
        }
        if (!hijo.hoja) {
            for (int j = 0; j < t; j++) {
                nuevo.hijos.add(hijo.hijos.remove(t));
            }
        }
        nodo.hijos.add(i + 1, nuevo);
        nodo.llave.add(i, hijo.llave.remove(t - 1));
    }

    // =============================
    // MÉTODO DE ELIMINAR
    // =============================
    public void eliminar(E e) {
        if (raiz == null) {
            System.out.println("Árbol vacío");
            return;
        }
        eliminar(raiz, e);

        // Si la raíz queda sin llaves
        if (raiz.llave.size() == 0) {
            if (raiz.hoja) {
                raiz = null;
            } else {
                raiz = raiz.hijos.get(0);
            }
        }
    }

    private void eliminar(Nodo<E> nodo, E e) {
        int idx = buscarClave(nodo, e);

        if (idx < nodo.llave.size() && nodo.llave.get(idx).compareTo(e) == 0) {
            if (nodo.hoja) {
                nodo.llave.remove(idx); // caso 1: en hoja
            } else {
                eliminarDeNoHoja(nodo, idx); // caso 2: en nodo interno
            }
        } else {
            if (nodo.hoja) {
                return; // no está
            }

            boolean ultimaPos = (idx == nodo.llave.size());
            if (nodo.hijos.get(idx).llave.size() < t) {
                llenar(nodo, idx);
            }

            if (ultimaPos && idx > nodo.llave.size()) {
                eliminar(nodo.hijos.get(idx - 1), e);
            } else {
                eliminar(nodo.hijos.get(idx), e);
            }
        }
    }

    private void eliminarDeNoHoja(Nodo<E> nodo, int idx) {
        E k = nodo.llave.get(idx);
        if (nodo.hijos.get(idx).llave.size() >= t) {
            E pred = obtenerPredecesor(nodo, idx);
            nodo.llave.set(idx, pred);
            eliminar(nodo.hijos.get(idx), pred);
        } else if (nodo.hijos.get(idx + 1).llave.size() >= t) {
            E succ = obtenerSucesor(nodo, idx);
            nodo.llave.set(idx, succ);
            eliminar(nodo.hijos.get(idx + 1), succ);
        } else {
            fusionar(nodo, idx);
            eliminar(nodo.hijos.get(idx), k);
        }
    }

    private E obtenerPredecesor(Nodo<E> nodo, int idx) {
        Nodo<E> cur = nodo.hijos.get(idx);
        while (!cur.hoja) {
            cur = cur.hijos.get(cur.llave.size());
        }
        return cur.llave.get(cur.llave.size() - 1);
    }

    private E obtenerSucesor(Nodo<E> nodo, int idx) {
        Nodo<E> cur = nodo.hijos.get(idx + 1);
        while (!cur.hoja) {
            cur = cur.hijos.get(0);
        }
        return cur.llave.get(0);
    }

    private void llenar(Nodo<E> nodo, int idx) {
        if (idx != 0 && nodo.hijos.get(idx - 1).llave.size() >= t) {
            prestarDeAnterior(nodo, idx);
        } else if (idx != nodo.llave.size() && nodo.hijos.get(idx + 1).llave.size() >= t) {
            prestarDeSiguiente(nodo, idx);
        } else {
            if (idx != nodo.llave.size()) {
                fusionar(nodo, idx);
            } else {
                fusionar(nodo, idx - 1);
            }
        }
    }

    private void prestarDeAnterior(Nodo<E> nodo, int idx) {
        Nodo<E> hijo = nodo.hijos.get(idx);
        Nodo<E> hermano = nodo.hijos.get(idx - 1);

        hijo.llave.add(0, nodo.llave.get(idx - 1));
        if (!hijo.hoja) {
            hijo.hijos.add(0, hermano.hijos.remove(hermano.hijos.size() - 1));
        }
        nodo.llave.set(idx - 1, hermano.llave.remove(hermano.llave.size() - 1));
    }

    private void prestarDeSiguiente(Nodo<E> nodo, int idx) {
        Nodo<E> hijo = nodo.hijos.get(idx);
        Nodo<E> hermano = nodo.hijos.get(idx + 1);

        hijo.llave.add(nodo.llave.get(idx));
        if (!hijo.hoja) {
            hijo.hijos.add(hermano.hijos.remove(0));
        }
        nodo.llave.set(idx, hermano.llave.remove(0));
    }

    private void fusionar(Nodo<E> nodo, int idx) {
        Nodo<E> hijo = nodo.hijos.get(idx);
        Nodo<E> hermano = nodo.hijos.get(idx + 1);

        hijo.llave.add(nodo.llave.remove(idx));
        hijo.llave.addAll(hermano.llave);

        if (!hijo.hoja) {
            hijo.hijos.addAll(hermano.hijos);
        }
        nodo.hijos.remove(idx + 1);
    }

    private int buscarClave(Nodo<E> nodo, E k) {
        int idx = 0;
        while (idx < nodo.llave.size() && nodo.llave.get(idx).compareTo(k) < 0) {
            idx++;
        }
        return idx;
    }
}
