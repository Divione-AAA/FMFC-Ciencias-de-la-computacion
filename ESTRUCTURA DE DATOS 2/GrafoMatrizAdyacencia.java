import java.util.*;

public class GrafoMatrizAdyacencia implements Grafo {

    private List<String> vertices;
    private float[][] matriz;
    private static final int MAX = 100;

    public GrafoMatrizAdyacencia() {
        vertices = new ArrayList<>();
        matriz = new float[MAX][MAX];
    }

    // =================== Manipulación del grafo ===================

    @Override
    public int insVertice(String nombre) {
        if (buscar(nombre) != -1) return -1;
        vertices.add(nombre);
        return vertices.size() - 1;
    }

    @Override
    public void insArista(String origen, String destino, float peso) {
        int i = buscar(origen);
        int j = buscar(destino);

        if (i == -1) i = insVertice(origen);
        if (j == -1) j = insVertice(destino);

        matriz[i][j] = peso;
    }

    @Override
    public void insArista(String origen, String destino) {
        insArista(origen, destino, 1);
    }

    @Override
    public void elimVertice(String nombre) {
        int idx = buscar(nombre);
        if (idx == -1) return;

        vertices.remove(idx);

        for (int i = idx; i < vertices.size(); i++) {
            for (int j = 0; j < MAX; j++) {
                matriz[i][j] = matriz[i + 1][j];
                matriz[j][i] = matriz[j][i + 1];
            }
        }
    }

    @Override
    public void elimArista(String origen, String destino) {
        int i = buscar(origen);
        int j = buscar(destino);
        if (i != -1 && j != -1) {
            matriz[i][j] = 0;
        }
    }

    // =================== Operaciones ===================

    @Override
    public int buscar(String nombre) {
        return vertices.indexOf(nombre);
    }

    @Override
    public boolean esAdyacente(String origen, String destino) {
        int i = buscar(origen);
        int j = buscar(destino);
        if (i == -1 || j == -1) return false;
        return matriz[i][j] != 0;
    }

    // =================== Recorridos ===================

    @Override
    public List<String> recorridoAmplitud(String verticeOrigen) {
        List<String> resultado = new ArrayList<>();
        int origen = buscar(verticeOrigen);
        if (origen == -1) return resultado;

        boolean[] visitado = new boolean[vertices.size()];
        Queue<Integer> cola = new LinkedList<>();

        cola.add(origen);
        visitado[origen] = true;

        while (!cola.isEmpty()) {
            int v = cola.poll();
            resultado.add(vertices.get(v));

            for (int i = 0; i < vertices.size(); i++) {
                if (matriz[v][i] != 0 && !visitado[i]) {
                    visitado[i] = true;
                    cola.add(i);
                }
            }
        }

        return resultado;
    }

    @Override
    public List<String> recorridoProfundidad(String verticeOrigen) {
        List<String> resultado = new ArrayList<>();
        int origen = buscar(verticeOrigen);
        if (origen == -1) return resultado;

        boolean[] visitado = new boolean[vertices.size()];
        Stack<Integer> pila = new Stack<>();
        pila.push(origen);

        while (!pila.isEmpty()) {
            int v = pila.pop();
            if (!visitado[v]) {
                visitado[v] = true;
                resultado.add(vertices.get(v));

                for (int i = vertices.size() - 1; i >= 0; i--) {
                    if (matriz[v][i] != 0 && !visitado[i]) {
                        pila.push(i);
                    }
                }
            }
        }

        return resultado;
    }

    // =================== Utilidad ===================

    public void imprimirMatriz() {
        System.out.print("   ");
        for (String v : vertices) System.out.print(v + " ");
        System.out.println();

        for (int i = 0; i < vertices.size(); i++) {
            System.out.print(vertices.get(i) + " ");
            for (int j = 0; j < vertices.size(); j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
