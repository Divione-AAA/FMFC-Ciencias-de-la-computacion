import java.util.*;

public class GrafoListaAdyacencia implements Grafo {

    private Map<String, List<Arista>> vertices;

    private static class Arista {
        String destino;
        float peso;

        Arista(String destino, float peso) {
            this.destino = destino;
            this.peso = peso;
        }

        Arista(String destino) {
            this(destino, 1); // peso por defecto = 1 si no se especifica
        }
    }

    public GrafoListaAdyacencia() {
        vertices = new HashMap<>();
    }

    // ========================= Manipulación del grafo =========================
    @Override
    public int insVertice(String nombre) {
        if (vertices.containsKey(nombre)) return -1; // ya existe
        vertices.put(nombre, new ArrayList<>());
        return 1; // éxito
    }

    @Override
    public void insArista(String origen, String destino, float peso) {
        if (!vertices.containsKey(origen)) insVertice(origen);
        if (!vertices.containsKey(destino)) insVertice(destino);
        vertices.get(origen).add(new Arista(destino, peso));
    }

    @Override
    public void insArista(String origen, String destino) {
        insArista(origen, destino, 1); // peso por defecto = 1
    }

    @Override
    public void elimVertice(String nombre) {
        vertices.remove(nombre);
        // eliminar aristas que apuntan a este vértice
        for (List<Arista> adyacentes : vertices.values()) {
            adyacentes.removeIf(a -> a.destino.equals(nombre));
        }
    }

    @Override
    public void elimArista(String origen, String destino) {
        if (vertices.containsKey(origen)) {
            vertices.get(origen).removeIf(a -> a.destino.equals(destino));
        }
    }

    // ========================= Operaciones sobre grafos =========================
    @Override
    public int buscar(String nombre) {
        return vertices.containsKey(nombre) ? 1 : -1;
    }

    @Override
    public boolean esAdyacente(String origen, String destino) {
        if (!vertices.containsKey(origen)) return false;
        for (Arista a : vertices.get(origen)) {
            if (a.destino.equals(destino)) return true;
        }
        return false;
    }

    // ========================= Recorridos =========================
    @Override
    public List<String> recorridoAmplitud(String verticeOrigen) {
        List<String> resultado = new ArrayList<>();
        if (!vertices.containsKey(verticeOrigen)) return resultado;

        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new LinkedList<>();
        cola.add(verticeOrigen);
        visitados.add(verticeOrigen);

        while (!cola.isEmpty()) {
            String v = cola.poll();
            resultado.add(v);
            for (Arista a : vertices.get(v)) {
                if (!visitados.contains(a.destino)) {
                    cola.add(a.destino);
                    visitados.add(a.destino);
                }
            }
        }

        return resultado;
    }

    @Override
    public List<String> recorridoProfundidad(String verticeOrigen) {
        List<String> resultado = new ArrayList<>();
        if (!vertices.containsKey(verticeOrigen)) return resultado;

        Set<String> visitados = new HashSet<>();
        Stack<String> pila = new Stack<>();
        pila.push(verticeOrigen);

        while (!pila.isEmpty()) {
            String v = pila.pop();
            if (!visitados.contains(v)) {
                resultado.add(v);
                visitados.add(v);
                // agregamos los adyacentes en orden inverso para DFS clásico
                List<Arista> adyacentes = vertices.get(v);
                for (int i = adyacentes.size() - 1; i >= 0; i--) {
                    if (!visitados.contains(adyacentes.get(i).destino)) {
                        pila.push(adyacentes.get(i).destino);
                    }
                }
            }
        }

        return resultado;
    }

    // ========================= Método para depuración =========================
    public void imprimirGrafo() {
        for (String vertice : vertices.keySet()) {
            System.out.print(vertice + " -> ");
            for (Arista a : vertices.get(vertice)) {
                System.out.print(a.destino + "(" + a.peso + ") ");
            }
            System.out.println();
        }
    }
}
