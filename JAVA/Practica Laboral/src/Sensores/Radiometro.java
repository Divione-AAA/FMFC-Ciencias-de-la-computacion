package Sensores;

import java.util.ArrayList;

public class Radiometro extends Sensores{

    private ArrayList<Double> UV;

    public Radiometro(String ubicacion, String tipo, String entorno) {
        super(ubicacion, tipo, entorno);
    }

    public void addUV(double u) {
        UV.add(u);
    }

    public ArrayList<Double> getUV() {
        return UV;
    }
}
