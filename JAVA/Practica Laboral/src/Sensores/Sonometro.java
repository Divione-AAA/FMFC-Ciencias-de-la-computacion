package Sensores;

import java.util.ArrayList;

public class Sonometro extends Sensores{

    private ArrayList<Double> decibeles;

    public Sonometro(String ubicacion, String tipo, String entorno) {
        super(ubicacion, tipo, entorno);
    }

    public ArrayList<Double> getDecibeles() {
        return decibeles;
    }

    public void addDecibeles(Double d) {
        decibeles.add(d);
    }
}
