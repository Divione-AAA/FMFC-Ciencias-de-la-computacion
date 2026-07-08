package Search;

/**
 * Implementa el algoritmo de búsqueda de patrones por Fuerza Bruta (String Matching Naive) 
 * adaptado a la interfaz `DNAPatternSearch` para la identificación de secuencias genéticas.
 * Aunque es un enfoque simple, la fuerza bruta es útil como línea base de comparación (benchmark). 
 * Evalúa de manera exhaustiva todas las ventanas posibles del texto genético T (de longitud n) 
 * para encontrar el patrón de ADN P (de longitud m).
 * Complejidad Temporal Peor Caso: $O((n - m + 1) \cdot m) \approx O(n \cdot m)$. Ocurre en textos altamente 
 * repetitivos (por ejemplo, secuencias largas de poliaquilas como "AAAAA..." buscando "AAAAB").
 * Complejidad Temporal Mejor Caso: $O(n)$ si el primer carácter de la ventana no coincide con el del patrón, 
 * rompiendo el bucle interno inmediatamente.
 * Justificación Experimental: A través de `r.agregarComparacion()`, esta clase permite demostrar en las 
 * pruebas empíricas que este algoritmo realiza significativamente más comparaciones que enfoques optimizados 
 * (como Boyer-Moore o KMP) ante textos extensos y patrones de ADN largos.
 */
public class BruteForceSearch implements DNAPatternSearch {

    /**
     * Escanea el texto carácter por carácter desplazando el patrón de izquierda a derecha.
     * * MODIFICACIÓN PARA ANÁLISIS: Incorpora contadores dinámicos dentro del bucle de comparación 
     * (`r.agregarComparacion()`) para alimentar las métricas experimentales exigidas en el apartado 4 del enunciado.
     * * Parámetros:
     * @param T Cadena de texto base (Secuencia de ADN donde se busca).
     * @param P Patrón o motivo genético que se desea localizar.
     * @return SearchResult Objeto contenedor con las posiciones de coincidencia y el costo experimental.
     */
    @Override
    public SearchResult search(String T, String P) {

        // Inicialización del contenedor de resultados con la etiqueta del algoritmo
        SearchResult r = new SearchResult("Fuerza Bruta");

        // VALIDACIÓN DE DISEÑO: Si el patrón es más largo que el texto, la coincidencia es imposible.
        // Se aborta tempranamente en O(1) evitando cálculos innecesarios.
        if (P.length() > T.length()) {
            return r;
        }

        int n = T.length();
        int m = P.length();
        int j;

        // Bucle Externo: Define el índice de inicio de la ventana actual en el Texto.
        // Se acota hasta (n - m) porque más allá el texto restante es más corto que el patrón.
        for (int i = 0; i <= n - m; i++) {
            j = 0;
            
            // Bucle Interno: Compara carácter por carácter el patrón contra la ventana del texto.
            while (j < m) {
                // REGISTRO EXPERIMENTAL: Se contabiliza de forma estricta cada intento de emparejamiento.
                // Esencial para el análisis comparativo final frente a otros enfoques del proyecto.
                r.agregarComparacion();

                // Si ocurre un desajuste (mismatch), se rompe el bucle interno (Parada temprana de la ventana)
                if (T.charAt(i + j) != P.charAt(j)) {
                    break;
                }
                j++; // Avanza al siguiente carácter del patrón
            }

            // Si el índice j alcanzó el tamaño m, significa que toda la ventana coincidió con el patrón.
            if (j == m) {
                // Se registra el índice de inicio 'i' de la coincidencia hallada: O(1)
                r.agregarPosicion(i);
            }
        }

        return r;
    }
}