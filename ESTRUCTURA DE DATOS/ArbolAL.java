import java.util.*;

public class ArbolAL<E extends Comparable<E>>{
    private static class Pair<K, V> {
        K first;
        V second;

        Pair(K k, V v) {
            this.first = k;
            this.second = v;
        }
    }

    private ArrayList<Pair<E, ArrayList<Integer>>> lista;

    public ArbolAL() {
        lista = new ArrayList<>();
    }

    public E raiz() throws Exception {
        if (lista.isEmpty()) throw new Exception("Árbol vacío");
        return lista.get(0).first;
    }

    public boolean esVacio() {
        return lista.isEmpty();
    }

    public void Vaciar() {
        lista.clear();
    }

    public int buscar(E elElemento)throws Exception {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).first.compareTo(elElemento) == 0) return i;
        }
        throw new Exception("Elemento no encontrado");
    }

    public E padre(E elElemento) throws Exception {
        if (esVacio()) throw new Exception("Árbol vacío");
        int idx = buscar(elElemento);
        if (idx == -1) throw new Exception("Elemento no encontrado");

        for (Pair<E, ArrayList<Integer>> par : lista) {
            if (par.second.contains(idx)) return par.first;
        }
        throw new Exception("No tiene padre (es raíz)");
    }

    public E primerHijo(E elElemento) throws Exception {
        if (esVacio()) throw new Exception("Árbol vacío");
        int idx = buscar(elElemento);
        if (idx == -1) throw new Exception("Elemento no encontrado");

        ArrayList<Integer> hijos = lista.get(idx).second;
        if (hijos.isEmpty()) throw new Exception("No tiene hijos");
        return lista.get(hijos.get(0)).first;
    }

    public E siguienteHermano(E elElemento) throws Exception {
        if (esVacio()) throw new Exception("Árbol vacío");
        int idx = buscar(elElemento);
        if (idx == -1) throw new Exception("Elemento no encontrado");

        for (Pair<E, ArrayList<Integer>> par : lista) {
            ArrayList<Integer> hijos = par.second;
            for (int i = 0; i < hijos.size(); i++) {
                if (hijos.get(i) == idx && i + 1 < hijos.size()) {
                    return lista.get(hijos.get(i + 1)).first;
                }
            }
        }
        throw new Exception("No tiene hermano derecho");
    }

    public void insertaHijo(E elPadre, E elElemento) throws Exception {
        if (esVacio()) {
            lista.add(new Pair<>(elElemento, new ArrayList<>()));
            return;
        }

        int idx = buscar(elPadre);
        if (idx == -1) throw new Exception("Padre no encontrado");

        lista.add(new Pair<>(elElemento, new ArrayList<>()));
        lista.get(idx).second.add(lista.size() - 1);
    }

    public void insertaHermano(E hermano, E elElemento) throws Exception {
        if (esVacio()) throw new Exception("Árbol vacío");

        int idx = buscar(hermano);
        if (idx == -1) throw new Exception("Hermano no encontrado");

        if (idx == 0) throw new Exception("La raíz no puede tener hermanos");

        for (Pair<E, ArrayList<Integer>> par : lista) {
            if (par.second.contains(idx)) {
                lista.add(new Pair<>(elElemento, new ArrayList<>()));
                par.second.add(lista.size() - 1);
                return;
            }
        }

        throw new Exception("No se encontró el padre del hermano");
    }

    private void recolectarDescendientes(int idx, ArrayList<Integer> acumulador) {
        for (int hijo : lista.get(idx).second) {
            acumulador.add(hijo);
            recolectarDescendientes(hijo, acumulador);
        }
    }
    
    public void elimina(E elElemento) throws Exception {
        if (esVacio()) throw new Exception("Árbol vacío");

        int idx = buscar(elElemento);
        if (idx == -1) throw new Exception("Elemento no encontrado");

        ArrayList<Integer> borrar = new ArrayList<>();
        recolectarDescendientes(idx, borrar);
        borrar.add(idx);
        borrar.sort((a, b) -> b - a);

        // Crear mapa de índices antes de eliminar
        Map<Integer, E> valoresRestantes = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            if (!borrar.contains(i)) {
                valoresRestantes.put(i, lista.get(i).first);
            }
        }

        // Eliminar nodos
        for (int i : borrar) lista.remove(i);

        // Reconstruir índices
        Map<E, Integer> nuevoIndice = new HashMap<>();
        for (int i = 0; i < lista.size(); i++) {
            nuevoIndice.put(lista.get(i).first, i);
        }

        // Actualizar listas de hijos
            for (Pair<E, ArrayList<Integer>> par : lista) {
            ArrayList<Integer> hijosActualizados = new ArrayList<>();
            for (int h : par.second) {
                E hijoValor = valoresRestantes.get(h);
                if (hijoValor != null && nuevoIndice.containsKey(hijoValor)) {
                    hijosActualizados.add(nuevoIndice.get(hijoValor));
                }
            }
            par.second = hijosActualizados;
        }
    }

    public void imprimir() {
        for (int i = 0; i < lista.size(); i++) {
            E valor = lista.get(i).first;
            ArrayList<Integer> hijos = lista.get(i).second;
            System.out.print(valor + " -> hijos: ");
            for (int h : hijos) {
                System.out.print(lista.get(h).first + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        try {
            ArbolAL<String> arbol = new ArbolAL<>();

            arbol.insertaHijo(null, "A"); // raíz
            arbol.insertaHijo("A", "B");
            arbol.insertaHijo("A", "C");
            arbol.insertaHijo("B", "D");
            arbol.insertaHermano("C", "E");

            System.out.println("Árbol original:");
            arbol.imprimir();

            System.out.println("\nPadre de D: " + arbol.padre("D"));
            System.out.println("Primer hijo de A: " + arbol.primerHijo("A"));
            System.out.println("Siguiente hermano de C: " + arbol.siguienteHermano("C"));

            arbol.elimina("B");

            System.out.println("\nÁrbol después de eliminar 'B':");
            arbol.imprimir();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
