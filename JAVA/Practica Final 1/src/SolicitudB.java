import java.util.ArrayList;

public class SolicitudB extends Solicitud {
    private ArrayList<Double> costos;
    public SolicitudB(Integer numero, String codigo, String nombre) throws Exception {
        super(numero, codigo, nombre);
    }

    public void AddCosto(Double costo) throws Exception {
        if (costo == null || costo < 0) {
            throw new ConstException("Costo invalido");
        }
        costos.add(costo);
    }

    public boolean costo(Double costo) {
        for(Double i: costos){
            if(i>costo){
                return true;
            }
        }
        return false;
    }
}
