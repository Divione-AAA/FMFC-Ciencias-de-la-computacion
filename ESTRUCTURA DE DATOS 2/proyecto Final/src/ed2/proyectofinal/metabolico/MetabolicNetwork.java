package ed2.proyectofinal.metabolico;

import java.util.*;

/**
 * MetabolicNetwork
 * -----------------
 * Implementa un grafo dirigido con pesos reales (positivos o negativos) que
 * modela la red metabólica. Internamente se usa lista de adyacencia porque el
 * grafo esperado es disperso: con V nodos y E aristas, la lista de adyacencia
 * requiere O(V + E) memoria frente a O(V^2) de la matriz de adyacencia.
 *
 * Algoritmo seleccionado para Rutas (R1): Bellman–Ford.
 * - Justificación: Bellman–Ford calcula caminos de coste mínimo en grafos con
 *   pesos negativos y detecta ciclos de peso negativo; Dijkstra no puede usarse
 *   con aristas negativas.
 * - Complejidad: O(V * E) en el peor caso.
 *
 * Estructura interna:
 * - Map<Integer, Metabolite> nodes: índice -> metabolito
 * - List<List<Edge>> adj: lista de adyacencia con objetos Edge
 * - Se mantiene una lista de rutas objetivo (pares src,tgt) para análisis
 *
 * Nota sobre reutilización: la implementación de montículos o listas se puede
 * integrar con código de prácticas previas; aquí se ha priorizado claridad
 * y corrección del algoritmo Bellman–Ford.
 */
public class MetabolicNetwork {

    /** Clase interna que representa una arista (reacción). */
    public static class Edge {
        public final int from;
        public final int to;
        public final double weight; // energía libre de Gibbs (positivo o negativo)
        public final String id;     // identificador de la reacción
        public Edge(int from,int to,double weight,String id){ this.from=from; this.to=to; this.weight=weight; this.id=id; }
        @Override public String toString(){ return String.format("%s: %d -> %d (%.3f)", id, from, to, weight); }
    }

    private final Map<Integer, Metabolite> nodes = new LinkedHashMap<>();
    private final List<List<Edge>> adj = new ArrayList<>();
    private final List<Edge> edges = new ArrayList<>();

    // Rutas objetivo que el sistema debe analizar (pares fuente, destino)
    private final List<int[]> routes = new ArrayList<>();

    public MetabolicNetwork() { }

    /**
     * Añade un metabolito a la red. El id debe ser único. Se amplía la lista de
     * adyacencia si es necesario.
     */
    public void addMetabolite(Metabolite m) {
        int id = m.getId();
        if(nodes.containsKey(id)) throw new IllegalArgumentException("Metabolite id exists: " + id);
        nodes.put(id, m);
        while(adj.size() <= id) adj.add(new ArrayList<>());
    }

    /**
     * Elimina un metabolito y todas sus aristas entrantes/salientes.
     * Lanza NotFoundException si el id no existe.
     *
     * Complejidad: O(V + E) debido a la necesidad de limpiar aristas entrantes.
     */
    public void removeMetabolite(int id) throws NotFoundException {
        if(!nodes.containsKey(id)) throw new NotFoundException("Metabolite not found: " + id);
        nodes.remove(id);
        // eliminar aristas salientes
        if(id < adj.size()) {
            List<Edge> out = adj.get(id);
            edges.removeAll(out);
            out.clear();
        }
        // eliminar aristas entrantes
        for(List<Edge> list : adj){
            Iterator<Edge> it = list.iterator();
            while(it.hasNext()){
                Edge e = it.next();
                if(e.to == id){ it.remove(); edges.remove(e); }
            }
        }
        // Nota: no compactamos índices: IDs se mantienen fijos.
    }

    /**
     * Añade una reacción dirigida entre dos metabolitos existentes.
     */
    public void addReaction(int fromId, int toId, double deltaG, String reactionId) throws NotFoundException {
        if(!nodes.containsKey(fromId)) throw new NotFoundException("Source metabolite unknown: " + fromId);
        if(!nodes.containsKey(toId)) throw new NotFoundException("Target metabolite unknown: " + toId);
        Edge e = new Edge(fromId, toId, deltaG, reactionId);
        while(adj.size() <= Math.max(fromId, toId)) adj.add(new ArrayList<>());
        adj.get(fromId).add(e);
        edges.add(e);
    }

    /**
     * Registra una ruta de interés (origen -> destino) para análisis posterior.
     */
    public void addRouteToAnalyze(int sourceId, int targetId) throws NotFoundException {
        if(!nodes.containsKey(sourceId) || !nodes.containsKey(targetId))
            throw new NotFoundException("Route endpoints must be known nodes");
        routes.add(new int[]{sourceId, targetId});
    }

    public List<int[]> getRoutes() { return Collections.unmodifiableList(routes); }

    // Resultado auxiliar de Bellman–Ford
    public static class BFResult { public final double[] dist; public final int[] pred; public final boolean negativeCycle; BFResult(double[] d,int[] p,boolean n){dist=d;pred=p;negativeCycle=n;} }

    /**
     * Ejecuta Bellman–Ford desde un nodo fuente. Devuelve distancias y
     * predecesores. Si detecta ciclo negativo alcanzable desde la fuente, el
     * campo negativeCycle será true.
     *
     * Complejidad: O(V * E)
     */
    public BFResult bellmanFordFrom(int srcId) {
        final double INF = Double.POSITIVE_INFINITY;
        int V = adj.size();
        double[] dist = new double[V];
        int[] pred = new int[V];
        Arrays.fill(dist, INF);
        Arrays.fill(pred, -1);
        dist[srcId] = 0.0;

        // Relajación repetida
        for(int i=0;i<V-1;i++){
            boolean changed = false;
            for(Edge e: edges){
                if(e.from < dist.length && e.to < dist.length) {
                    if(dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]){
                        dist[e.to] = dist[e.from] + e.weight;
                        pred[e.to] = e.from;
                        changed = true;
                    }
                }
            }
            if(!changed) break; // convergió antes
        }

        // Detección de ciclo negativo alcanzable desde src
        boolean neg = false;
        for(Edge e: edges){
            if(e.from < dist.length && e.to < dist.length) {
                if(dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]){ neg = true; break; }
            }
        }
        return new BFResult(dist, pred, neg);
    }

    /**
     * Detecta si existe cualquier ciclo de peso negativo en la componente global
     * del grafo. Se utiliza la técnica de super-fuente: conectar una fuente
     * ficticia a todos los nodos con coste 0 y ejecutar Bellman–Ford desde ella.
     *
     * Justificación: así se detectan ciclos negativos en cualquier componente
     * del grafo.
     */
    public boolean hasNegativeCycle() {
        // Construimos aristas extendidas (edges + super->v)
        int superIndex = adj.size();
        int V = adj.size() + 1;
        final double INF = Double.POSITIVE_INFINITY;
        double[] dist = new double[V]; int[] pred = new int[V];
        Arrays.fill(dist, INF); Arrays.fill(pred, -1);
        dist[superIndex] = 0.0;

        List<Edge> allEdges = new ArrayList<>(edges);
        for(int v=0; v<adj.size(); v++) allEdges.add(new Edge(superIndex, v, 0.0, "super->"+v));

        for(int i=0;i<V-1;i++){
            boolean changed = false;
            for(Edge e: allEdges){
                if(e.from < dist.length && e.to < dist.length) {
                    if(dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]){
                        dist[e.to] = dist[e.from] + e.weight; pred[e.to] = e.from; changed = true;
                    }
                }
            }
            if(!changed) break;
        }
        for(Edge e: allEdges){
            if(e.from < dist.length && e.to < dist.length) {
                if(dist[e.from] != INF && dist[e.from] + e.weight < dist[e.to]) return true;
            }
        }
        return false;
    }

    /**
     * Calcula la ruta de menor coste energético entre dos metabolitos.
     * Si existe un ciclo negativo en la red se lanza IllegalStateException, ya
     * que los caminos de coste mínimo dejan de tener sentido (se podría reducir
     * indefinidamente el coste).
     *
     * Devuelve la lista de Metabolite en orden desde fuente a destino si existe
     * un camino; si no hay camino devuelve lista vacía.
     */
    public List<Metabolite> shortestPath(int sourceId, int targetId) {
        if(hasNegativeCycle()) throw new IllegalStateException("Negative energy cycle detected in network; shortest paths undefined.");
        BFResult res = bellmanFordFrom(sourceId);
        if(res.negativeCycle) throw new IllegalStateException("Negative cycle reachable from source; shortest paths undefined.");
        if(res.dist[targetId] == Double.POSITIVE_INFINITY) return Collections.emptyList();
        // reconstruir camino
        LinkedList<Metabolite> path = new LinkedList<>();
        int cur = targetId;
        while(cur != -1 && cur != sourceId){ path.addFirst(nodes.get(cur)); cur = res.pred[cur]; }
        if(cur == sourceId) path.addFirst(nodes.get(sourceId));
        return path;
    }

    /**
     * Método utilitario para obtener coste mínimo desde una fuente a todos los
     * nodos. Lanza IllegalStateException si existe ciclo negativo.
     */
    public Map<Integer, Double> distancesFrom(int sourceId) {
        if(hasNegativeCycle()) throw new IllegalStateException("Negative energy cycle detected in network; distances undefined.");
        BFResult res = bellmanFordFrom(sourceId);
        if(res.negativeCycle) throw new IllegalStateException("Negative cycle reachable from source; distances undefined.");
        Map<Integer, Double> m = new LinkedHashMap<>();
        for(Integer id : nodes.keySet()) m.put(id, res.dist[id]);
        return m;
    }

    /** Eliminación de reacciones por id. */
    public boolean removeReactionById(String reactionId) {
        boolean removed = false;
        Iterator<Edge> it = edges.iterator();
        while(it.hasNext()){
            Edge e = it.next();
            if(e.id.equals(reactionId)){
                it.remove();
                // eliminar de adj[from]
                List<Edge> list = adj.get(e.from);
                list.removeIf(x -> x.id.equals(reactionId));
                removed = true;
            }
        }
        return removed;
    }

}
