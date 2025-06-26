public class SolicitudA extends Solicitud {
    private Double costo;
    public SolicitudA(Double costo,Integer numero, String codigo, String nombre) throws Exception {
        super(numero, codigo, nombre);
        this.costo = costo;

    }
}
