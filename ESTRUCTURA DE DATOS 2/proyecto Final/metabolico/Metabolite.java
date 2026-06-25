package metabolico;

/**
 * Metabolite: representa un nodo en la red metabólica.
 *
 * Documentación integrada:
 * - Estructura usada: POJO sencillo con identificador entero y metadatos.
 * - Justificación: los metabolitos son entidades ligeras; usar un objeto facilita
 *   anotar información (nombre, fórmula, comentarios) y pasar referencias.
 *
 * Reutilización y diseño:
 * - Esta clase es intencionalmente simple para ser reutilizable desde otras
 *   partes del proyecto (algoritmos, pruebas, serialización).
 */
public class Metabolite {
    private final int id;          // identificador único dentro de la red
    private final String name;     // nombre amigable (p. ej. "Glucosa")
    private final String formula;  // fórmula o descripción corta

    public Metabolite(int id, String name, String formula) {
        this.id = id;
        this.name = name;
        this.formula = formula;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getFormula() { return formula; }

    @Override
    public String toString() {
        return String.format("[%d] %s (%s)", id, name, formula==null?"-":formula);
    }

    @Override
    public boolean equals(Object o) {
        if(this==o) return true;
        if(!(o instanceof Metabolite)) return false;
        Metabolite m = (Metabolite)o;
        return this.id == m.id;
    }

    @Override
    public int hashCode() { return Integer.hashCode(id); }
}
