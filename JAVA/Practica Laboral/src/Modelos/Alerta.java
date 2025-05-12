package Modelos;

public class Alerta {
    public int sensorId;
    public String tipo;
    public double limite;
    public String sensorNombre;
    public String ubicacion;
    public String clasificacion;
    public double valorActual;
    public String nombre;  // Nuevo campo agregado

    // Constructor completo (verificar alertas)
    public Alerta(int sensorId, String tipo, double limite, String sensorNombre, String ubicacion, String clasificacion, double valorActual) {
        this.sensorId = sensorId;
        this.tipo = tipo;
        this.limite = limite;
        this.sensorNombre = sensorNombre;
        this.ubicacion = ubicacion;
        this.clasificacion = clasificacion;
        this.valorActual = valorActual;
    }

    // Constructor opcional con nombre incluido
    public Alerta(int sensorId, String tipo, double limite, String nombre) {
        this.sensorId = sensorId;
        this.tipo = tipo;
        this.limite = limite;
        this.nombre = nombre;
    }

    public String getMensaje() {
        return "El sensor \"" + sensorNombre + "\" en " + ubicacion + " (" + clasificacion + ") ha alcanzado el límite de " + valorActual + " en " + tipo;
    }
}
