package Sensores;

import java.util.ArrayList;

public class CalidadAire extends Sensores{

    private ArrayList<Integer> calidadAire;

    public CalidadAire(String ubicacion, String tipo, String entorno) {
        super(ubicacion, tipo, entorno);
    }

    public void addCalidadAire(Integer t) {
        calidadAire.add(t);
    }

    public ArrayList<Integer> getCalidadAire() {
        return calidadAire;
    }
}
