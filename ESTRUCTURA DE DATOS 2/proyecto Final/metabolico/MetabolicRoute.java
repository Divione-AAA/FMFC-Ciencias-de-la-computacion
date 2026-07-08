package metabolico;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Esta clase actúa como el Modelo de Datos que encapsula 
 * una ruta metabólica de interés clínico dentro del sistema. 
 * Identificación Autogestionada: Utiliza un campo estático (`nextRouteId`) para garantizar 
 * identificadores únicos correlativos de manera automática, reduciendo el riesgo de colisiones 
 * de IDs en las pruebas automatizadas.
 * Encapsulamiento y Protección de Datos: Los atributos críticos como origen, destino y relevancia 
 * clínica se definen como `final` para forzar su inmutabilidad tras la instanciación, asegurando la 
 * integridad de las rutas durante las fases de ordenación en el Heap y la resolución en el Grafo.
 * Gestión de Dependencias: Mantiene una lista modular de IDs de otras rutas de las cuales depende, 
 * sirviendo como base directa para que el algoritmo de Kahn (`computeAnalysisOrder`) calcule los grados de entrada.
 */
public class MetabolicRoute {

    // Contador estático global para la asignación secuencial y única de IDs a cada instancia.
    private static int nextRouteId = 1;
    
    // Atributos inmutables identificativos y de negocio
    private final int id;
    private final int sourceId;
    private final int targetId;
    private final double clinicalRelevance; // Criterio primario de ordenación en el Max-Heap
    
    // Atributo dinámico mutable
    private int reactionCount;              // Criterio secundario de desempate en el Heap (Min-Heap)
    
    // Lista de adyacencia de dependencias (Grafo de precedencia de tareas)
    private final List<Integer> dependencyIds;

    /**
     * Inicializa los componentes de la ruta y aplica reglas de negocio preventivas.
     * Se utiliza `Math.max(0, reactionCount)` para blindar la estructura 
     * contra inconsistencias de software o físicos (conteo de reacciones negativo imposible en la naturaleza).
     * Complejidad Temporal: O(1).
     */
    public MetabolicRoute(int sourceId, int targetId, double clinicalRelevance, int reactionCount) {
        this.id = nextRouteId++;
        this.sourceId = sourceId;
        this.targetId = targetId;
        this.clinicalRelevance = clinicalRelevance;
        this.reactionCount = Math.max(0, reactionCount);
        this.dependencyIds = new ArrayList<>();
    }

    /**
     * Obtiene el identificador único de la ruta.
     * Complejidad Temporal: O(1).
     */
    public int getId() {
        return id;
    }

    /**
     * Obtiene el ID del metabolito origen (punto de partida en la red).
     * Complejidad Temporal: O(1).
     */
    public int getSourceId() {
        return sourceId;
    }

    /**
     * Obtiene el ID del metabolito destino (punto final/producto en la red).
     * Complejidad Temporal: O(1).
     */
    public int getTargetId() {
        return targetId;
    }

    /**
     * Obtiene el factor de importancia clínica asignado para el triaje en el Heap.
     * Complejidad Temporal: O(1).
     */
    public double getClinicalRelevance() {
        return clinicalRelevance;
    }

    /**
     * Obtiene el número de reacciones (pasos de aristas) que componen la ruta mínima.
     * Complejidad Temporal: O(1).
     */
    public int getReactionCount() {
        return reactionCount;
    }

    /**
     * Actualiza el recuento de reacciones tras realizar optimizaciones o recalcular caminos con Bellman-Ford.
     * Permite al sistema reajustar dinámicamente las prioridades secundarias si el grafo cambia.
     * Complejidad Temporal: O(1).
     */
    public void setReactionCount(int reactionCount) {
        this.reactionCount = Math.max(0, reactionCount);
    }

    /**
     * 
     * Registra de manera dirigida que esta ruta requiere la finalización de otra antes de ser procesada.
     * Incorpora un mecanismo de prevención interna (`!contains`) 
     * para evitar la duplicación accidental de dependencias, lo cual alteraría destructivamente los cálculos 
     * de grados de entrada (indegree) en el algoritmo de ordenamiento topológico.
     * * Complejidad Temporal: O(D) donde D es el número de dependencias actuales en la lista (debido a la búsqueda de `contains`).
     */
    public void addDependency(int dependencyRouteId) {
        if (!dependencyIds.contains(dependencyRouteId)) {
            dependencyIds.add(dependencyRouteId);
        }
    }

    /**
     * Devuelve una envoltura inmutable de la lista utilizando `Collections.unmodifiableList`. 
     * Esto evita que clases externas alteren de manera fraudulenta o errónea las dependencias desde fuera de la clase, 
     * obligando a usar estrictamente el método modular `addDependency`.
     * Complejidad Temporal: O(1).
     */
    public List<Integer> getDependencyIds() {
        return Collections.unmodifiableList(dependencyIds);
    }

    /**
     * Formatea de manera clara legibles las métricas de la ruta para salidas por consola en casos de prueba automáticos.
     * Facilita el análisis experimental exigido en la entrega.
     * Complejidad Temporal: O(D) para procesar la cadena de texto de la lista de dependencias.
     */
    @Override
    public String toString() {
        return String.format("Route[%d] %d -> %d | relevance=%.2f | reactions=%d | deps=%s",id, sourceId, targetId, clinicalRelevance, reactionCount, dependencyIds);
    }
}