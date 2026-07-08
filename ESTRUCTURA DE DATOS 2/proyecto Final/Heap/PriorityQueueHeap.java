package Heap;

import java.util.ArrayList;
import metabolico.MetabolicRoute;

/**
 * Clase wrapper que actúa como nodo dentro del Montículo (Heap). 
 * Se diseñó de esta manera para encapsular el objeto de negocio 'MetabolicRoute' 
 * permitiendo una modularización limpia y facilitando futuras extensiones (como indexación 
 * dinámica o almacenamiento de metadatos del nodo en el heap) sin alterar la lógica de la ruta.
 */
class HeapNode {

    /*
     * Clase que almacena una ruta metabólica.
     */
    MetabolicRoute route;

    public HeapNode(MetabolicRoute route) {
        this.route = route;
    }
}

/**
 * Se implementa una Cola de Prioridad basada en un Montículo Binario Máximo (Max-Heap).
 * 1. Se utiliza un 'ArrayList' subyacente para representar el árbol binario.
 * Esto evita la sobrecarga de memoria de los punteros de nodos tradicionales y permite un 
 * acceso aleatorio indexado en tiempo constante O(1), optimizando las fórmulas de parentesco 
 * (hijos: 2i+1, 2i+2; padre: (i-1)/2).
 *  Se reutilizó la estructura de montículo desarrollada en las 
 * prácticas base de la asignatura, pero modificando la regla de ordenación estática por una 
 * prioridad dinámica y compuesta adaptada al problema metabólico asignado.
 * En comparación con una lista enlazada o un arreglo ordenado (donde la inserción o extracción 
 * costaría O(N)), el Heap ofrece un comportamiento óptimo y balanceado con un costo 
 * logarítmico O(log N) para ambas operaciones, ideal para procesar grandes flujos de rutas metabólicas.
 */
public class PriorityQueueHeap {

    /**
     * El árbol completo representado secuencialmente mediante un ArrayList.
     * Mapea perfectamente la estructura de árbol casi completo del montículo en memoria contigua.
     */
    private ArrayList<HeapNode> heap;

    /**
     * Constructor de la clase.
     * Inicializa la estructura de almacenamiento. Complejidad: O(1).
     */
    public PriorityQueueHeap() {
        heap = new ArrayList<>();
    }

    /**
     * Intercambia de posición dos nodos del montículo usando un elemento temporal.
     * Complejidad Temporal: O(1) ya que ArrayList.set() y get() operan en tiempo constante.
     */
    private void swap(int i, int j) {
        HeapNode temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    /**
     * Este método implementa la lógica de precedencia modificada exclusivamente para este proyecto.
     * Se evalúa bajo un enfoque de prioridad compuesta:
     * 1) Mayor relevancia clínica (Criterio Max-Heap de nivel primario).
     * 2) Menor número de reacciones (Criterio Min-Heap de nivel secundario/desempate).
     * * Justificación: Permite procesar de manera urgente las rutas críticas para la salud, 
     * y ante la misma importancia, prioriza la eficiencia biológica (la ruta más corta/rápida).
     * Complejidad Temporal: O(1).
     */
    private boolean higherPriority(HeapNode a, HeapNode b) {
        // Criterio 1: Mayor relevancia clínica
        if (a.route.getClinicalRelevance() > b.route.getClinicalRelevance()) {
            return true;
        }
        // Criterio 2 (Desempate): Menor número de reacciones
        if (a.route.getClinicalRelevance() == b.route.getClinicalRelevance()) {
            return a.route.getReactionCount() < b.route.getReactionCount();
        }
        return false;
    }

    /**
     * Inserta el nuevo elemento al final de la estructura (manteniendo la propiedad de forma del árbol) 
     * y luego invoca a siftUp para restaurar la propiedad de orden del montículo hacia arriba.
     * Complejidad Temporal: O(log N). En el peor de los casos, la nueva ruta metabólica tiene la máxima 
     * prioridad y debe trepar por toda la altura del árbol, la cual está acotada por log2(N).
     */
    public void insert(MetabolicRoute route) {
        HeapNode nuevo = new HeapNode(route);
        heap.add(nuevo); // Inserción al final: O(1) amortizado
        siftUp(heap.size() - 1); // Rebalanceo ascendente: O(log N)
    }

    /**
     * Extrae el elemento raíz (máxima prioridad garantizada por el Max-Heap). Para mantener el árbol 
     * casi completo, toma el último elemento del ArrayList, lo coloca en la raíz, y ejecuta siftDown 
     * para hundir el elemento hasta su posición correcta según las reglas de prioridad.
     * Complejidad Temporal: O(log N). El proceso de hundimiento realiza comparaciones a lo largo 
     * de la altura del árbol (log2(N)), lo cual es altamente eficiente para un despachador dinámico.
     */
    public MetabolicRoute extractMax() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("La cola está vacía");
        }

        MetabolicRoute max = heap.get(0).route; // Guarda la raíz prioritaria
        HeapNode ultimo = heap.remove(heap.size() - 1); // Remueve el último elemento: O(1)

        if (!heap.isEmpty()) {
            heap.set(0, ultimo); // Coloca el último en la raíz
            siftDown(0); // Rebalanceo descendente: O(log N)
        }

        return max;
    }

    /**
     * Compara el nodo actual en el índice 'i' con sus hijos izquierdo y derecho. Determina cuál de los 
     * tres tiene la prioridad más alta (usando higherPriority). Si un hijo es más prioritario, se 
     * intercambia con el padre y el ciclo continúa bajando por esa rama.
     * Complejidad Temporal: O(log N). En cada iteración desciende un nivel en el árbol binario. El número 
     * máximo de iteraciones está limitado por la altura del árbol h = log2(N).
     */
    private void siftDown(int i) {
        while (true) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;
            int largest = i;

            // Compara con el hijo izquierdo aplicando los criterios compuestos
            if (left < heap.size() && higherPriority(heap.get(left), heap.get(largest))) {
                largest = left;
            }

            // Compara con el hijo derecho aplicando los criterios compuestos
            if (right < heap.size() && higherPriority(heap.get(right), heap.get(largest))) {
                largest = right;
            }

            // Si el padre ya es el más prioritario de la terna, la propiedad se cumple y rompe el ciclo
            if (largest == i) {
                break;
            }
            
            swap(i, largest);
            i = largest; // Actualiza el índice para seguir bajando
        }
    }

    /**
     * Compara el elemento en el índice 'i' con su nodo padre ubicado en (i-1)/2. Si el elemento actual 
     * cumple con tener mayor prioridad que su padre, se intercambian las posiciones y el proceso 
     * se repite recursiva o iterativamente hacia la raíz.
     * Complejidad Temporal: O(log N). Sube un nivel por iteración hasta la raíz del árbol de altura log2(N).
     */
    private void siftUp(int i) {
        while (i > 0) {
            int padre = (i - 1) / 2;

            // Si el hijo tiene mayor prioridad que el padre, se realiza el intercambio
            if (higherPriority(heap.get(i), heap.get(padre))) {
                swap(i, padre);
                i = padre; // Sube al índice del padre para continuar la verificación
            } else {
                break; // Si el orden es correcto, se detiene
            }
        }
    }

    /**
     * Devuelve el elemento raíz que contiene la máxima prioridad metabólica sin alterar la estructura.
     * Complejidad Temporal: O(1) debido al acceso directo al primer índice del ArrayList.
     */
    public MetabolicRoute getMax() throws EmptyPriorityQueueException {
        if (isEmpty()) {
            throw new EmptyPriorityQueueException("La cola está vacía");
        }
        return heap.get(0).route;
    }

    /**
     * Indica si existen rutas registradas en el montículo.
     * Complejidad Temporal: O(1).
     */
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    /**
     * Devuelve el volumen actual de elementos administrados por la cola de prioridad.
     * Complejidad Temporal: O(1).
     */
    public int size() {
        return heap.size();
    }
}