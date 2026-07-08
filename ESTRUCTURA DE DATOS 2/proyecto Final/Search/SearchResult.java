package Search;

import java.util.ArrayList;

/**
Esta clase actúa como un Objeto de Transferencia de Datos (DTO) y contenedor métrico. Su propósito 
fundamental dentro de la entrega es unificar, estructurar y estandarizar los resultados arrojados por 
cualquier algoritmo que implemente la interfaz `DNAPatternSearch`.
*/
public class SearchResult {

    // Lista dinámica que registra los índices de inicio en el texto donde se encontró el patrón de ADN.
    private ArrayList<Integer> posiciones;

    // Contador acumulativo que mide el esfuerzo experimental (número de evaluaciones de caracteres realizadas).
    private int comparaciones;

    // Nombre identificativo del algoritmo que generó este resultado (ej: "Fuerza Bruta", "KMP").
    private String algoritmo;

    /**
     * CONSTRUCTOR DE LA CLASE SEARCHRESULT O(1)
     */
    public SearchResult(String algoritmo) {
        this.algoritmo = algoritmo;
        this.posiciones = new ArrayList<>(); 
        this.comparaciones = 0;              
    }

    /**
     * REGISTRO DE COINCIDENCIAS (agregarPosicion)
     * Almacena el índice de partida de un alineamiento exitoso descubierto en el texto genético.
     * O(1) 
    */
    public void agregarPosicion(int pos) {
        posiciones.add(pos);
    }

    /**
     * CONTADOR DE ESFUERZO COMPUTACIONAL (agregarComparacion)
     * Incrementa en una unidad el registro de operaciones de comparación elementales.
     * Este método es invocado explícitamente por los bucles internos de los algoritmos 
     * antes de evaluar si dos caracteres son iguales. Representa la métrica empírica directa del coste temporal.
     * O(1)
     */
    public void agregarComparacion() {
        comparaciones++;
    }

    /**
     * Retorna la lista con los índices de todas las posiciones geolocalizadas del motivo.
     * O(1)
     */
    public ArrayList<Integer> getPosiciones() {
        return posiciones;
    }

    /**
     * Obtiene el total de comparaciones de caracteres acumuladas durante el escaneo biológico.
    O(1)
    */
    public int getComparaciones() {
        return comparaciones;
    }

    /**
     * Obtiene el nombre del algoritmo asociado a este reporte, permitiendo identificar la procedencia de los datos.
    O(1)
     */
    public String getAlgoritmo() {
        return algoritmo;
    }

    /**
     * CÓMPUTO DE RECURRENCIA TOTAL (totalCoincidencias)
     * Evalúa la cantidad total de veces que el patrón de ADN aparece repetido de forma exacta en la secuencia.
    O(1)
    */
    public int totalCoincidencias() {
        return posiciones.size();
    }
}