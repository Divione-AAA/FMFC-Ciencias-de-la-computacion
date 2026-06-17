package ed2.proyectofinal.metabolico;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * MetabolicNetwork ----------------- Implementa un grafo dirigido con pesos
 * reales (positivos o negativos) que modela la red metabólica. Internamente se
 * usa lista de adyacencia porque el grafo esperado es disperso: con V nodos y E
 * aristas, la lista de adyacencia requiere O(V + E) memoria frente a O(V^2) de
 * la matriz de adyacencia.
 *
 * Algoritmo seleccionado para Rutas (R1): Bellman–Ford. - Justificación:
 * Bellman–Ford calcula caminos de coste mínimo en grafos con pesos negativos y
 * detecta ciclos de peso negativo; Dijkstra no puede usarse con aristas
 * negativas. - Complejidad: O(V * E) en el peor caso.
 *
 * Estructura interna: - ArrayList<Metabolite>: almacenamiento de metabolitos -
 * List<List<Edge>> adj: lista de adyacencia - Lista de rutas objetivo
 */
public class MetabolicNetwork {

    /**
     * Clase interna que representa una arista (reacción).
     */
    public static class Edge {

        public final int from;
        public final int to;
        public final double weight;
        public final String id;

        public Edge(int from, int to, double weight, String id) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.id = id;
        }

        @Override
        public String toString() {
            return String.format("%s: %d -> %d (%.3f)", id, from, to, weight);
        }
    }

    private final ArrayList<Metabolite> nodes = new ArrayList<>();
    private final List<List<Edge>> adj = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();

    // Rutas objetivo que el sistema debe analizar
    private final List<int[]> routes = new ArrayList<>();

    public MetabolicNetwork() {
    }

    /**
     * Busca posición del metabolito. Complejidad: O(V)
     */
    private int findIndex(int id) {
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Añade un metabolito a la red.
     */
    public void addMetabolite(Metabolite m) {
        if (findIndex(m.getId()) != -1) {
            throw new IllegalArgumentException("Metabolite id exists: " + m.getId());
        }

        nodes.add(m);
        adj.add(new ArrayList<>());
    }

    /**
     * Elimina un metabolito y todas sus aristas entrantes/salientes. Lanza
     * NotFoundException si el id no existe.
     *
     * Complejidad: O(V + E)
     */
    public void removeMetabolite(int id) throws NotFoundException {

        int index = findIndex(id);

        if (index == -1) {
            throw new NotFoundException("Metabolite not found: " + id);
        }

        nodes.remove(index);
        adj.remove(index);

        for (int i = 0; i < edges.size();) {

            Edge e = edges.get(i);

            if (e.from == index || e.to == index) {
                edges.remove(i);
            } else {
                i++;
            }
        }

        for (int i = 0; i < adj.size(); i++) {

            List<Edge> list = adj.get(i);

            for (int j = 0; j < list.size();) {

                Edge e = list.get(j);

                if (e.from == index || e.to == index) {
                    list.remove(j);
                } else {
                    j++;
                }
            }
        }
    }

    /**
     * Añade una reacción dirigida entre dos metabolitos existentes.
     */
    public void addReaction(
            int fromId,
            int toId,
            double deltaG,
            String reactionId
    ) throws NotFoundException {

        int from = findIndex(fromId);
        int to = findIndex(toId);

        if (from == -1) {
            throw new NotFoundException("Source metabolite unknown");
        }

        if (to == -1) {
            throw new NotFoundException("Target metabolite unknown");
        }

        Edge e
                = new Edge(
                        from,
                        to,
                        deltaG,
                        reactionId
                );

        adj.get(from).add(e);
        edges.add(e);
    }

    /**
     * Registra una ruta de interés.
     */
    public void addRouteToAnalyze(
            int sourceId,
            int targetId
    ) throws NotFoundException {

        if (findIndex(sourceId) == -1
                || findIndex(targetId) == -1) {

            throw new NotFoundException(
                    "Route endpoints must exist"
            );

        }

        routes.add(
                new int[]{
                    sourceId,
                    targetId
                }
        );
    }

    public List<int[]> getRoutes() {
        return Collections.unmodifiableList(routes);
    }

    public static class BFResult {

        public final double[] dist;
        public final int[] pred;
        public final boolean negativeCycle;

        BFResult(
                double[] d,
                int[] p,
                boolean n
        ) {

            dist = d;
            pred = p;
            negativeCycle = n;

        }
    }

    /**
     * Ejecuta Bellman–Ford.
     *
     * Complejidad: O(V * E)
     */
    public BFResult bellmanFordFrom(int srcId) {

        int src = findIndex(srcId);

        int V = nodes.size();

        double[] dist = new double[V];
        int[] pred = new int[V];

        Arrays.fill(
                dist,
                Double.POSITIVE_INFINITY
        );

        Arrays.fill(
                pred,
                -1
        );

        dist[src] = 0;

        for (int i = 0; i < V - 1; i++) {

            boolean changed = false;

            for (int j = 0; j < edges.size(); j++) {

                Edge e = edges.get(j);

                if (dist[e.from]
                        != Double.POSITIVE_INFINITY) {

                    if (dist[e.from]
                            + e.weight
                            < dist[e.to]) {

                        dist[e.to]
                                = dist[e.from]
                                + e.weight;

                        pred[e.to] = e.from;

                        changed = true;
                    }
                }
            }

            if (!changed) {
                break;
            }
        }

        boolean neg = false;

        for (int i = 0; i < edges.size(); i++) {

            Edge e = edges.get(i);

            if (dist[e.from]
                    != Double.POSITIVE_INFINITY) {

                if (dist[e.from]
                        + e.weight
                        < dist[e.to]) {

                    neg = true;
                    break;

                }

            }
        }

        return new BFResult(
                dist,
                pred,
                neg
        );
    }

    /**
     * Detecta si existe cualquier ciclo negativo.
     */
    public boolean hasNegativeCycle() {

        for (int i = 0; i < nodes.size(); i++) {

            BFResult r
                    = bellmanFordFrom(
                            nodes
                                    .get(i)
                                    .getId()
                    );

            if (r.negativeCycle) {
                return true;
            }

        }

        return false;
    }

    /**
     * Calcula ruta mínima entre metabolitos.
     */
    public List<Metabolite> shortestPath(
            int sourceId,
            int targetId
    ) {

        if (hasNegativeCycle()) {

            throw new IllegalStateException(
                    "Negative cycle detected"
            );

        }

        BFResult res
                = bellmanFordFrom(
                        sourceId
                );

        int target
                = findIndex(
                        targetId
                );

        if (res.dist[target]
                == Double.POSITIVE_INFINITY) {

            return Collections.emptyList();

        }

        LinkedList<Metabolite> path
                = new LinkedList<>();

        int cur = target;

        while (cur != -1) {

            path.addFirst(
                    nodes.get(cur)
            );

            cur
                    = res.pred[cur];

        }

        return path;

    }

    /**
     * Eliminación de reacciones por id.
     */
    public boolean removeReactionById(
            String reactionId
    ) {

        for (int i = 0; i < edges.size(); i++) {

            Edge e
                    = edges.get(i);

            if (e.id.equals(
                    reactionId
            )) {

                edges.remove(i);

                List<Edge> list
                        = adj.get(
                                e.from
                        );

                for (int j = 0;
                        j < list.size();
                        j++) {

                    if (list
                            .get(j).id
                            .equals(
                                    reactionId
                            )) {

                        list.remove(j);

                        break;

                    }

                }

                return true;

            }

        }

        return false;

    }

}
