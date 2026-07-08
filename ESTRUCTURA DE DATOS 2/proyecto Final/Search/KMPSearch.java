package Search;

/**
 * Implementación del algoritmo de búsqueda de patrones Knuth-Morris-Pratt (KMP) 
 * adaptado a la interfaz `DNAPatternSearch` para la secuenciación de motivos genéticos.
 * KMP resuelve esto mediante el preprocesamiento del patrón (Función de Falla), que almacena la longitud 
 * del prefijo más largo que también es un sufijo propio. Esto permite "comprometerse" con el texto y, 
 * ante un desajuste, desplazar inteligentemente el patrón de forma dinámica sin hacer retroceder nunca 
 * el puntero del texto `i`.
 * Complejidad Temporal Peor Caso: $O(n + m)$, donde $n$ es la longitud del texto y $m$ la del patrón. 
 * Es lineal e independiente de las repeticiones del texto.
 * Análisis comparativo experimental: En las pruebas empíricas con motivos medianos y largos (10 y 20 bases), 
 * KMP arroja un número radicalmente menor de comparaciones en contraste con la fuerza bruta, evidenciando 
 * la alta eficiencia de reutilizar la información precalculada en el arreglo `fail`.
 */
public class KMPSearch implements DNAPatternSearch {

    /**
     * Construye la tabla de prefijos/sufijos propios conocidos en la literatura como LPS (Longest Prefix Suffix).
     * Mapea para cada índice el tamaño de la subcadena repetida más larga al inicio del patrón.
     * * REUTILIZACIÓN Y DISEÑO: Modela una estrategia de Programación Dinámica subyacente que evita 
     * recalcular alineamientos redundantes sobre el motivo biológico.
     * * ANÁLISIS DE COMPLEJIDAD:
     * Complejidad Temporal: $O(m)$, donde $m$ es la longitud del patrón `P.length()`. En cada paso del bucle 
     * `while`, el puntero `i` avanza o el puntero de prefijos `j` retrocede acotadamente a través de la tabla.
     */
    public int[] funcionFalla(String P) {

        int[] falla = new int[P.length()];

        falla[0] = 0; // Por definición, un único carácter no tiene un sufijo propio válido
        int j = 0;    // Longitud del prefijo coincidente actual
        int i = 1;    // Puntero exploratorio que recorre el patrón de izquierda a derecha

        while (i < P.length()) {
            // Caso 1: Los caracteres coinciden, se extiende el prefijo/sufijo común
            if (P.charAt(j) == P.charAt(i)) {
                falla[i] = j + 1;
                i++;
                j++;
            }
            // Caso 2: Desajuste interno pero existe un prefijo previo menor al que podemos saltar
            else if (j > 0) {
                j = falla[j - 1]; // Retroceso inteligente guiado por los datos ya calculados
            }
            // Caso 3: Desajuste total, no hay prefijo común viable para esta subcadena
            else {
                falla[i] = 0;
                i++;
            }
        }
        return falla;
    }

    /**
     * Escanea el texto biológico buscando alineamientos exactos con el patrón utilizando la tabla de fallas.
     *  Incorpora llamadas a `r.agregarComparacion()` inmediatamente 
     * antes de evaluar los caracteres en la estructura condicional, capturando fidedignamente el coste real 
     * de ejecución del algoritmo.
     * * Complejidad Temporal: $O(n)$, ya que el puntero `i` que indexa el texto $T$ nunca retrocede en sus posiciones.
     */
    @Override
    public SearchResult search(String T, String P) {

        SearchResult r = new SearchResult("KMP");

        // VALIDACIÓN DE DISEÑO: Si el tamaño del patrón excede al texto, se aborta en O(1)
        if (P.length() > T.length()) {
            return r;
        }

        // Fase de Preprocesamiento: O(m)
        int[] fail = funcionFalla(P);

        int n = T.length();
        int m = P.length();
        int i = 0; // Puntero exploratorio sobre el Texto T
        int j = 0; // Puntero comparativo sobre el Patrón P

        // Fase de Emparejamiento: O(n)
        while (i < n) {

            // REGISTRO DE CONTROL EXPERIMENTAL: Requisito fundamental para las métricas del informe
            r.agregarComparacion();

            // Si hay coincidencia entre la base del texto y la del patrón
            if (P.charAt(j) == T.charAt(i)) {

                // Si 'j' alcanzó el último carácter (m-1), se ha descubierto el motivo completo
                if (j == m - 1) {
                    // Se registra el índice de inicio real del patrón dentro del texto T
                    r.agregarPosicion(i - m + 1);
                    
                    // Modificación algorítmica estándar de KMP: Permite continuar la búsqueda de 
                    // manera solapada en el resto del texto sin perder eficiencia.
                    j = fail[j];
                    i++;
                } else {
                    // La coincidencia es parcial, se avanza linealmente en ambos vectores
                    i++;
                    j++;
                }
            } 
            // Si hay desajuste (mismatch) y el patrón ya llevaba bases emparejadas previas
            else if (j > 0) {
                // DECISIÓN CRÍTICA DE DISEÑO: Se evita reiniciar 'i'. El patrón se desplaza 
                // a la derecha reutilizando la información estructural del arreglo 'fail'.
                j = fail[j - 1];
            } 
            // Si hay desajuste y no se llevaba ninguna coincidencia previa en la ventana actual
            else {
                i++; // Simplemente se avanza al siguiente nucleótido del texto
            }
        }
        return r;
    }
}