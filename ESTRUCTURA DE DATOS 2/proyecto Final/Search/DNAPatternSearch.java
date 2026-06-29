package Search;

/**
 * DNAPatternSearch
 *
 * Interfaz comun para algoritmos de busqueda
 * de patrones geneticos.
 *
 * Se reutiliza para comparar:
 *
 * - Fuerza Bruta
 * - KMP
 *
 * Ambos algoritmos deben devolver:
 *
 * - posiciones encontradas
 * - comparaciones realizadas
 */

public interface DNAPatternSearch {

    /*
    Busca un patron dentro de una secuencia
    */

    SearchResult search(
            String sequence,
            String pattern
    );

}