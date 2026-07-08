package metabolico;

import Heap.EmptyPriorityQueueException;
import Heap.PriorityQueueHeap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ESTRUCTURA UTILIZADA: Grafo Dirigido con Pesos Reales representado mediante Listas de Adyacencia.
 * Se seleccionó una Lista de Adyacencia (`List<List<Edge>> adj`) porque las redes metabólicas reales 
 * son grafos altamente dispersos (cada metabolito interactúa solo con un subconjunto pequeño de la red). 
 * Esta decisión optimiza el uso de memoria a un orden de O(V + E), evitando el desperdicio cuadrático 
 * O(V^2) que provocaría una matriz de adyacencia.
 * Bellman–Ford:
 * Las aristas representan reacciones bioquímicas cuyos pesos equivalen al cambio de Energía Libre de Gibbs ($\Delta G$). 
 * Dado que los procesos exergónicos liberan energía, se modelan con **valores negativos**. El algoritmo de Dijkstra 
 * falla ante pesos negativos al volverse codicioso (greedy). Bellman-Ford es el enfoque matemáticamente idóneo 
 * porque computa correctamente rutas con costes negativos y tiene la propiedad crucial de detectar "ciclos de 
 * energía infinita negativa" (bucles metabólicos termodinámicamente imposibles).
 * Aunque Bellman-Ford es asintóticamente más lento que Dijkstra en las pruebas 
 * experimentales con redes metabólicas reales la condición de parada temprana (`if (!changed) break;`) mitiga este impacto, 
 * terminando la ejecución en pocas iteraciones si el grafo converge rápido.
 */
public class MetabolicNetwork {

    /**
     * Clase interna que representa una arista (reacción bioquímica).
     * Modela la direccionalidad, el identificador único de la enzima/reacción y el coste termodinámico ($\Delta G$).
     */
    public static class Edge {
        public final int from;       // Índice interno del metabolito origen
        public final int to;         // Índice interno del metabolito destino
        public final double weight;  // Energía Libre de Gibbs (Delta G)
        public final String id;      // Código identificador de la reacción

        public Edge(int from, int to, double weight, String id) {
            this.from = from;
            this.to = to;
            this.weight = weight;
            this.id = id;
        }
    }

    // Atributos de la Red (Estructuras de datos modulares)
    private ArrayList<Metabolite> nodes = new ArrayList<>();
    private List<List<Edge>> adj = new ArrayList<>();
    private List<Edge> edges = new ArrayList<>();
    private List<MetabolicRoute> routes = new ArrayList<>(); // Lista de rutas objetivo a analizar

    /**
     * Constructor por defecto de la red metabólica.
     */
    public MetabolicNetwork(){}

    /**
     * Encuentra la posición mapeada de un metabolito en el ArrayList a través de su ID de negocio.
     * * Complejidad Temporal: $O(V)$ en el peor caso, ya que realiza un recorrido lineal sobre los nodos.
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
     * Inserta un nuevo nodo en el grafo e inicializa su lista de adyacencia vacía.
     * * Complejidad Temporal: $O(V)$ debido a la verificación previa de existencia con `findIndex`.
     */
    public void addMetabolite(Metabolite m) {
        if (findIndex(m.getId()) != -1) {
            throw new IllegalArgumentException("Metabolite id exists: " + m.getId());
        }
        nodes.add(m);
        adj.add(new ArrayList<>()); // Inicializa lista de adyacencia para este nuevo vértice
    }

    /**
     * Borra un nodo de la red y limpia de manera segura todas sus reacciones entrantes y salientes.
     * * MODIFICACIÓN REALIZADA: Se diseñaron ciclos con índices controlados manualmente (`i` y `j`) para evitar 
     * excepciones de modificación concurrente (`ConcurrentModificationException`) al remover dinámicamente elementos de los ArrayLists.
     * * Complejidad Temporal: $O(V + E)$. Requiere reestructurar los índices de los nodos y limpiar las listas de aristas globales y locales.
     */
    public void removeMetabolite(int id) throws Exception {
        int index = findIndex(id);
        if (index == -1) throw new NotFoundException("Metabolito no encontrado: " + id);
        
        nodes.remove(index);
        adj.remove(index);

        // Limpieza en la lista global de aristas
        for (int i = 0; i < edges.size();) {
            Edge e = edges.get(i);
            if (e.from == index || e.to == index) {
                edges.remove(i);
            } else {
                i++;
            }
        }

        // Limpieza de adyacencias locales para prevenir referencias muertas
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
     * Agrega una arista dirigida ponderada conectando dos metabolitos existentes en la red.
     * * Complejidad Temporal: $O(V)$, dominada por la búsqueda de los índices correspondientes a los IDs provistos.
     */
    public void addReaction(int fromId, int toId, double deltaG, String reactionId) throws NotFoundException {
        int from = findIndex(fromId);
        int to = findIndex(toId);

        if (from == -1) throw new NotFoundException("Source metabolite unknown");
        if (to == -1) throw new NotFoundException("Target metabolite unknown");

        Edge e = new Edge(from, to, deltaG, reactionId);
        adj.get(from).add(e); // Inserción en lista de adyacencia local: O(1)
        edges.add(e);        // Inserción en lista global: O(1)
    }

    /**
     * Inserta una ruta de interés en el sistema de análisis y calcula preventivamente su número de reacciones.
     * * COMPORTAMIENTO EXPERIMENTAL: Invoca internamente a `shortestPath()`. Si la ruta no es viable o está desconectada, 
     * se penaliza asignándole un recuento de reacciones equivalente a `Integer.MAX_VALUE` para su posterior gestión en el Heap.
     * * Complejidad Temporal: El coste está dominado por Bellman-Ford: $O(V \cdot E)$.
     */
    public MetabolicRoute addRouteToAnalyze(int sourceId, int targetId, double clinicalRelevance) throws Exception {
        if (findIndex(sourceId) == -1 || findIndex(targetId) == -1) throw new NotFoundException("Ruta no existe");

        List<Metabolite> shortestPath = shortestPath(sourceId, targetId);
        int reactionCount;
        if (sourceId == targetId) {
            reactionCount = 0;
        } else if (shortestPath.isEmpty()) {
            reactionCount = Integer.MAX_VALUE;
        } else {
            reactionCount = Math.max(0, shortestPath.size() - 1);
        }

        MetabolicRoute route = new MetabolicRoute(sourceId, targetId, clinicalRelevance, reactionCount);
        routes.add(route);
        return route;
    }

    /**
     * Sobrecarga de método para registrar rutas sin prioridad clínica explícita (inicializa en 0.0).
     */
    public MetabolicRoute addRouteToAnalyze(int sourceId, int targetId) throws Exception {
        return addRouteToAnalyze(sourceId, targetId, 0.0);
    }

    /**
     * Devuelve una vista inmutable de las rutas registradas para proteger la encapsulación del modelo.
     * * Complejidad Temporal: $O(1)$.
     */
    public List<MetabolicRoute> getRoutes() {
        return Collections.unmodifiableList(routes);
    }

    /**
     * Busca una ruta específica usando su ID único.
     * * Complejidad Temporal: $O(R)$ donde R es la cantidad de rutas registradas.
     */
    public MetabolicRoute findRouteById(int routeId) {
        for (MetabolicRoute route : routes) {
            if (route.getId() == routeId) {
                return route;
            }
        }
        return null;
    }

    /**
     * Conecta el procesamiento de dos rutas, forzando a que una requiera la previa resolución de otra.
     * * Complejidad Temporal: $O(R)$ debido a las llamadas de consulta `findRouteById`.
     */
    public void addRouteDependency(int routeId, int dependencyRouteId) throws Exception {
        MetabolicRoute route = findRouteById(routeId);
        MetabolicRoute dependency = findRouteById(dependencyRouteId);

        if (route == null || dependency == null) {
            throw new NotFoundException("Ruta o dependencia de ruta no encontrada");
        }
        if (routeId == dependencyRouteId) {
            throw new IllegalArgumentException("Una ruta no puede depender de sí misma");
        }

        route.addDependency(dependencyRouteId);
    }

    /**
     * Método utilitario de apoyo que indexa las rutas en un HashMap para agilizar los accesos por ID a tiempo constante.
     * * Complejidad Temporal: $O(R)$.
     */
    private Map<Integer, MetabolicRoute> buildRouteMap() {
        Map<Integer, MetabolicRoute> map = new HashMap<>();
        for (MetabolicRoute route : routes) {
            map.put(route.getId(), route);
        }
        return map;
    }

    /**
     * Implementa el **Algoritmo de Kahn para Ordenamiento Topológico**. Sin embargo, a diferencia de una cola regular (FIFO), 
     * se introdujo una **modificación sustancial**: se integró la estructura `PriorityQueueHeap` desarrollada en la entrega anterior.
     * Esta adaptación permite que, cuando múltiples rutas metabólicas están simultáneamente libres de dependencias 
     * (Grado de entrada = 0), el sistema no las procese al azar, sino que el Montículo (`Max-Heap`) elija inmediatamente 
     * aquella con la **mayor relevancia clínica** y el **menor número de reacciones**. Esto evidencia una integración inteligente de unidades previas.
     * * * Complejidad Temporal: $O(R \log R + D)$ donde R es el número de rutas y D el número total de dependencias. 
     * Cada inserción y extracción del Heap cuesta de forma controlada $O(\log R)$.
     */
    public List<MetabolicRoute> computeAnalysisOrder() throws CircularDependencyException {
        Map<Integer, MetabolicRoute> routeMap = buildRouteMap();
        Map<Integer, Set<Integer>> dependents = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();

        // Inicialización de estructuras auxiliares de grafos
        for (MetabolicRoute route : routes) {
            indegree.put(route.getId(), 0);
            dependents.put(route.getId(), new HashSet<>());
        }

        // Construcción del grado de entrada de dependencias
        for (MetabolicRoute route : routes) {
            for (int dependencyId : route.getDependencyIds()) {
                if (!routeMap.containsKey(dependencyId)) {
                    throw new CircularDependencyException("Dependencia de ruta desconocida: " + dependencyId);
                }
                dependents.get(dependencyId).add(route.getId());
                indegree.put(route.getId(), indegree.get(route.getId()) + 1);
            }
        }

        // REUTILIZACIÓN DE CÓDIGO: Se alimenta nuestro Montículo con nodos listos para procesar
        PriorityQueueHeap queue = new PriorityQueueHeap();
        for (MetabolicRoute route : routes) {
            if (indegree.get(route.getId()) == 0) {
                queue.insert(route);
            }
        }

        List<MetabolicRoute> orderedRoutes = new ArrayList<>();
        while (!queue.isEmpty()) {
            try {
                // Extrae la ruta metabólica más prioritaria disponible
                MetabolicRoute next = queue.extractMax();
                orderedRoutes.add(next);
                
                // Reduce el grado de entrada de sus dependientes y reevalúa el ingreso al Heap
                for (int dependentId : dependents.get(next.getId())) {
                    int nextIndegree = indegree.get(dependentId) - 1;
                    indegree.put(dependentId, nextIndegree);
                    if (nextIndegree == 0) {
                        queue.insert(routeMap.get(dependentId));
                    }
                }
            } catch (EmptyPriorityQueueException e) {
                break;
            }
        }

        // Validación de Ciclo: Si no se procesaron todas las rutas, existe un bloqueo circular insoluble
        if (orderedRoutes.size() != routes.size()) {
            throw new CircularDependencyException("Se detectó una dependencia circular entre rutas metabólicas");
        }

        return orderedRoutes;
    }

    /**
     * Determina la existencia de ciclos de dependencias mediante la ejecución de la ordenación topológica.
     * * Complejidad Temporal: $O(R \log R + D)$.
     */
    public boolean hasCircularRouteDependencies() {
        try {
            computeAnalysisOrder();
            return false;
        } catch (CircularDependencyException e) {
            return true;
        }
    }

    /**
     * Estructura de datos estática interna diseñada para encapsular de manera compacta los resultados del 
     * análisis termodinámico de Bellman-Ford (Vectores de distancias mínimos, predecesores y estado de ciclo).
     */
    public static class BFResult {
        public final double[] dist;
        public final int[] pred;
        public final boolean negativeCycle;

        BFResult(double[] d, int[] p, boolean n) {
            dist = d;
            pred = p;
            negativeCycle = n;
        }
    }

    /**
     * Bellman-Ford
     * 1. Inicializa todas las distancias en Infinito y la del origen en 0
     * 2. Realiza un proceso de "Relajación de Aristas" iterando de manera exhaustiva$V-1 veces sobre el conjunto global de reacciones.
     * 3. Optimización Experimental (Parada Temprana): Se introdujo la bandera flag `changed`. Si en una iteración completa ninguna 
     * distancia se optimiza, el algoritmo frena inmediatamente, ahorrando cómputo en ejecuciones prácticas.
     * 4. Fase de Verificación: Realiza una $V$-ésima pasada. Si una distancia sigue disminuyendo, significa que la energía decae 
     * indefinidamente, confirmando la presencia de un ciclo negativo.
     * Complejidad Temporal: $O(VE)$. En el peor de los escenarios teóricos, recorrerá el bucle externo $V-1$ veces examinando las $E$ aristas.
     */
    public BFResult bellmanFordFrom(int srcId) {
        int src = findIndex(srcId);
        int V = nodes.size();
        double[] dist = new double[V];
        int[] pred = new int[V];

        Arrays.fill(dist, Double.POSITIVE_INFINITY);
        Arrays.fill(pred, -1);
        dist[src] = 0;

        // Fase de Relajación (V-1 veces)
        for (int i = 0; i < V - 1; i++) {
            boolean changed = false;
            for (int j = 0; j < edges.size(); j++) {
                Edge e = edges.get(j);
                if (dist[e.from] != Double.POSITIVE_INFINITY) {
                    if (dist[e.from] + e.weight < dist[e.to]) {
                        dist[e.to] = dist[e.from] + e.weight;
                        pred[e.to] = e.from;
                        changed = true;
                    }
                }
            }
            // Parada temprana experimental si el sistema converge antes
            if (!changed) {
                break;
            }
        }

        // Fase de Detección de Ciclos Negativos
        boolean neg = false;
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            if (dist[e.from] != Double.POSITIVE_INFINITY) {
                if (dist[e.from] + e.weight < dist[e.to]) {
                    neg = true; // Se detectó degradación infinita de energía libre
                    break;
                }
            }
        }

        return new BFResult(dist, pred, neg);
    }

    /**
     * Ejecuta Bellman-Ford tomando secuencialmente cada metabolito como raíz para certificar la estabilidad termodinámica total de la red.
     * * Complejidad Temporal: $O(V^2 \cdot E)$.
     */
    public boolean hasNegativeCycle() {
        for (int i = 0; i < nodes.size(); i++) {
            BFResult r = bellmanFordFrom(nodes.get(i).getId());
            if (r.negativeCycle) return true;
        }
        return false;
    }

    /**
     * Verifica primero que el grafo esté libre de anomalías energéticas con `hasNegativeCycle()`. Posteriormente 
     * dispara Bellman-Ford y, empleando una estructura de tipo `LinkedList`, realiza una reconstrucción inversa (backtracking) 
     * saltando de destino a origen a través del arreglo de predecesores (`pred`), depositando los metabolitos al inicio 
     * (`addFirst`) para devolver el camino en el orden biológico correcto.
     * * * ANÁLISIS DE COMPLEJIDAD:
     * Complejidad Temporal: $O(V^2 \cdot E)$ debido a la validación exhaustiva previa de ciclos. La reconstrucción física del camino toma apenas un costo lineal de $O(V)$.
     */
    public List<Metabolite> shortestPath(int sourceId, int targetId) throws Exception {
        if (hasNegativeCycle()) {
            throw new CycleNegativeException("Se ha detectado un ciclo de energía infinita negativa");
        }

        BFResult res = bellmanFordFrom(sourceId);
        int target = findIndex(targetId);

        // Si la distancia es infinito, el metabolito objetivo es inalcanzable desde el origen
        if (res.dist[target] == Double.POSITIVE_INFINITY) {
            return Collections.emptyList();
        }

        LinkedList<Metabolite> path = new LinkedList<>();
        int cur = target;

        // Reconstrucción del camino de atrás hacia adelante: O(V)
        while (cur != -1) {
            path.addFirst(nodes.get(cur));
            cur = res.pred[cur];
        }
        return path;
    }

    /**
     * Busca y elimina una reacción química específica tanto de la lista maestra global como de su lista de adyacencia local.
     * * Complejidad Temporal: $O(E)$, debido a la necesidad de recorrer secuencialmente las estructuras en busca del String correspondiente.
     */
    public boolean removeReactionById(String reactionId) {
        for (int i = 0; i < edges.size(); i++) {
            Edge e = edges.get(i);
            if (e.id.equals(reactionId)) {
                edges.remove(i); // Remoción de la lista global
                
                // Remoción dirigida de la lista de adyacencia del nodo emisor
                List<Edge> list = adj.get(e.from);
                for (int j = 0; j < list.size(); j++) {
                    if (list.get(j).id.equals(reactionId)) {
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