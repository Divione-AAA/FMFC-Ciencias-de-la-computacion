import java.util.ArrayList;

public class ArbolPair<E extends Comparable<E>> {
    private static class Pair<K, V> {
        K first;
        V second;

        Pair(K k, V v) {
            this.first = k;
            this.second = v;
        }
    }

    private ArrayList<Pair<E, Integer>> lista;
    private int tam;

    public ArbolPair() {
        lista = new ArrayList<>(100000);
        tam = 0;
    }

    public void insertHijo(E padre, E elemento) throws Exception {
        if (tam == 0) {
            lista.add(new Pair<>(elemento, -1));
            ++tam;
            return;
        }
        for (int i = 0; i < tam; i++) {
            if (lista.get(i).first.compareTo(padre) == 0) {
                lista.add(new Pair<>(elemento, i));
                ++tam;
                return;
            }
        }
        throw new Exception("No existe ese padre");
    }

    public int buscar(E elemento) throws Exception {
        for (int i = 0; i < tam; i++) {
            if (lista.get(i).first.compareTo(elemento) == 0) {
                return lista.get(i).second;
            }
        }
        throw new Exception("No existe ese elemento");
    }

    public void insertarHermano(E padre, E hermano) throws Exception {
        int i = buscar(padre);
        lista.add(new Pair<>(hermano, i));
        ++tam;
    }

    public void borrar(E elemento) throws Exception {
        ArrayList<Integer> borrar = new ArrayList<>();
        int indice = -1;

        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).first.compareTo(elemento) == 0) {
                indice = i;
                break;
            }
        }

        if (indice == -1) {
            throw new Exception("No existe ese elemento");
        }

        recolectarDescendientes(indice, borrar);
        borrar.add(indice);

        borrar.sort((a, b) -> b - a);
        for (int idx : borrar) {
            lista.remove(idx);
            tam--;
        }
    }

    private void recolectarDescendientes(int padreIdx, ArrayList<Integer> acumulador) {
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).second == padreIdx) {
                acumulador.add(i);
                recolectarDescendientes(i, acumulador);
            }
        }
    }

    public void imprimir() {
        for (int i = 0; i < lista.size(); i++) {
            E valor = lista.get(i).first;
            int padreIdx = lista.get(i).second;
            String padre = (padreIdx == -1) ? "null" : lista.get(padreIdx).first.toString();
            System.out.println(valor + " -> padre: " + padre);
        }
    }

    public static void main(String[] args) {
        try {
            ArbolPair<String> arbol = new ArbolPair<>();

            arbol.insertHijo(null, "A");     // raíz
            arbol.insertHijo("A", "B");      // hijo de A
            arbol.insertHijo("A", "C");      // otro hijo de A
            arbol.insertHijo("B", "D");      // hijo de B
            arbol.insertHijo("B", "E");      // otro hijo de B
            arbol.insertarHermano("C", "F"); // hermano de C (también hijo de A)

            System.out.println("Árbol original:");
            arbol.imprimir();

            arbol.borrar("B"); // elimina B y sus hijos D y E

            System.out.println("\nÁrbol después de eliminar 'B':");
            arbol.imprimir();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
