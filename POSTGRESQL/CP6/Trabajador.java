public class Trabajador {

    private String idtrabajador;
    private String nombre;
    private float tarifahr;
    private String oficio;
    private String supervisor;

    public Trabajador(String idtrabajador, String nombre, float tarifahr, String oficio, String supervisor) {
        this.idtrabajador = idtrabajador;
        this.nombre = nombre;
        this.tarifahr = tarifahr;
        this.oficio = oficio;
        this.supervisor = supervisor;
    }

    public String getNombre() {
        return nombre;
    }
}
