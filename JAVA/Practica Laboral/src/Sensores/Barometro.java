package Sensores;

import java.util.ArrayList;

public class Barometro extends Sensores{

    private ArrayList<Double> pascales;

    public Barometro(String ubicacion, String tipo, String entorno) {
        super(ubicacion, tipo, entorno);
    }

    public ArrayList<Double> getPascales(){
        return pascales;
    }

    public void addPascale(double p){
        pascales.add(p);
    }
}
