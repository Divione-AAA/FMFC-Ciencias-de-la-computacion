package metabolico;

/**
 * Esta clase representa la abstracción de un Vértice o Nodo dentro del grafo dirigido 
 * que modela nuestra red metabólica. Actúa como un POJO (Plain Old Java Object) ligero e inmutable.
 * Inmutabilidad Estricta: Todos sus atributos se definen como `final` para forzar que la 
 * identidad y los metadatos de un metabolito permanezcan idénticos e inalterables una vez instanciados. 
 * Esto blinda al sistema contra corrupciones accidentales de datos en memoria durante la ejecución de 
 * algoritmos intensivos de caminos mínimos como Bellman-Ford.
 * Soporte Indispensable para Colecciones Hash: Se implementa una sobreescritura matemática explícita y coherente 
 * de los métodos `equals` y `hashCode` basándose exclusivamente en el identificador único (`id`). Esto es 
 * crítico y obligatorio para que objetos de este tipo puedan actuar correctamente como claves dentro de estructuras 
 * complejas reutilizadas en la red, tales como `HashMap` o `HashSet`.
 */
public class Metabolite {

    // Identificador numérico único que indexa de manera unívoca al nodo dentro del grafo.
    private final int id;
    
    // Nombre común o bioquímico de la entidad (ej: "Glucosa", "ATP").
    private final String name;
    
    // Representación de su fórmula química estructural o anotación corta de datos.
    private final String formula;

    /**
     * Inicializa los atributos inmutables que componen la identidad del nodo.
     * Complejidad Temporal: O(1).
     */
    public Metabolite(int id, String name, String formula) {
        this.id = id;
        this.name = name;
        this.formula = formula;
    }

    /**
     * Obtiene el identificador único del metabolito dentro de la red.
     * Complejidad Temporal: O(1).
     */
    public int getId() { 
        return id; 
    }

    /**
     * Obtiene el nombre amigable de la sustancia biológica.
     * Complejidad Temporal: O(1).
     */
    public String getName() { 
        return name; 
    }

    /**
     * Obtiene la descripción o fórmula corta molecular asociada.
     * Complejidad Temporal: O(1).
     */
    public String getFormula() { 
        return formula; 
    }

    /**
     * Sobreescritura del formateo de texto para depuración y volcado en consola de casos de prueba automáticos.
     * * MODIFICACIÓN REALIZADA: Incorpora un operador ternario interno para controlar de manera segura 
     * valores nulos (`formula == null ? "-" : formula`), evitando excepciones de puntero nulo y garantizando 
     * una visualización legible en los reportes de salida.
     * Complejidad Temporal: O(1).
     */
    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", id, name, formula == null ? "-" : formula);
    }

    /**
     * Determina bajo qué condiciones dos instancias de metabolito representan la misma entidad biológica.
     * * REGLA IMPLEMENTADA: Se aplica un enfoque de identidad por ID único. Si los identificadores numéricos coinciden, 
     * se asume que es el mismo vértice, independientemente de que las cadenas de texto del nombre o fórmula varíen.
     * * COMPORTAMIENTO EXPERIMENTAL: Previene la duplicidad lógica de nodos en las búsquedas secuenciales 
     * de la red y optimiza el rendimiento del método `List.contains()`.
     * Complejidad Temporal: O(1), al realizar únicamente comparaciones primitivas de referencias y enteros.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Optimización de identidad por referencia: O(1)
        if (!(o instanceof Metabolite)) return false; // Protección de tipos y nulos
        
        Metabolite m = (Metabolite) o;
        return this.id == m.id; // Comparación determinista por ID único
    }

    /**
     * FUNCIÓN HASH DISPERSA (hashCode)
     * Genera el valor de dispersión entero de la instancia basado contractualmente en el mismo criterio que `equals`.
     * * JUSTIFICACIÓN: Obligatorio para asegurar un rendimiento de tiempo constante O(1) en operaciones de 
     * inserción, borrado y consulta dentro de tablas hash de alto rendimiento (`HashSet` / `HashMap`), evitando 
     * colisiones masivas de buckets en memoria.
     * Complejidad Temporal: O(1).
     */
    @Override
    public int hashCode() { 
        return Integer.hashCode(id); 
    }
}