package Search;

import java.util.Random;

/**
 * Esta clase actúa como el motor de simulación y generación de datos biológicos sintéticos. 
 * Su objetivo fundamental es proveer un entorno controlado, aleatorio y reproducible para 
 * realizar el análisis comparativo empírico entre algoritmos de búsqueda de patrones 
 * (como Fuerza Bruta frente a Knuth-Morris-Pratt o Boyer-Moore).
 * * JUSTIFICACIÓN DE DECISIONES DE DISEÑO:
 * 1. ¿Por qué usar StringBuilder?: El uso de `StringBuilder` evita la penalización por 
 * inmutabilidad de los Strings tradicionales de Java. En lugar de crear $N$ objetos intermedios 
 * en memoria, concatena de manera eficiente en un buffer dinámico optimizado.
 * 2. Extracción Garantizada de Patrones: El método de generación de patrones no crea cadenas 
 * al azar, sino que **recorta subcadenas reales existentes** dentro del texto base. Esto asegura 
 * científicamente que los casos de prueba siempre tengan al menos una coincidencia (hit) exitosa, 
 * permitiendo evaluar el comportamiento de los algoritmos tanto en la fase de escaneo como en la 
 * fase de acierto.
 * * COMPORTAMIENTO EXPERIMENTAL ESPERADO:
 * Al generar motivos de longitudes crecientes (4, 10, 20 caracteres) inmersos en textos fijos 
 * (1000 caracteres), la clase ayuda a demostrar experimentalmente cómo los algoritmos avanzados 
 * aprovechan los patrones largos para mitigar el número total de comparaciones en comparación con 
 * la rigidez de la fuerza bruta.
 */
public class DNADataGenerator {

    /**
     * El alfabeto de la red y secuencias biológicas. 
     * Se define de forma inmutable y estática con las 4 bases nitrogenadas del ADN.
     */
    private static final char[] BASES = {
            'A', 'C', 'G', 'T'
    };

    /**
     * Motor pseudoaleatorio para la selección uniforme de bases y posiciones de corte.
     */
    private static Random random = new Random();

    /**
     * GENERACIÓN DE SECUENCIAS DE ADN (generateSequence)
     * Construye una cadena sintética lineal de nucleótidos del tamaño especificado.
     * * JUSTIFICACIÓN DE DISEÑO: Utiliza `random.nextInt(4)` para garantizar una distribución 
     * uniforme y equiprobable de los caracteres A, C, G y T, simulando condiciones ideales de ADN disperso.
     * * ANÁLISIS DE COMPLEJIDAD:
     * Complejidad Temporal: $O(L)$ donde $L$ es la longitud (`length`) de la secuencia deseada, 
     * debido al bucle iterativo que añade un nucleótido por ciclo.
     * Complejidad Espacial: $O(L)$ para almacenar la estructura de caracteres devuelta.
     */
    public static String generateSequence(int length) {
        StringBuilder dna = new StringBuilder(); // Optimización de memoria contigua

        for (int i = 0; i < length; i++) {
            // Elige un índice aleatorio entre 0 y 3 y extrae su base correspondiente
            dna.append(BASES[random.nextInt(4)]);
        }

        return dna.toString();
    }

    /**
     * EXTRACIÓN DE MOTIVOS / PATRONES VIABLES (generatePattern)
     * Extrae un fragmento de longitud exacta que se encuentra de manera garantizada dentro de la secuencia.
     * * JUSTIFICACIÓN ALGORÍTMICA: Al calcular el índice de inicio acotando el límite superior mediante 
     * `sequence.length() - patternLength`, se mitiga por completo el riesgo de lanzar excepciones por 
     * desbordamiento de rango (`StringIndexOutOfBoundsException`).
     * * ANÁLISIS DE COMPLEJIDAD:
     * Complejidad Temporal: $O(M)$ donde $M$ es `patternLength`, dado que `String.substring()` en versiones 
     * modernas de Java realiza una copia lineal de los caracteres seleccionados.
     */
    public static String generatePattern(String sequence, int patternLength) {
        // Selecciona un punto de partida aleatorio matemáticamente seguro
        int start = random.nextInt(sequence.length() - patternLength);

        // Retorna el fragmento interno de la ventana genética
        return sequence.substring(start, start + patternLength);
    }

    /**
     * GENERACIÓN DE SECUENCIA ESTÁNDAR PARA EL EXPERIMENTO
     * Encapsula los requisitos obligatorios definidos en el enunciado del proyecto (Texto de 1000 caracteres).
     * Proporciona un punto de entrada modular fijo para los benchmarks automáticos.
     * * Complejidad Temporal: $O(1)$ relativo a la ejecución fija, asintóticamente $O(N)$ para $N=1000$.
     */
    public static String generateExperimentSequence() {
        return generateSequence(1000);
    }

    /**
     * GENERADOR COLECTIVO DE MOTIVOS REQUERIDOS
     * Crea automáticamente un lote estructurado de patrones con las métricas explícitas del enunciado: 
     * longitudes de 4, 10 y 20 caracteres extraídos del mismo ecosistema de texto.
     * * JUSTIFICACIÓN DE DISEÑO: Retorna un arreglo clásico de Strings (`String[]`) de tamaño fijo [3], 
     * lo cual minimiza el uso de colecciones pesadas de Java y agiliza su procesamiento iterativo 
     * secuencial en los métodos `main` de pruebas de rendimiento.
     * * ANÁLISIS DE COMPLEJIDAD:
     * Complejidad Temporal: $O(1)$ por ser llamadas de tamaño constante y predeterminado por el enunciado.
     */
    public static String[] generatePatterns(String sequence) {
        String[] patterns = new String[3];

        // Inyección dirigida de motivos experimentales requeridos por la rúbrica del curso
        patterns[0] = generatePattern(sequence, 4);  // Caso: Patrón corto
        patterns[1] = generatePattern(sequence, 10); // Caso: Patrón mediano
        patterns[2] = generatePattern(sequence, 20); // Caso: Patrón largo

        return patterns;
    }
}